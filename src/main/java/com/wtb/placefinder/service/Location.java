package com.wtb.placefinder.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@JsonInclude
@Getter
@Setter
public class Location {

    private String address;
    private String postalCode;
    private String countryCode;
    private String city;
    private String state;
    private String crossStreet;
}
