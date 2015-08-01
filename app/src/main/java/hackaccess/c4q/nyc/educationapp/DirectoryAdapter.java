package hackaccess.c4q.nyc.educationapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by sufeizhao on 8/1/15.
 */
public class DirectoryAdapter extends RecyclerView.Adapter {

    private List<Card> cardsArray;
    private Context context;

    public DirectoryAdapter(Context context, List<Card> cardsArray) {
        this.context = context;
        this.cardsArray = cardsArray;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.directory_layout, viewGroup, false);
        return new DirectoryViewHolder(itemView);

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


}
