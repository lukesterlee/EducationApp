package hackaccess.c4q.nyc.educationapp;

import android.util.Log;

import java.util.List;

import AuntBertha.ABModel;
import AuntBertha.AuntBerthaApi;
import AuntBertha.Program;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Class used to handle parsing of the aunt bertha API data.
 */
public class ProgramGetter {

    public static final String AUNTBERTHA_ENDPOINT = "https://searchbertha-hrd.appspot.com/_ah/api/search/v1/zipcodes";

    private List<Program> mList;

    public List<Program> parseData(String zipcode) {

        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(AUNTBERTHA_ENDPOINT).build();

        AuntBerthaApi auntBerthaApi = restAdapter.create(AuntBerthaApi.class);

        auntBerthaApi.getFeed(zipcode, new Callback<ABModel>() {
            @Override
            public void success(ABModel abModel, Response response) {

                mList = abModel.getPrograms();
                Log.d("retroSuccess", "URL: " + response.getUrl());

            }

            @Override
            public void failure(RetrofitError e) {
                Log.d("retroFAIL", "URL: " + e.getUrl() + "||| Error: " + e.toString());

            }
        });


        return mList;
    }


}
