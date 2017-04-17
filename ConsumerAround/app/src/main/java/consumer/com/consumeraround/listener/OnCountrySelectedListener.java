package consumer.com.consumeraround.listener;

import consumer.com.consumeraround.model.City;
import consumer.com.consumeraround.model.Country;

/**
 * Created by root on 16/04/17.
 */

public interface OnCountrySelectedListener {

    // called when user selects a country
    void onCountrySelected(Country country, int position);

}
