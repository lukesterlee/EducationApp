package hackaccess.c4q.nyc.educationapp;

import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.ui.IconGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DirectoryActivity extends ActionBarActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private ListView mListView;
    private GoogleApiClient googleApiClient;
    private LocationRequest mLocationRequest;
    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
    private GoogleMap map;
    private Location location;
    private CardAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.directory_activity_layout);
        mListView = (ListView) findViewById(R.id.list_view);

        // Connect to Geolocation API to make current location request
        connectGoogleApiClient();
        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(10000)        // 10 seconds, in milliseconds
                .setFastestInterval(5000); // 1 second, in milliseconds

        // Create MapFragment based on map xml
        MapFragment mapFragment = (MapFragment) this.getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        map = mapFragment.getMap();

        mListView.setOnItemClickListener(new ProgramClickListener());
    }

    @Override
    public void onMapReady(final GoogleMap map) {
        map.getUiSettings().setMapToolbarEnabled(true);
        map.getUiSettings().setZoomControlsEnabled(true);
        map.getUiSettings().setCompassEnabled(true);
        map.getUiSettings().setMapToolbarEnabled(true);
        map.getUiSettings().setScrollGesturesEnabled(true);
        map.getUiSettings().setRotateGesturesEnabled(true);
        map.setMyLocationEnabled(true);
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }

    // Override methods for Connection Call Back for Geolocation API
    @Override
    public void onConnected(Bundle bundle) {
        location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
        if (location == null)
            LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, mLocationRequest, this);
        else
            handleNewLocation(location);
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        if (connectionResult.hasResolution()) {
            try {
                connectionResult.startResolutionForResult(this, CONNECTION_FAILURE_RESOLUTION_REQUEST);
            } catch (IntentSender.SendIntentException e) {
                e.printStackTrace();
            }
        } else {
            Log.i("Map", "Location services connection failed with code " + connectionResult.getErrorCode());
        }
    }

    @Override // must override
    public void onConnectionSuspended(int i) {}

    @Override
    public void onLocationChanged(Location location) {
        handleNewLocation(location);
    }

    private void handleNewLocation(Location location) {
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        LatLng locationLatLng = new LatLng(latitude, longitude);
        location.setLatitude(latitude);
        location.setLongitude(longitude);

        // Set initial view to current location
        map.moveCamera(CameraUpdateFactory.newLatLng(locationLatLng));
        map.animateCamera(CameraUpdateFactory.zoomTo(13));

        geocodeTask.execute();
    }

    protected synchronized void connectGoogleApiClient() {
        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .build();
        googleApiClient.connect();
        Log.d("Map", "Connected to Google API Client");
    }

    // Task to decode current location
    AsyncTask<Void, Void, Address> geocodeTask = new AsyncTask<Void, Void, Address>() {
        @Override
        protected Address doInBackground(Void... params) {
            Address address = null;

            Geocoder geocoder = new Geocoder(DirectoryActivity.this);
            try {
                List<Address> locations = geocoder.getFromLocation(
                        location.getLatitude(), location.getLongitude(), 1 /* maxResults */);
                address = locations.get(0);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return address;
        }

        @Override
        protected void onPostExecute(Address address) {
            String zipcode = address.getPostalCode();
            Log.d("ZIPCODE", zipcode);
            new ProgramTask().execute(zipcode);
        }
    };

    public class ProgramClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(DirectoryActivity.this, ProgramActivity.class);
            //intent.putExtra("program", programs.get(position));
            startActivity(intent);
        }
    }

    private class ProgramTask extends AsyncTask<String, Void, List<Program>> {

        @Override
        protected List<Program> doInBackground(String... zipcode) {

            return new ProgramGetter().getHardCodingData(zipcode[0]);
        }

        @Override
        protected void onPostExecute(List<Program> programs) {
            mAdapter = new CardAdapter(getApplicationContext(), programs);
            mListView.setAdapter(mAdapter);
            populateMap(programs);
        }
    }

    // Task to decode current location
    public void populateMap(List<Program> programs) {
        List<LatLng> latLngs = new ArrayList<LatLng>();
        int count = 1;

        for (Program program : programs) {
            latLngs.add(program.getLatLng());

            IconGenerator mIconGenerator = new IconGenerator(DirectoryActivity.this);
            Bitmap iconBitmap = mIconGenerator.makeIcon(Integer.toString(count));
            map.addMarker(new MarkerOptions()
                    .position(program.getLatLng())
                    .title(program.getName())
                    .icon(BitmapDescriptorFactory.fromBitmap(iconBitmap)));
            count++;
        }

        Log.d("Map", "Fix Zoom");
        fixZoomForLatLngs(map, latLngs);
    }

    public static void fixZoomForLatLngs(GoogleMap map, List<LatLng> latLngs) {
        if (latLngs != null && latLngs.size() > 0) {
            LatLngBounds.Builder bc = new LatLngBounds.Builder();

            for (LatLng latLng : latLngs)
                bc.include(latLng);

            map.animateCamera(CameraUpdateFactory.newLatLngBounds(bc.build(), 50));
        }
    }
}
