package consumer.com.consumeraround.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import consumer.com.consumeraround.R;
import consumer.com.consumeraround.adapter.CityListAdapter;
import consumer.com.consumeraround.listener.OnCitySelectedListener;
import consumer.com.consumeraround.model.City;
import consumer.com.consumeraround.service.CityService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CityListActivity extends AppCompatActivity {

    private RecyclerView recyclerViewListCity;
    private CityListAdapter cityListAdapter;
    private Call<List<City>> mListCity;
    private List<City> cityList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_list);

        recyclerViewListCity = (RecyclerView) findViewById(R.id.recycler_view_city_list);
        recyclerViewListCity.setHasFixedSize(true);
        recyclerViewListCity.setLayoutManager(new LinearLayoutManager(this));

        getListCity();

    }

    public void getListCity() {

        mListCity = CityService.Factory.create().fetchListCity();

        mListCity.enqueue(new Callback<List<City>>() {
            @Override
            public void onResponse(Call<List<City>> call, Response<List<City>> response) {

                cityList = response.body();

                if(cityList != null) {

                    showFixtures(cityList);

                }

            }

            @Override
            public void onFailure(Call<List<City>> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    public void getFixturesData(String currentMatchDay) {

        mListCity = CityService.Factory.create().retrieveCitiesByCountryName(currentMatchDay);

        mListCity.enqueue(new Callback<List<City>>() {
            @Override
            public void onResponse(Call<List<City>> call, Response<List<City>> response) {

                List<City> listCity = response.body();

                if(listCity != null) {

                    showFixtures(listCity);

                }

            }

            @Override
            public void onFailure(Call<List<City>> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    private void showFixtures(List<City> listCity) {

        if(listCity != null){

            cityListAdapter = new CityListAdapter(listCity, getApplicationContext());
            recyclerViewListCity.setAdapter(cityListAdapter);

        }

    }
}
