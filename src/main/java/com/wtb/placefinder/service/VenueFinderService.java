package com.wtb.placefinder.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.wtb.placefinder.gateway.VenueFinderGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VenueFinderService {

    private final VenueFinderGateway venueFinderGateway;

    @Autowired
    public VenueFinderService(VenueFinderGateway venueFinderGateway) {
        this.venueFinderGateway = venueFinderGateway;
    }

    public List<Venue> getVenuesForLocation(String placeId) {
        JsonNode venuesResult = venueFinderGateway.getVenues(placeId);
        return getVenues(venuesResult);
    }

    private List<Venue> getVenues(JsonNode jsonNode) {
        List<Venue> venues = new ArrayList<>();
        for(JsonNode venueNode : jsonNode.findPath("response").findPath("venues")){
            Venue venue = new Venue();
            venue.setId(venueNode.findPath("id").asText());
            venue.setName(venueNode.findPath("name").asText());
            venue.setVerified(venueNode.findPath("verified").asBoolean());
            venue.setLocation(createLocation(venueNode));
            venue.setContact(createContact(venueNode));
            venue.setCategories(createCategories(venueNode));
            venues.add(venue);
        }
        return venues;
    }

    private List<Category> createCategories(JsonNode venueNode) {
        List<Category> categories = new ArrayList<>();
        for(JsonNode categoryNode : venueNode.findPath("categories")){
            Category category = new Category();
            category.setId(categoryNode.findPath("id").asText());
            category.setName(categoryNode.findPath("name").asText());
            category.setPluralName(categoryNode.findPath("pluralName").asText());
            categories.add(category);
        }
        return categories;
    }

    private Contact createContact(JsonNode venueNode) {
        Contact contact = new Contact();
        contact.setPhone(venueNode.findPath("contact").findPath("phone").asText());
        contact.setFacebook(venueNode.findPath("contact").findPath("facebook").asText());
        contact.setTwitter(venueNode.findPath("contact").findPath("twitter").asText());
        return contact;
    }

    private Location createLocation(JsonNode venueNode) {
        Location location = new Location();
        location.setAddress(venueNode.findPath("location").findPath("address").asText());
        location.setState(venueNode.findPath("location").findPath("state").asText());
        location.setCity(venueNode.findPath("location").findPath("city").asText());
        location.setCountryCode(venueNode.findPath("location").findPath("cc").asText());
        location.setPostalCode(venueNode.findPath("location").findPath("postalCode").asText());
        location.setCrossStreet(venueNode.findPath("location").findPath("crossStreet").asText());
        return location;
    }
}
