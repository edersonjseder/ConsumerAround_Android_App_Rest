package consumer.com.consumeraround.viewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import consumer.com.consumeraround.R;
import consumer.com.consumeraround.listener.OnItemClickListener;

/**
 * Created by root on 01/11/16.
 */

public class CountriesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private TextView textViewCountryId;
    private TextView textViewCountryName;

    private OnItemClickListener onItemClickListener;

    public CountriesViewHolder(View itemView) {
        super(itemView);

        textViewCountryId = (TextView) itemView.findViewById(R.id.textview_country_id);
        textViewCountryName = (TextView) itemView.findViewById(R.id.textview_country_name);

        itemView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        onItemClickListener.onItemClick(v, getLayoutPosition());
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public TextView getTextViewCountryId() {
        return textViewCountryId;
    }

    public TextView getTextViewCountryName() {
        return textViewCountryName;
    }

}
