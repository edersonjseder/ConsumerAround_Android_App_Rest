package consumer.com.consumeraround.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import consumer.com.consumeraround.R;
import consumer.com.consumeraround.databinding.ActivityCountryDetailBinding;
import consumer.com.consumeraround.databinding.ActivityMainConsumerBinding;
import consumer.com.consumeraround.model.City;
import consumer.com.consumeraround.model.Country;
import consumer.com.consumeraround.service.CityService;
import consumer.com.consumeraround.service.CountryService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CountryDetailActivity extends AppCompatActivity {
    private static final String TAG = "CountryDetailActivity";

    public static final String POSITION = "position";
    public static final String COUNTRY = "country";

    private Country country;
    private Country mCountry;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCountryDetailBinding countryBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_country_detail);

        Bundle bundle = getIntent().getExtras();
        position = bundle.getInt(POSITION);
        country = (Country) bundle.getSerializable(COUNTRY);

        countryBinding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCountryObject();
            }
        });


        countryBinding.textviewCountryId.setText(String.valueOf(position));
        countryBinding.textviewCountryName.setText(country.getName());

    }

    public void addCountryObject() {

        AlertDialog.Builder alertAddCountry = new AlertDialog.Builder(this);

        // Object to add in Data base
        mCountry = new Country();

        alertAddCountry.setMessage("Adding Country");
        alertAddCountry.setTitle("Enter the country name");

        LayoutInflater inflater = getLayoutInflater();
        View alertCityLayout = inflater.inflate(R.layout.add_country_name, null);

        final EditText countryName = (EditText) alertCityLayout.findViewById(R.id.text_add_country_name_c);

        alertAddCountry.setView(alertCityLayout);

        alertAddCountry.setPositiveButton("Save", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int whichButton) {

                // gets the Country name from user
                String mCountryName = countryName.getText().toString();

                mCountry.setName(mCountryName);

                Log.i(TAG, "setPositiveButton() inside method " + mCountry);

                Call<Country> call = CountryService.Factory.create().saveCountryObject(mCountry);
                call.enqueue(new Callback<Country>() {
                    @Override
                    public void onResponse(Call<Country> call, Response<Country> response) {

                        mCountry = response.body();

                        Toast.makeText(getApplicationContext(), "City " + country.getName() + " created", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(Call<Country> call, Throwable t) {

                        Toast.makeText(getApplicationContext(), "Couldn't create Country", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
        alertAddCountry.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Canceled.
                dialog.cancel();
            }
        });
        alertAddCountry.show();
    }
}
