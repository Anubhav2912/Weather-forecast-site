package com.anubhav.weather.gui;

import com.anubhav.weather.model.CountryCityResponse;
import com.anubhav.weather.model.SelectedItem;
import com.anubhav.weather.model.WeatherApi;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


@Route("weather")
@StyleSheet("/style.css")
@HtmlImport("/style.html")
public class WeatherGui extends VerticalLayout {

  private String city;
  private String units = "metric";
  private final String apiKey =  "9f1c9d5001a81c74d30f19a75218c891";

  private String errorMessage = "";

  private static CountryCityResponse getCountryCityResponse() {
    RestTemplate restTemplate=new RestTemplate();
    String countryCityUrl = 	"https://countriesnow.space/api/v0.1/countries";

    CountryCityResponse countryCityResponse = restTemplate.getForObject(countryCityUrl, CountryCityResponse.class);
    if (countryCityResponse == null) {
      throw new RuntimeException("CountryCity response is null");
    }
    List<String> countries = countryCityResponse.getData().stream().map(e->e.getCountry()).collect(
        Collectors.toList());
    return countryCityResponse;

  }

  private static List<String> getCountries() {
    List<String> countries = getCountryCityResponse().getData().stream()
        .map(e -> e.getCountry()).collect(Collectors.toList());
    return countries;
  }

  private static List<String> getCities(String country) {
    List<String> cities = getCountryCityResponse().getData().stream()
        .filter(e -> e.getCountry().equalsIgnoreCase(country)).map(e -> e.getCities()).findFirst()
        .get();
    return cities;
  }


  private ResponseEntity<WeatherApi> connectToApi() {

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

  public WeatherGui() {
    Label label3DGlobe = new Label();
    Label labelHeading = new Label("WEATHER FORECAST SYSTEM");
    Label labelDeveloper = new Label("By Anubhav Kumar Singh");
    Label labelReport = new Label("WEATHER REPORT");
    Label labelError = new Label("");
    Label labelLine = new Label("============================================================");



    List<String> countries = getCountries();
    ComboBox<String> comboCountry = new ComboBox<>("Select Country");
    comboCountry.setAllowCustomValue(true);
    comboCountry.setItems(countries);

    ComboBox<String> comboCity = new ComboBox<>("Select City");

    comboCountry.getElement().addPropertyChangeListener("selectedItem", event -> {
      SelectedItem jCountry = null;
      try {
        System.out.println(event.getValue().toString());
        jCountry = new ObjectMapper().readValue(event.getValue().toString(), SelectedItem.class);
      System.out.println(jCountry.getLabel());
        List<String> cities = getCities(jCountry.getLabel());
        comboCity.setAllowCustomValue(true);
        comboCity.setItems(cities);

      } catch (Exception e) {
        System.out.println("Exception access the Property Change event" + e.getMessage());
      }
    });

    TextField textFieldSetCity = new TextField("Selected City:");

    comboCity.getElement().addPropertyChangeListener("selectedItem", event -> {
      SelectedItem jCity = null;
      try {
        jCity = new ObjectMapper().readValue(event.getValue().toString(), SelectedItem.class);
        System.out.println(jCity.getLabel());
        textFieldSetCity.setValue(jCity.getLabel());
      } catch (Exception e) {
        System.out.println("Exception access the Property Change event" + e.getMessage());
      }

    });


    Button buttonCheckWeather = new Button("Check weather!");

    Label labelCityAndCountry = new Label();
    Image weatherIcon = new Image();
    Label labelTemperature = new Label();
    Label labelPressure = new Label();
    Label labelWind = new Label();
    Label labelWindSpeed = new Label();
    Label labelWindDegrees = new Label();
    Label labelLocation = new Label();
    Label labelLocationLongitude = new Label();
    Label labelLocationLatitude = new Label();

    buttonCheckWeather.addClickShortcut(Key.ENTER);

    textFieldSetCity.getClassNames().add("textFieldSetCity");
    buttonCheckWeather.getClassNames().add("buttonCheckWeather");

    labelReport.getClassNames().add("labelReport");
    labelError.getClassNames().add("labelError");
    labelLine.getClassNames().add("labelLine");
    labelHeading.getClassNames().add("labelHeading");
    labelDeveloper.getClassNames().add("labelDeveloper");
    labelCityAndCountry.getClassNames().add("labelCityAndCountry");
    labelTemperature.getClassNames().add("labelTemperature");
    labelPressure.getClassNames().add("labelPressure");
    labelWind.getClassNames().add("labelWind");
    labelWindSpeed.getClassNames().add("labelWindSpeed");
    labelWindDegrees.getClassNames().add("labelWindDegrees");
    labelLocation.getClassNames().add("labelLocation");
    labelLocationLongitude.getClassNames().add("labelLocationLongitude");
    labelLocationLatitude.getClassNames().add("labelLocationLatitude");

    buttonCheckWeather.addClickListener(clickEvent -> {
        try {
          this.city = textFieldSetCity.getValue();
          labelCityAndCountry.setText(
              connectToApi().getBody().getName() + ", " + connectToApi().getBody().getSys()
                  .getCountry());
          weatherIcon.setSrc(
              "http://openweathermap.org/img/wn/" + connectToApi().getBody().getWeather()
                  .get(0).getIcon() + "@2x.png");
          labelTemperature.setText(
              "Temperature: " + connectToApi().getBody().getMain().getTemp().intValue()
                  + "°C");
          labelPressure.setText(
              "Pressure: " + connectToApi().getBody().getMain().getPressure() + " hPa");
          labelWind.setText("Wind:");
          labelWindSpeed.setText(
              "Speed: " + connectToApi().getBody().getWind().getSpeed() + " m/s");
          labelWindDegrees.setText(
              "Degrees: " + connectToApi().getBody().getWind().getDeg() + "°");
          labelLocation.setText("Location of city:");
          labelLocationLongitude.setText(
              "Longitude: " + connectToApi().getBody().getCoord().getLon());
          labelLocationLatitude.setText(
              "Latitude: " + connectToApi().getBody().getCoord().getLat());

          textFieldSetCity.setValue("");
        }catch (Exception e) {
          labelError.setText("Not Found");
        }
    });
    add(
        label3DGlobe,
        labelHeading,
        labelDeveloper,
        comboCountry,
        comboCity,
        textFieldSetCity,
        buttonCheckWeather,
        labelReport,
        labelLine,
        labelCityAndCountry,
        weatherIcon,
        labelTemperature,
        labelPressure,
        labelWind,
        labelWindSpeed,
        labelWindDegrees,
        labelLocation,
        labelLocationLongitude,
        labelLocationLatitude
    );
    this.setAlignItems(Alignment.CENTER);
  }
}
