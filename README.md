# place_finder


## Technology stack
- Java8
- SpringBoot
- Maven

### Architecture
- The service is a RESTFUL stateless service and can be used in a distributed architecture such as micro service.

## building the app
- run mvn clean install -U command

## running the app
- Application is the main entry point which can be run from your favourite IDE, once it starts use a REST client e.g. IDE or Postman


## endpoint

  URL: /places/{placeId}   e.g. http://localhost:8080/places/london
  method: GET

  Response:

     {
         "place": "london",
         "venues": [
             {
                 "id": "4cb1d0e2562d224bfdb42188",
                 "name": "\"A Conversation With Oscar Wilde\" statue",
                 "verified": false,
                 "contact": {
                     "phone": "",
                     "twitter": "",
                     "facebook": ""
                 },
                 "location": {
                     "address": "Adelaide St.",
                     "postalCode": "WC2 N 5",
                     "countryCode": "GB",
                     "city": "London",
                     "state": "Greater London",
                     "crossStreet": "Btw King William IV & Duncannon"
                 },
                 "categories": [
                     {
                         "id": "507c8c4091d498d9fc8c67a9",
                         "name": "Public Art",
                         "pluralName": "Public Art"
                     }
                 ]
             },
             {
                 "id": "52c57cc911d2dba4b2352f65",
                 "name": "Paul French Patisserie",
                 "verified": false,
                 "contact": {
                     "phone": "",
                     "twitter": "",
                     "facebook": ""
                 },
                 "location": {
                     "address": "Covent Garden",
                     "postalCode": "",
                     "countryCode": "GB",
                     "city": "",
                     "state": "",
                     "crossStreet": ""
                 },
                 "categories": [
                     {
                         "id": "4bf58dd8d48988d10c941735",
                         "name": "French Restaurant",
                         "pluralName": "French Restaurants"
                     }
                 ]
             }
                 ]
             }
         ]
     }



Notes:
  - error handing needs to be more specific by catching more specific exceptions
  - an alternative service can be called or some data can be retrieved from DB in case the venue finder service is down Hystrix (wrap the call in a command and FallBack implementation to call alternative service)
  - a health check endpoint should be exposed so that it can be queried by other services and during deployment pipeline smoke testing
  - more test coverage is needed namely an integration test with various non-happy path scenarios (cucumber)
  - end to end test suite should also be added e.g. Selenium, serenity or just testing with REST calls