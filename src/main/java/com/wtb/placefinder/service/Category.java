package com.wtb.placefinder.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@JsonInclude
@Getter
@Setter
public class Category {

    private String id;
    private String name;
    private String pluralName;
}
