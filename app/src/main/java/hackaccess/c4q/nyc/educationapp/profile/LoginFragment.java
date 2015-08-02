package hackaccess.c4q.nyc.educationapp.profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.firebase.client.Firebase;

import hackaccess.c4q.nyc.educationapp.FirebaseHelper;
import hackaccess.c4q.nyc.educationapp.R;

public class LoginFragment extends Fragment implements View.OnClickListener {

    EditText emailLogin, passwordLogin;
    FirebaseHelper firebaseHelper;
    Firebase ref;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.fragment_login, container, false);

        emailLogin = (EditText) result.findViewById(R.id.emailLogin);
        passwordLogin = (EditText) result.findViewById(R.id.passwordLogin);
        firebaseHelper = FirebaseHelper.getInstance(getActivity().getApplicationContext());
        ref = firebaseHelper.getRef();

        return result;
    }

    public void login(View view){

        String email = emailLogin.getText().toString();
        String password = passwordLogin.getText().toString();

        //TODO: Login with firebase method

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.userSignUpButton:
                login(v);
                break;
        }
    }
}
