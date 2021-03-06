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

public class DetailsFragment extends Fragment {

    private Program mProgram;

    private TextView mTextViewName;
    private TextView mTextViewDescription;
    private TextView mTextViewLanguage;
    private TextView mTextViewLastUpdated;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.fragment_details, container, false);

        Bundle bundle = getArguments();

        if (bundle != null) {
            mProgram = bundle.getParcelable(Constants.EXTRA_PROGRAM);
        }

        mTextViewDescription = (TextView) result.findViewById(R.id.tv_description);
        mTextViewDescription.setText(mProgram.getDescription());

        mTextViewLanguage = (TextView) result.findViewById(R.id.tv_languages);
        mTextViewLanguage.setText(mProgram.getLanguage());

        mTextViewLastUpdated = (TextView) result.findViewById(R.id.tv_last_updated);
        mTextViewLastUpdated.setText(mProgram.getLastUpdated());

        mTextViewName = (TextView) result.findViewById(R.id.tv_office_name);
        mTextViewName.setText(mProgram.getName());

        return result;
    }
}
