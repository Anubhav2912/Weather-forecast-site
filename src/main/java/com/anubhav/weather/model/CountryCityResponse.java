package com.anubhav.weather.model;

import java.util.List;

public class CountryCityResponse{
  private Boolean error;
  private String msg;
  private List<CountryCity> data;

  public Boolean getError() {
    return error;
  }

  public void setError(Boolean error) {
    this.error = error;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public List<CountryCity> getData() {
    return data;
  }

  public void setData(List<CountryCity> data) {
    this.data = data;
  }
}

