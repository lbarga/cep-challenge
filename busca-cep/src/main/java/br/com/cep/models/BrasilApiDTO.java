package br.com.cep.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BrasilApiDTO {
    @JsonProperty("cep")
    private String cep;

    @JsonProperty("state")
    private String state;

    @JsonProperty("city")
    private String city;

    @JsonProperty("neighborhood")
    private String neighborhood;

    @JsonProperty("street")
    private String street;

    @JsonProperty("service")
    private String service;

    @JsonProperty("location")
    private Location location;

    public String getCep() {
        return cep;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public String getStreet() {
        return street;
    }

    public String getService() {
        return service;
    }

    public Location getLocation() {
        return location;
    }
}
