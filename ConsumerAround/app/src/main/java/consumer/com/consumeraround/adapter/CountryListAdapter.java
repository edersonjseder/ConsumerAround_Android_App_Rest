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
import consumer.com.consumeraround.activities.CountryDetailActivity;
import consumer.com.consumeraround.listener.OnCitySelectedListener;
import consumer.com.consumeraround.listener.OnCountrySelectedListener;
import consumer.com.consumeraround.listener.OnItemClickListener;
import consumer.com.consumeraround.model.City;
import consumer.com.consumeraround.model.Country;
import consumer.com.consumeraround.viewHolders.CitiesViewHolder;
import consumer.com.consumeraround.viewHolders.CountriesViewHolder;

/**
 * Created by ederson.js on 01/11/2016.
 */

public class CountryListAdapter extends RecyclerView.Adapter<CountriesViewHolder> {
    private static final String TAG = "CountryListAdapter";

    private Context context;
    private List<Country> countryList;

    public CountryListAdapter(List<Country> countryList, Context context) {
        this.countryList = countryList;
        this.context = context;
    }

    @Override
    public CountriesViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view_country_name, viewGroup, false);
        CountriesViewHolder fixturesViewHolder = new CountriesViewHolder(view);
        view.setTag(fixturesViewHolder);

        return fixturesViewHolder;
    }

    @Override
    public void onBindViewHolder(CountriesViewHolder countriesViewHolder, int position) {
        Log.i(TAG, "onBindViewHolder() inside method");

        // Gets the position of the item on the List and add the object information
        final Country country = this.countryList.get(position);

        countriesViewHolder.getTextViewCountryId().setText(String.valueOf(country.getId()));
        countriesViewHolder.getTextViewCountryName().setText(country.getName());

        // Listener to the card view item to show a detail when is clicked
        countriesViewHolder.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                // Intent to call the Detail activity
                Intent intent = new Intent(context, CountryDetailActivity.class);

                // Pasing Country Object and position integer as bundle arguments
                // to the next activity
                Bundle bundle = new Bundle();
                bundle.putInt(CountryDetailActivity.POSITION, position);
                bundle.putSerializable(CountryDetailActivity.COUNTRY, country);

                intent.putExtras(bundle);

                // Calling the other actvity
                view.getContext().startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return (countryList != null) ? countryList.size() : 0;
    }

}
