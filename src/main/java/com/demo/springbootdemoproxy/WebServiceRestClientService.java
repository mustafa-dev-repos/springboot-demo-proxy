package com.demo.springbootdemoproxy;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

//@Service
public class WebServiceRestClientService {

    private static final String REST_URI = "http://localhost:8082/spring-jersey/resources/employees";
    private Client client = ClientBuilder.newClient();

    public String createJsonService(String restURI, String bodyMessage) {
        return client.target(restURI)
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(bodyMessage, MediaType.APPLICATION_JSON), String.class)
                ;
    }

    public String getJsonService(String restURI) {
        return client.target(restURI)
                .request(MediaType.APPLICATION_JSON)
                .get(String.class);
    }
}