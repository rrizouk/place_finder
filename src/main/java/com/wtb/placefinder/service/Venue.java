package com.wtb.placefinder.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@JsonInclude
@Getter
@Setter
public class Venue {

    private String id;
    private String name;
    private boolean verified;

    private Contact contact;
    private Location location;
    private List<Category> categories;

}
