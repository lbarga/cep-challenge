package br.com.buscar_cep.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ApiCepDTO {
    @JsonProperty("code")
    private String code;

    @JsonProperty("state")
    private String state;

    @JsonProperty("city")
    private String city;

    @JsonProperty("district")
    private String district;

    @JsonProperty("address")
    private String address;

    @JsonProperty("status")
    private String status;
    @JsonProperty("ok")
    private String ok;
    @JsonProperty("statusText")
    private String statusText;

    public String getCode() {
        return code;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public String getDistrict() {
        return district;
    }

    public String getAddress() {
        return address;
    }

    public String getStatus() {
        return status;
    }

    public String getOk() {
        return ok;
    }

    public String getStatusText() {
        return statusText;
    }
}
