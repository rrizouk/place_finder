package com.wtb.placefinder.gateway;


import com.fasterxml.jackson.databind.JsonNode;
import com.wtb.placefinder.exceptions.VenueFinderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class VenueFinderGateway {


    private final RestTemplate restTemplate;

    @Value("${fourSquareVenueFinder.baseUrl}")
    private String venuesFinderUrl;

    @Value("${fourSquareVenueFinder.clientId}")
    private String clientId;

    @Value("${fourSquareVenueFinder.clientSecret}")
    private String clientSecret;


    @Autowired
    public VenueFinderGateway(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public JsonNode getVenues(String placeId){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(venuesFinderUrl)
                .queryParam("near", placeId)
                .queryParam("client_id", clientId)
                .queryParam("client_secret", clientSecret)
                .queryParam("v", getVersion());

        HttpEntity<?> entity = new HttpEntity<>(headers);

        HttpEntity<JsonNode> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, JsonNode.class);
        throwExceptionIfNotOk(response);
        return response.getBody();
    }


    private void throwExceptionIfNotOk(HttpEntity<JsonNode> response) {
        if(response.getBody().findPath("meta").findPath("code").intValue() != HttpStatus.OK.value()){
            throw new VenueFinderException("default message " + response.getBody().findPath("meta").findPath("code"));
        }
    }

    public String getVersion() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(new Date());
    }
}
