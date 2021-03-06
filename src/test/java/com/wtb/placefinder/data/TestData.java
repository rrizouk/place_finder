package com.wtb.placefinder.data;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.Scanner;

import static org.springframework.http.HttpStatus.OK;


public class TestData {

    public static final ObjectMapper MAPPER = new ObjectMapper();

    public static ResponseEntity<JsonNode> createResponseFromJsonForVenues() {
        JsonNode tasksResponse = convertJsonToJsonNode(getVenuesResponse());
        return new ResponseEntity<>(tasksResponse, OK);
    }
    public static  String getVenuesResponse(){
        return loadFromFile("/json-response/venues.json");
    }

    private static String loadFromFile( String path) {
        return new Scanner(TestData.class.getResourceAsStream(path), "UTF-8").useDelimiter("\\A").next();
    }

    public static JsonNode convertJsonToJsonNode(String jsonResponse) {
        try {
            return MAPPER.readTree(jsonResponse.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
