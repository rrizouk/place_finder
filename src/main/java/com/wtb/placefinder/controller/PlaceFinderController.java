package com.wtb.placefinder.controller;

import com.wtb.placefinder.controller.dto.PlaceWithVenuesResponse;
import com.wtb.placefinder.exceptions.VenueFinderException;
import com.wtb.placefinder.service.PlaceFinderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.util.WebUtils.CONTENT_TYPE_CHARSET_PREFIX;

@Slf4j
@RestController
public class PlaceFinderController {

    private static final String APPLICATION_JSON_VALUE_UTF8 = APPLICATION_JSON_VALUE + CONTENT_TYPE_CHARSET_PREFIX + "UTF-8";
    private static final String GENERIC_ERROR_MESSAGE = "system error, please contact administrator";
    private static final String VENUE_NOT_FOUND_ERROR_MESSAGE = "venue not found, please select another place";

    private final PlaceFinderService placeFinderService;

    @Autowired
    public PlaceFinderController(PlaceFinderService placeFinderService) {
        this.placeFinderService = placeFinderService;
    }

    @RequestMapping(value = "/places/{placeId}", method = GET, produces = APPLICATION_JSON_VALUE_UTF8)
    @ResponseBody
    public PlaceWithVenuesResponse getVenues(@PathVariable String placeId){
        log.info("getting venues for placeId {}", placeId);
        PlaceWithVenuesResponse placeWithVenuesResponse =  this.placeFinderService.getVenues(placeId);
        return placeWithVenuesResponse;
    }

    @ExceptionHandler
    public ResponseEntity<VenueFinderErrorMessage> handleVenueNotFoundException(VenueFinderException exception) {
        log.warn(exception.getMessage(), exception);
        return new ResponseEntity<>(new VenueFinderErrorMessage("error_internal", VENUE_NOT_FOUND_ERROR_MESSAGE, ExceptionUtils.getStackTrace(exception)), INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler
    public ResponseEntity<VenueFinderErrorMessage> handleException(Throwable exception) {
        log.warn(exception.getMessage(), exception);
        return new ResponseEntity<>(new VenueFinderErrorMessage("error_internal", GENERIC_ERROR_MESSAGE, ExceptionUtils.getStackTrace(exception)), INTERNAL_SERVER_ERROR);
    }


}