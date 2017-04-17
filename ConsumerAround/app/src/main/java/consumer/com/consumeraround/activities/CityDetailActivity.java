package consumer.com.consumeraround.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import consumer.com.consumeraround.R;
import consumer.com.consumeraround.databinding.ActivityCityDetailBinding;
import consumer.com.consumeraround.model.City;
import consumer.com.consumeraround.path.InPath;
import consumer.com.consumeraround.service.CityService;
import consumer.com.consumeraround.util.Utils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CityDetailActivity extends AppCompatActivity {
    private static final String TAG = "CityDetailActivity";

    public static final String POSITION = "position";
    public static final String CITY = "city";

    private City city;
    private City mCity;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCityDetailBinding cityBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_city_detail);

        Bundle bundle = getIntent().getExtras();
        position = bundle.getInt(POSITION);
        city = (City) bundle.getSerializable(CITY);

        cityBinding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCityObject();
            }
        });

        cityBinding.textviewCityId.setText(String.valueOf(position));
        cityBinding.textviewCityName.setText(city.getName());
        cityBinding.textviewCountryName.setText(city.getCountry().getName());
    }

    public void addCityObject() {

        AlertDialog.Builder alertAddCity = new AlertDialog.Builder(this);

        // Object to add in Data base
        mCity = new City();

        alertAddCity.setMessage("Adding City");
        alertAddCity.setTitle("Enter the city name");

        LayoutInflater inflater = getLayoutInflater();
        View alertCityLayout = inflater.inflate(R.layout.add_city_name, null);

        final EditText cityName = (EditText) alertCityLayout.findViewById(R.id.text_add_city_name);
        final EditText countryName = (EditText) alertCityLayout.findViewById(R.id.text_add_country_name);

        alertAddCity.setView(alertCityLayout);

        alertAddCity.setPositiveButton("Save", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int whichButton) {

                // gets the City name from user
                String mCityName = cityName.getText().toString();
                String mCountryName = countryName.getText().toString();

                mCity.setName(mCityName);
                mCity.getCountry().setName(mCountryName);

                Log.i(TAG, "setPositiveButton() inside method " + mCity);

                Call<City> call = CityService.Factory.create().saveCityObject(mCity);
                call.enqueue(new Callback<City>() {
                    @Override
                    public void onResponse(Call<City> call, Response<City> response) {

                        mCity = response.body();

                        Toast.makeText(getApplicationContext(), "City " + city.getName() + " created", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(Call<City> call, Throwable t) {

                        Toast.makeText(getApplicationContext(), "Couldn't create City", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
        alertAddCity.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Canceled.
                dialog.cancel();
            }
        });
        alertAddCity.show();
    }

}
