package hackaccess.c4q.nyc.educationapp;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

import java.util.List;

import AuntBertha.Program;


public class MainActivity extends ActionBarActivity {

    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ProgramGetter rp = new ProgramGetter();

        List<Program> myList =rp.parseData("11226");
        tv = (TextView) findViewById(R.id.testingText);

        if(myList != null){
            tv.setText(myList.get(0).getOffices().get(0).getHours().toString());
        }

    }


}
