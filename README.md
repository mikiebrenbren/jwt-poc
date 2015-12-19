# JWT Proof of concept
Prototype implementation of JWT with a RESTful webservice

### Running Application

This is a gradle spring boot app,
run these commands at the project root level

```$ gradle clean build```

```$ java -jar build/libs/gradle-sb-archtype-1.0.0.jar```

#### Postman

Download Postman if you do not have it already (https://chrome.google.com/webstore/detail/postman/fhbjgbiflinjbdggehcddcbncdddomop?hl=en)

import file named ```JWTCollection.json.postman_collection```, found at the root level of the project into Postman

Select the ```/login``` request in the imported Postman collection.  Click on the 'Body' tab and select 'x-www-form-encoded' radio button.  There should be an option to input a key and value, be sure to enter (if they are not already there)

key:user | value: username

key:password | value: pw1234

NOTE: The value fields are hardcoded in the app so it doesnt matter what you enter for those, just make sure the keys(user, password) are entered correctly!

Confirm the request method is a POST and send the request.  

Your response headers should contain a header that looks similar to this...

```X-AUTH-TOKEN → eyJhbGciOiJIUzUxMiJ9.eyJleHAiOjE0NTA1NjI4NDYsInN1YiI6InVzZXJuYW1lIn0.yFFmUHqLEoauuIAexyGYojuQiUbxzeOB7QiRG8vR_e0yizez1uXqI5vUNJwF_iAZTWKSQVdBwi6R88GF8FihgA```

THAT IS YOUR JWT!! CONGRATS.  You can now make subsequent requests to other resources in the app by adding this header to your other requests.  Notice the other resource ```/hello``` in the Postman collection.  Add the JWT header to the request header in your request to ```/hello``` and see the results.

### Final notes:
The JWT libraries that are is being used in this app is this one, https://github.com/jwtk/jjwt

Stole most of this work from this blog post
http://technicalrex.com/2015/02/20/stateless-authentication-with-spring-security-and-jwt/

Also we were concerned about JWT expiration, it turns out there is an expiration you can set on it in the jjwt api already.  See source for more information.
