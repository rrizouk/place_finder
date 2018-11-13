package com.wtb.placefinder.controller;


import com.google.common.collect.Lists;
import com.wtb.placefinder.controller.dto.PlaceWithVenuesResponse;
import com.wtb.placefinder.service.PlaceFinderService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;


import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
public class PlaceFinderControllerTest {

    private static final String PLACE_FINDER_URL = "/places/";
    MockMvc mockMvc;

    @InjectMocks
    private PlaceFinderController underTest;

    @Mock
    PlaceFinderService placeFinderService;

    @Before
    public void setUp()  {
       this.mockMvc = standaloneSetup(underTest).setMessageConverters(new MappingJackson2HttpMessageConverter()).build();
    }



    @Test
    public void shouldGetVenuesForGivenPlace() throws Exception {
        String placeId = "london";

        given(placeFinderService.getVenues(placeId)).willReturn(dummyPlaceWithVenuesResponse(placeId));
        mockMvc.perform(get(PLACE_FINDER_URL + placeId)).andExpect(status().isOk())
                                                        .andExpect(jsonPath("$.place").value(placeId))
                                                        .andExpect(jsonPath("$.venues").exists());
    }

    private PlaceWithVenuesResponse dummyPlaceWithVenuesResponse(String placeId) {
        return PlaceWithVenuesResponse.builder().placeId(placeId).venues(Lists.newArrayList()).build();
    }
}