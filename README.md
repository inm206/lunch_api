# Lunch API

This is an API that gives recipes at the /lunch endpoint.
 
 Recipes based on the ingredients
 available in the fridge and
 the
 provided recipes, both received from different API endpoints.
 
 ## Running Instructions

 Run unit tests with Maven:
 
 ```./mvnw -Dtest=LunchApiApplicationTests test```
 
 Run integration tests with Maven:
  
  ```./mvnw -Dtest=LunchApiIntegrationTests test```

 Run app with Maven:
 
 ```./mvnw spring-boot:run```
 ### OR 
 ### Docker container setup
 #### Build container:
 
 ```docker build -t lunchapi .```
 
 #### Run container:
 
 ```docker run -e "SPRING_PROFILES_ACTIVE=prod" -p 8080:8080 -t lunchapi```
 
 ## Client testing
 
 Perform a GET request to localhost:8080/lunch using an API tool such as Postman or Paw.
 ### OR 
 ### Using cURL:
 
 ```curl localhost:8080/lunch```
 
 
 Side note: Ingredient and recipe endpoints changed to http due to a java SSLHandshakeException
  on my machine, and time constraint after not working with imported Let's Encrypt certificate to
   Truststore