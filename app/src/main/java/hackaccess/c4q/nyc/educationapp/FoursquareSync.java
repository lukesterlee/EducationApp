package hackaccess.c4q.nyc.educationapp;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by sufeizhao on 8/1/15.
 */
public class FoursquareSync {

    private String preHTTP = "https://api.foursquare.com/v2/venues/search?client_id=2EF4KXHB5RCBWHULDRCQTC5OTPEV5ZIAHLBMEK04NTAFHRLS&client_secret=IIYDA0UFT4WH2F5KD00EEVUUTOZMH422LXXX5AGHRB1ALI2S&v=20130815&ll=";

    public String getJsonString(List<Program> programs) {
        URL url = null;

        for (Program program : programs) {
            try {
                url = new URL(preHTTP + program.getLatLng().toString());
                Log.d("LATLNG+++++", program.getLatLng().toString());
                HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

                InputStream inputStream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                StringBuilder builder = new StringBuilder();
                String line = null;

                while ((line = reader.readLine()) != null) {

                    builder.append(line + "\n");
                }

                return builder.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public List<Program> getJSONData(List<Program> programs) {

        List<Program> list = new ArrayList<Program>();
        String body = getJsonString(programs);

        if (body != null) {
            JSONObject object = null;
            try {
                object = new JSONObject(body);
                JSONArray items = object.getJSONArray("programs");

                for (int i = 0; i < items.length(); i++) {
                    JSONObject item = items.getJSONObject(i);
                    double distance = item.getDouble("distance");

                    if (distance <= 5){
                        String name = item.getString("name");
                        JSONArray offices = item.getJSONArray("offices");
                        JSONObject inside = offices.getJSONObject(0);
                        JSONObject location = inside.getJSONObject("location");
                        double lat = location.getDouble("latitude");
                        double lon = location.getDouble("longitude");
                        list.add(new Program(distance, name, new LatLng(lat, lon)));
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return list;
    }
}
