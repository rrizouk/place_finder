package com.wtb.placefinder.service;

import com.google.common.collect.Lists;
import com.wtb.placefinder.controller.dto.PlaceWithVenuesResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.*;
import static org.mockito.BDDMockito.*;


@RunWith(MockitoJUnitRunner.class)
public class PlaceFinderServiceTest {

    @InjectMocks
    private PlaceFinderService underTest;

    @Mock
    private VenueFinderService venueFinderService;

    @Test
    public void shouldBeAbleToGetVenuesForPlace(){
        String placeId = "london";

        given(venueFinderService.getVenuesForLocation(placeId)).willReturn(dummyVenuesList());

        PlaceWithVenuesResponse response = underTest.getVenues(placeId);

        assertNotNull(response);
        assertThat(response.getPlaceId(), is(placeId));
    }

    private List<Venue> dummyVenuesList() {
        Venue venue1 = createDummyVenue("id1","name1");
        Venue venue2 = createDummyVenue("id2","name2");
        return Lists.newArrayList(venue1,venue2);
    }

    private Venue createDummyVenue(String id, String name) {
        Venue venue = new Venue();
        venue.setId(id);
        venue.setName(name);
        return venue;
    }
}