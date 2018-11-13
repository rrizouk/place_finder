package com.wtb.placefinder.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@JsonInclude
@Getter
@Setter
public class Contact {

    private String phone;
    private String twitter;
    private String facebook;

}
