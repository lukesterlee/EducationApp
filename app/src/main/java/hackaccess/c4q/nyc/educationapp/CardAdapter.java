package hackaccess.c4q.nyc.educationapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


/**
 * Created by Willee on 8/1/15.
 */
public class CardAdapter extends BaseAdapter {

    private Context mContext;
    private List<Program> mList;
    private LayoutInflater mInflater;


    private TextView mTextViewName;
    private TextView mTextViewDistance;
    private ImageView mImageView;

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

        mTextViewName.setText(getItem(position).getName());
        mTextViewDistance.setText(getItem(position).getDistance() + "");
       // mImageView.setImageBitmap(BITMAP);

        return convertView;
    }
}

