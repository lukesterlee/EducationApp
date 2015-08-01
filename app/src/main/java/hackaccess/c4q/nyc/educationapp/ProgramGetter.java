package hackaccess.c4q.nyc.educationapp;

import android.util.Log;

import java.util.ArrayList;

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

    private ArrayList<Program> mList;
    public static final String AUNTBERTHA_ENDPOINT = "https://searchbertha-hrd.appspot.com/_ah/api/search/v1/zipcodes";


    public ArrayList<Program> parseData(String zipcode) {

        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(AUNTBERTHA_ENDPOINT).build();

        AuntBerthaApi auntBerthaApi = restAdapter.create(AuntBerthaApi.class);

        auntBerthaApi.getFeed(zipcode, new Callback<ABModel>() {
            @Override
            public void success(ABModel abModel, Response response) {
                mList = (ArrayList<Program>) abModel.getPrograms();

            }
            @Override
            public void failure(RetrofitError e) {
                Log.e("retroFAIL", "URL: " + e.getUrl() + "||| Error: " + e.toString());
                Log.e("retroFAIL", "Size of mList: " + mList.size());

            }
        });
        return mList;

    }


}
