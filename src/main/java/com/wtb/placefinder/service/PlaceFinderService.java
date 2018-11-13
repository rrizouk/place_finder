package com.wtb.placefinder.service;


import com.wtb.placefinder.controller.dto.PlaceWithVenuesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceFinderService {

    private final VenueFinderService venueFinderService;

    @Autowired
    public PlaceFinderService(VenueFinderService venueFinderService) {
        this.venueFinderService = venueFinderService;
    }


    public PlaceWithVenuesResponse getVenues(String placeId) {
        List<Venue> venues = venueFinderService.getVenuesForLocation(placeId);
        return new PlaceWithVenuesResponse(placeId,venues);
    }
}
