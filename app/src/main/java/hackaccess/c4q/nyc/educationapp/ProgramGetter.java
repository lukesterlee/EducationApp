package hackaccess.c4q.nyc.educationapp;

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
 * Class used to handle parsing of the aunt bertha API data.
 */
public class ProgramGetter {

    public static final String PRE_ENDPOINT = "https://searchbertha-hrd.appspot.com/_ah/api/search/v1/zipcodes/";
    public static final String POST_ENDPOINT = "/programs?api_key=b30f1b9f41161c0fb3b39cb49aff8104&serviceTag=mentoring&attributeTag=young%20adults";


    public String getJsonString(String zipcode) {

        URL url = null;
        try {
            url = new URL(PRE_ENDPOINT + zipcode + POST_ENDPOINT);
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
        return null;
    }

    public List<Program> getHardCodingData(String zipcode) {

        List<Program> list = new ArrayList<Program>();

        String body = getJsonString(zipcode);

        if (body != null) {
            JSONObject object = null;
            try {
                object = new JSONObject(body);
                JSONArray programs = object.getJSONArray("programs");

                for (int i = 0; i < programs.length(); i++) {
                    JSONObject item = programs.getJSONObject(i);
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
