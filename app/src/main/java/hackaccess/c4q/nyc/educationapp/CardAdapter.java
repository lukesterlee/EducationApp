package hackaccess.c4q.nyc.educationapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CardAdapter extends BaseAdapter {

    private Context mContext;
    private List<Program> mList;
    private LayoutInflater mInflater;
    private String preHTTP = "https://maps.googleapis.com/maps/api/streetview?key=AIzaSyDTaAeiCfVCXJhdweubPkgIvsni3s1-9ss&size=800x400&location=";

    private TextView mTextViewName;
    private TextView mTextViewDistance;
    private ImageView mImageView;

    private TextView mTextViewOpenHour;
    private TextView mTextViewLanguage;

    public CardAdapter(Context mContext, List<Program> mList) {
        this.mContext = mContext;
        this.mList = mList;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Program getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item_place, parent, false);
        }

        mTextViewName = (TextView) convertView.findViewById(R.id.tv_agency_name);
        mTextViewDistance = (TextView) convertView.findViewById(R.id.tv_agency_distance);
        mImageView = (ImageView) convertView.findViewById(R.id.iv_agency_photo);
        mTextViewOpenHour = (TextView) convertView.findViewById(R.id.tv_hours);
        mTextViewLanguage = (TextView) convertView.findViewById(R.id.tv_agency_language);

        Program program = getItem(position);

        mTextViewName.setText(program.getName());
        mTextViewDistance.setText(program.getDistance() + " mi");
        mTextViewLanguage.setText("Language : " + program.getLanguage());

        Calendar now = Calendar.getInstance();
        int day = now.get(Calendar.DAY_OF_WEEK);
        String today = "";
        switch (day) {
            case Calendar.MONDAY:
                today = program.getMonday();
                break;
            case Calendar.TUESDAY:
                today = program.getTuesday();
                break;
            case Calendar.WEDNESDAY:
                today = program.getWednesday();
                break;
            case Calendar.THURSDAY:
                today = program.getThursday();
                break;
            case Calendar.FRIDAY:
                today = program.getFriday();
                break;
            case Calendar.SATURDAY:
                today = program.getSaturday();
                break;
            case Calendar.SUNDAY:
                today = program.getSunday();
                break;
        }
        mTextViewOpenHour.setText("Today : " + today);

        URL url = null;
        try {
            url = new URL(preHTTP + getItem(position).getLatitude() + "," + getItem(position).getLongitude());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Glide.with(mContext).load(url).centerCrop().into(mImageView);

        return convertView;
    }
}

