package com.wtb.placefinder.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.wtb.placefinder.data.TestData;
import com.wtb.placefinder.gateway.VenueFinderGateway;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.*;


@RunWith(MockitoJUnitRunner.class)
public class VenueFinderServiceTest {

    @InjectMocks
    private VenueFinderService underTest;

    @Mock
    private VenueFinderGateway venueFinderGateway;

    @Test
    public void shouldGetVenuesForLocation(){
        String placeId = "paris";

        given(venueFinderGateway.getVenues(placeId)).willReturn(dummyVenuesResponse());
        List<Venue> result = underTest.getVenuesForLocation(placeId);

        assertThat(result.size(), is(2));

    }

    private JsonNode dummyVenuesResponse() {
        return TestData.createResponseFromJsonForVenues().getBody();
    }

}