package hackaccess.c4q.nyc.educationapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

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
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.ui.IconGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import hackaccess.c4q.nyc.educationapp.chat.ChatRoomActivity;
import hackaccess.c4q.nyc.educationapp.program.ProgramActivity;

/**
 * Created by sufeizhao on 8/2/15.
 */
public class MapActivity extends AppCompatActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private GoogleApiClient googleApiClient;
    private LocationRequest mLocationRequest;
    private GoogleMap map;
    private Location location = new Location("Current Location");
    boolean gps_enabled = false;
    private boolean isLoggedIn = false;
    private SharedPreferences preferences;
    private Program programClicked;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        // Connect to Geolocation API to make current location request
        locationServiceIsAvailable();
        connectGoogleApiClient();
        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(10000)        // 10 seconds, in milliseconds
                .setFastestInterval(5000); // 1 second, in milliseconds

        // Create MapFragment based on map xml
        MapFragment mapFragment = (MapFragment) this.getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        map = mapFragment.getMap();
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
                connectionResult.startResolutionForResult(this, Constants.CONNECTION_FAILURE_RESOLUTION_REQUEST);
            } catch (IntentSender.SendIntentException e) {
                e.printStackTrace();
            }
        } else {
            Log.i("Map", "Location services connection failed with code " + connectionResult.getErrorCode());
        }
    }

    @Override // must override
    public void onConnectionSuspended(int i) {
    }

    @Override
    public void onLocationChanged(Location location) {
        preferences = getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Constants.LASTLATITUDE, String.valueOf(location.getLatitude()));
        editor.putString(Constants.LASTLONGITUDE, String.valueOf(location.getLongitude())).apply();
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

        if (gps_enabled)
            geocodeTask.execute();
    }

    @Override
    protected void onStart() {
        super.onStart();
        locationServiceIsAvailable();
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception ex) {}
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

            Geocoder geocoder = new Geocoder(MapActivity.this);
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
            new ProgramTask().execute(zipcode);
        }
    };

    private class ProgramTask extends AsyncTask<String, Void, List<Program>> {

        @Override
        protected List<Program> doInBackground(String... zipcode) {
            return new ProgramGetter().getHardCodingData(zipcode[0]);
        }

        @Override
        protected void onPostExecute(List<Program> programs) {
            populateMap(programs);
        }
    }

    // Task to decode current location
    public void populateMap(List<Program> programs) {
        List<LatLng> latLngs = new ArrayList<LatLng>();
        int count = 1;

        for (Program program : programs) {

            LatLng latLng = new LatLng(program.getLatitude(), program.getLongitude());

            latLngs.add(latLng);

            IconGenerator mIconGenerator = new IconGenerator(MapActivity.this);
            Bitmap iconBitmap = mIconGenerator.makeIcon(Integer.toString(count));
            map.addMarker(new MarkerOptions().get
                    .position(latLng)
                    .title(program.getName())
                    .snippet(getResources().getString(R.string.marker_snippet))
                    .icon(BitmapDescriptorFactory.fromBitmap(iconBitmap)));

            count++;
        }

        Log.d("Map", "Fix Zoom");
        fixZoomForLatLngs(map, latLngs);
        map.setOnInfoWindowClickListener(new ProgramClickListener(programs));
    }

    public static void fixZoomForLatLngs(GoogleMap map, List<LatLng> latLngs) {
        if (latLngs != null && latLngs.size() > 0) {
            LatLngBounds.Builder bc = new LatLngBounds.Builder();

            for (LatLng latLng : latLngs)
                bc.include(latLng);

            map.animateCamera(CameraUpdateFactory.newLatLngBounds(bc.build(), 50));
        }
    }

    public class ProgramClickListener implements GoogleMap.OnInfoWindowClickListener {
        List<Program> programs;

        public ProgramClickListener(List<Program> programs) {
            this.programs = programs;
        }

        @Override
        public void onInfoWindowClick(Marker marker) {
            Intent intent = new Intent(MapActivity.this, ProgramActivity.class);
            String title = marker.getTitle();
            Program program = programs.get(programs.indexOf(title));

            intent.putExtra(Constants.EXTRA_PROGRAM, (Parcelable) program);
            startActivity(intent);
        }
    }

    // MENU RESOURCES
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.map_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_listview) {
            Intent list = new Intent(this, DirectoryActivity.class);
            startActivity(list);
        }
        if (id == R.id.action_profile) {
            if (isLoggedIn) {
                Intent profile = new Intent(this, ProfileActivity.class);
                startActivity(profile);
            } else {
                Intent create = new Intent(this, CreateProfileActivity.class);
                startActivity(create);
            }
        }
        if (id == R.id.action_chat) {
            Intent chat = new Intent(this, ChatRoomActivity.class);
            startActivity(chat);
        }
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    public void locationServiceIsAvailable() {
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        boolean gps_enabled = false;

        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception ex) {}

        if (!gps_enabled) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setMessage(getResources().getString(R.string.gps_network_not_enabled));
            dialog.setPositiveButton(getResources().getString(R.string.open_location_settings), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(myIntent);
                }
            });
            dialog.setNegativeButton(getString(R.string.Cancel), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    Toast.makeText(MapActivity.this, "Location Services disabled", Toast.LENGTH_LONG).show();
                }
            });
            dialog.show();
        }
    }
}
