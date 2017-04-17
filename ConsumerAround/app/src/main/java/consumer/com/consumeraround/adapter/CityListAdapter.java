package consumer.com.consumeraround.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import consumer.com.consumeraround.R;
import consumer.com.consumeraround.activities.CityDetailActivity;
import consumer.com.consumeraround.listener.OnCitySelectedListener;
import consumer.com.consumeraround.listener.OnItemClickListener;
import consumer.com.consumeraround.model.City;
import consumer.com.consumeraround.viewHolders.CitiesViewHolder;

/**
 * Created by ederson.js on 01/11/2016.
 */

public class CityListAdapter extends RecyclerView.Adapter<CitiesViewHolder> {
    private static final String TAG = "CityListAdapter";

    private Context context;
    private List<City> cityList;

    public CityListAdapter(List<City> cityList, Context context) {
        this.cityList = cityList;
        this.context = context;
    }

    @Override
    public CitiesViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view_city_name, viewGroup, false);
        CitiesViewHolder fixturesViewHolder = new CitiesViewHolder(view);
        view.setTag(fixturesViewHolder);

        return fixturesViewHolder;
    }

    @Override
    public void onBindViewHolder(CitiesViewHolder citiesViewHolder, int position) {
        Log.i(TAG, "onBindViewHolder() inside method");

        // Gets the position of the item on the List and add the object information
        final City city = this.cityList.get(position);

        citiesViewHolder.getTextViewCityId().setText(String.valueOf(city.getId()));
        citiesViewHolder.getTextViewCityName().setText(city.getName());

        // Listener to the card view item to show a detail when is clicked
        citiesViewHolder.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                Intent intent = new Intent(context, CityDetailActivity.class);

                Bundle bundle = new Bundle();
                bundle.putInt(CityDetailActivity.POSITION, position);
                bundle.putSerializable(CityDetailActivity.CITY, city);

                intent.putExtras(bundle);

                view.getContext().startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return (cityList != null) ? cityList.size() : 0;
    }

}
