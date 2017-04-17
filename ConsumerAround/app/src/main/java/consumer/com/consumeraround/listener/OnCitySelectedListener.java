package consumer.com.consumeraround.listener;

import consumer.com.consumeraround.model.City;

/**
 * Created by root on 16/04/17.
 */

public interface OnCitySelectedListener {

    // called when user selects a city
    void onCitySelected(City city, int position);

}
