package com.anubhav.weather.service.proxy;

import com.anubhav.weather.model.CountryCityResponse;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


public class CountryCityProxyService {

  private List<String> countries;

  private List<String> cities;

  public CountryCityResponse getCountryCityResponse() {
    RestTemplate restTemplate=new RestTemplate();
    String countryCityUrl = 	"https://countriesnow.space/api/v0.1/countries";

    CountryCityResponse countryCityResponse = restTemplate.getForObject(countryCityUrl, CountryCityResponse.class);
    if (countryCityResponse == null) {
      throw new RuntimeException("CountryCity response is null");
    }
    countries = countryCityResponse.getData().stream().map(e->e.getCountry()).collect(
        Collectors.toList());
    return countryCityResponse;

  }

  public List<String> getCountries() {
    List<String> countries = getCountryCityResponse().getData().stream()
        .map(e -> e.getCountry()).collect(Collectors.toList());
    return countries;
  }

  public List<String> getCities(String country) {
    List<String> cities = getCountryCityResponse().getData().stream()
        .filter(e -> e.getCountry().equalsIgnoreCase(country)).map(e -> e.getCities()).findFirst()
        .get();
    return cities;
  }
}
