package hackaccess.c4q.nyc.educationapp.program;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import hackaccess.c4q.nyc.educationapp.Constants;
import hackaccess.c4q.nyc.educationapp.Program;
import hackaccess.c4q.nyc.educationapp.R;

public class HoursFragment extends Fragment {

    private Program mProgram;

    private TextView mTextViewHours;
    private TextView mTextViewAddress;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.fragment_hours, container, false);

        Bundle bundle = getArguments();

        if (bundle != null) {
            mProgram = bundle.getParcelable(Constants.EXTRA_PROGRAM);
        }


        mTextViewHours = (TextView) result.findViewById(R.id.tv_hours);
        mTextViewAddress = (TextView) result.findViewById(R.id.tv_location);

        mTextViewHours.setText("Monday: " + mProgram.getMonday() + "\n");
        mTextViewHours.append("Tuesday: " + mProgram.getTuesday() + "\n");
        mTextViewHours.append("Wednesday: " + mProgram.getWednesday() + "\n");
        mTextViewHours.append("Thursday: " + mProgram.getThursday() + "\n");
        mTextViewHours.append("Friday: " + mProgram.getFriday() + "\n");
        mTextViewHours.append("Saturday: " + mProgram.getSaturday() + "\n");
        mTextViewHours.append("Sunday: " + mProgram.getSunday() + "\n");

        mTextViewAddress.setText(mProgram.getAddress());

        return result;
    }
}
