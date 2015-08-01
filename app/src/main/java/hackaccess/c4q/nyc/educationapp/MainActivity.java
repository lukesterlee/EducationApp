package hackaccess.c4q.nyc.educationapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import auntbertha.Program;
import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends ActionBarActivity {


    private ListView mListView;
    private TextView mTextView;

    private CardAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.listView);

        //ProgramGetter rp = new ProgramGetter();

        //List<Program> myList =rp.parseData("11226");



        //mTextView = (TextView) findViewById(R.id.textView_test);

        //new TestTask().execute();


        new ProgramTask().execute();



    }

    private class ProgramTask extends AsyncTask<Void, Void, List<hackaccess.c4q.nyc.educationapp.Program>> {

        @Override
        protected List<hackaccess.c4q.nyc.educationapp.Program> doInBackground(Void... params) {

            List<hackaccess.c4q.nyc.educationapp.Program> list = new ArrayList<>();
            list.add(new hackaccess.c4q.nyc.educationapp.Program(3.2, "luke"));


            //return list;
            return new ProgramGetter().getHardCodingData("11367");
        }

        @Override
        protected void onPostExecute(List<hackaccess.c4q.nyc.educationapp.Program> programs) {
            mAdapter = new CardAdapter(getApplicationContext(), programs);
            mListView.setAdapter(mAdapter);

        }
    }


    private class TestTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {



            return new ProgramGetter().getJsonString("11367");
        }

        @Override
        protected void onPostExecute(String programs) {
            mTextView.setText(programs);

        }
    }


}
