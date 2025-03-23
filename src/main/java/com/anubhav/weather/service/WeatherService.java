package com.anubhav.weather.service;

import com.anubhav.weather.model.WeatherApi;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class WeatherService {
  private String city;
  private String units = "metric";
  private final String apiKey =  "9f1c9d5001a81c74d30f19a75218c891";


  public ResponseEntity<WeatherApi> connectToApi() {

    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<WeatherApi> exchange = restTemplate.exchange(
        "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&units=" + units
            + "&APPID=" + apiKey,
        HttpMethod.GET,
        HttpEntity.EMPTY,
        WeatherApi.class
    );
    return exchange;
  }


}
