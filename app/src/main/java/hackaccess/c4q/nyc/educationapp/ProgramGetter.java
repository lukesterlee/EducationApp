package hackaccess.c4q.nyc.educationapp;

import android.app.DownloadManager;
import android.util.Log;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

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

import auntbertha.ABModel;
import auntbertha.AuntBerthaApi;
import auntbertha.Program;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Class used to handle parsing of the aunt bertha API data.
 */
public class ProgramGetter {

    public static final String AUNTBERTHA_ENDPOINT = "https://searchbertha-hrd.appspot.com/_ah/api/search/v1/zipcodes";

    public static final String PRE_ENDPOINT = "https://searchbertha-hrd.appspot.com/_ah/api/search/v1/zipcodes/";
    public static final String POST_ENDPOINT = "/programs?api_key=b30f1b9f41161c0fb3b39cb49aff8104&serviceTag=mentoring&attributeTag=young%20adults";


    private List<Program> mList;

    public List<Program> parseData(String zipcode) {

        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(AUNTBERTHA_ENDPOINT).setLogLevel(RestAdapter.LogLevel.FULL).build();

        AuntBerthaApi auntBerthaApi = restAdapter.create(AuntBerthaApi.class);

        auntBerthaApi.getFeed(zipcode, new Callback<ABModel>() {
            @Override
            public void success(ABModel abModel, Response response) {

                mList = abModel.getPrograms();
                Log.d("retroSuccess", "URL: " + response.getUrl());

            }

            @Override
            public void failure(RetrofitError e) {
                mList = new ArrayList<Program>();

                mList.add(new Program());
                Log.d("retroFAIL", "URL: " + e.getUrl() + "||| Error: " + e.toString());

            }
        });


        return mList;
    }


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

//        OkHttpClient client = new OkHttpClient();
//        Request request = new Request.Builder().url(PRE_ENDPOINT + zipcode + POST_ENDPOINT).build();
//
//        com.squareup.okhttp.Response response = null;
//        try {
//            response = client.newCall(request).execute();
//            return response.body().toString();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
    }


    public List<hackaccess.c4q.nyc.educationapp.Program> getHardCodingData(String zipcode) {

        List<hackaccess.c4q.nyc.educationapp.Program> list = new ArrayList<>();

        String body = getJsonString(zipcode);
        Log.i("Luke", body);

        if (body != null) {
            JSONObject object = null;
            try {
                object = new JSONObject(body);
                JSONArray programs = object.getJSONArray("programs");

                for (int i = 0; i < programs.length(); i++) {
                    JSONObject item = programs.getJSONObject(i);
                    double distance = item.getDouble("distance");
                    String name = item.getString("name");
                    list.add(new hackaccess.c4q.nyc.educationapp.Program(distance, name));
                }



            } catch (JSONException e) {
                e.printStackTrace();
            }



        }



        return list;

    }


}
