package hackaccess.c4q.nyc.educationapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
public class ProgramActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.program_activity_layout);

        Intent intent = getIntent();
        //Program program = intent.getParcelableExtra("program");

    }
}
