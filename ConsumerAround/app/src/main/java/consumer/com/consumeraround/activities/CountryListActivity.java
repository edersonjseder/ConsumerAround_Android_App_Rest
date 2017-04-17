package consumer.com.consumeraround.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import consumer.com.consumeraround.R;
import consumer.com.consumeraround.adapter.CountryListAdapter;
import consumer.com.consumeraround.listener.OnCitySelectedListener;
import consumer.com.consumeraround.listener.OnCountrySelectedListener;
import consumer.com.consumeraround.model.City;
import consumer.com.consumeraround.model.Country;
import consumer.com.consumeraround.service.CountryService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CountryListActivity extends AppCompatActivity {

    private RecyclerView recyclerViewListCountry;
    private CountryListAdapter countryListAdapter;
    private Call<List<Country>> mListCountry;
    private List<Country> countryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_list);

        recyclerViewListCountry = (RecyclerView) findViewById(R.id.recycler_view_country_list);
        recyclerViewListCountry.setHasFixedSize(true);
        recyclerViewListCountry.setLayoutManager(new LinearLayoutManager(this));

        getListCountry();
    }

    public void getListCountry() {

        mListCountry = CountryService.Factory.create().fetchListCountry();

        mListCountry.enqueue(new Callback<List<Country>>() {
            @Override
            public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {

                countryList = response.body();

                if(countryList != null) {

                    showFixtures(countryList);

                }

            }

            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    private void showFixtures(List<Country> listCountry) {

        if(listCountry != null){

            countryListAdapter = new CountryListAdapter(listCountry, getApplicationContext());
            recyclerViewListCountry.setAdapter(countryListAdapter);

        }

    }
}
