package consumer.com.consumeraround.viewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import consumer.com.consumeraround.R;
import consumer.com.consumeraround.listener.OnItemClickListener;

/**
 * Created by root on 01/11/16.
 */

public class CitiesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private TextView textViewCityId;
    private TextView textViewCityName;

    private OnItemClickListener onItemClickListener;

    public CitiesViewHolder(View itemView) {
        super(itemView);

        textViewCityId = (TextView) itemView.findViewById(R.id.textview_city_id);
        textViewCityName = (TextView) itemView.findViewById(R.id.textview_city_name);

        itemView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        onItemClickListener.onItemClick(v, getLayoutPosition());
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public TextView getTextViewCityId() {
        return textViewCityId;
    }

    public TextView getTextViewCityName() {
        return textViewCityName;
    }

}
