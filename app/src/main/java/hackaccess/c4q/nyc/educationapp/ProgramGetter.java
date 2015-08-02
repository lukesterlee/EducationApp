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

                    if (distance <= 10){
                        String name = item.getString("name");
                        JSONArray offices = item.getJSONArray("offices");
                        JSONObject firstOffice = offices.getJSONObject(0);
                        JSONObject location = firstOffice.getJSONObject("location");
                        JSONArray languages = item.getJSONArray("supported_languages");
                        JSONObject hours = firstOffice.getJSONObject("hours");

                        String monday;
                        String tuesday;
                        String wednesday;
                        String thursday;
                        String friday;
                        String saturday;
                        String sunday;

                        if (hours.getBoolean("monday")) {
                            monday = hours.getString("monday_start") + " - ";
                            monday += hours.getString("monday_finish");
                        } else {
                            monday = "Closed";
                        }

                        if (hours.getBoolean("tuesday")) {
                            tuesday = hours.getString("tuesday_start") + " - ";
                            tuesday += hours.getString("tuesday_finish");
                        } else {
                            tuesday = "Closed";
                        }

                        if (hours.getBoolean("wednesday")) {
                            wednesday = hours.getString("wednesday_start") + " - ";
                            wednesday += hours.getString("wednesday_finish");
                        } else {

                            wednesday = "Closed";
                        }

                        if (hours.getBoolean("thursday")) {
                            thursday = hours.getString("thursday_start") + " - ";
                            thursday += hours.getString("thursday_finish");
                        } else {

                            thursday = "Closed";
                        }

                        if (hours.getBoolean("friday")) {
                            friday = hours.getString("friday_start") + " - ";
                            friday += hours.getString("friday_finish");
                        } else {

                            friday = "Closed";
                        }

                        if (hours.getBoolean("saturday")) {
                            saturday = hours.getString("saturday_start") + " - ";
                            saturday += hours.getString("saturday_finish");
                        } else {

                            saturday = "Closed";
                        }

                        if (hours.getBoolean("sunday")) {
                            sunday = hours.getString("sunday_start") + " - ";
                            sunday += hours.getString("sunday_finish");
                        } else {

                            sunday = "Closed";
                        }

                        List<String> hoursList = new ArrayList<>();
                        hoursList.add(monday);
                        hoursList.add(tuesday);
                        hoursList.add(wednesday);
                        hoursList.add(thursday);
                        hoursList.add(friday);
                        hoursList.add(saturday);
                        hoursList.add(sunday);

                        String programId = item.getString("id");
                        double latitude = location.getDouble("latitude");
                        double longitude = location.getDouble("longitude");
                        String description = item.getString("description");
                        String language = "";


                        for (int j = 0; j < languages.length(); j++) {
                            language += languages.getString(j) + " ";
                        }

                        String lastUpdated = item.getString("update_date");
                        String phoneNumber;

                        try {
                            phoneNumber = firstOffice.getString("phone_number");
                        } catch (JSONException e) {
                            phoneNumber = "N/A";
                        }


                        list.add(new Program(description, distance, language, lastUpdated,
                                latitude, longitude, name, phoneNumber, programId, zipcode, monday,
                                tuesday, wednesday, thursday, friday, saturday, sunday));
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return list;
    }
}
