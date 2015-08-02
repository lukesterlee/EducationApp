package hackaccess.c4q.nyc.educationapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Willee on 8/1/15.
 */
public class ContactFragment extends Fragment {

    private TextView mTextViewPhoneNumber;
    private Program mProgram;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.fragment_contact, container, false);




        Bundle bundle = getArguments();

        if (bundle != null) {
            mProgram = bundle.getParcelable(Constants.EXTRA_PROGRAM);
        }


        mTextViewPhoneNumber = (TextView) result.findViewById(R.id.textView_phone_number);
        mTextViewPhoneNumber.setText(mProgram.getPhoneNumber());

        return result;
    }
}
