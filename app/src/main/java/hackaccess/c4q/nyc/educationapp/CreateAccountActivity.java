package hackaccess.c4q.nyc.educationapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;

import hackaccess.c4q.nyc.educationapp.firebase.Account;

/**
 * Created by c4q-anthonyf on 8/1/15.
 */
public class CreateAccountActivity extends Activity implements View.OnClickListener{

    private EditText mEditTextEmail;
    private EditText mEditTextPassword;
    private Button mButton;

    private FirebaseHelper mHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        mEditTextEmail = (EditText) findViewById(R.id.editText_email);
        mEditTextPassword = (EditText) findViewById(R.id.editText_password);
        mButton = (Button) findViewById(R.id.button);

        mButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        mHelper = FirebaseHelper.getInstance(getApplicationContext());

        mHelper.createAccount(mEditTextEmail.getText().toString(), mEditTextPassword.getText().toString());

//        if () {
//            Toast.makeText(getApplicationContext(), "Account Created", Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(getApplicationContext(), "Email Already In Use", Toast.LENGTH_SHORT).show();
//
//        }


    }
}
