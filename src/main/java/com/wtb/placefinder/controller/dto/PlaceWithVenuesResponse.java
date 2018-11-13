package com.wtb.placefinder.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wtb.placefinder.service.Venue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@JsonInclude
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class PlaceWithVenuesResponse {

    @JsonProperty("place")
    private String placeId;

    @JsonProperty("venues")
    private List<Venue> venues;


}
