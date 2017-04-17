package consumer.com.consumeraround.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by root on 19/11/16.
 */
public class City implements Serializable{

    private static final long serialVersionUID = -222864142685757025L;

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("country")
    private Country country;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        if(this.country == null){
            this.country = new Country();
        }
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}