package com.example.demo.service;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockserver.client.MockServerClient;
import org.mockserver.matchers.Times;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.mockserver.springtest.MockServerTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import io.netty.handler.codec.http.HttpMethod;

@SpringBootTest
@ActiveProfiles("test")
@MockServerTest("service.test-api.url: http://localhost:${mockServerPort}/path/to/endpoint")
public class ExampleServiceTest {
    
    @Autowired
    private ExampleService exampleService;

    private static MockServerClient mockServer;

    @BeforeEach
    void setup() {
        mockServer.reset();
    }

    @Test
    void shouldCallApi() {
        HttpRequest req = getReq();
        
        exampleService.callApi();

        mockServer.verify(req);
    }

    private HttpRequest getReq() {
        final HttpRequest req = HttpRequest.request()
                .withMethod(HttpMethod.GET.toString())
                .withPath("/path/to/endpoint");

        mockServer.when(req, Times.once())
            .respond(HttpResponse.response().withStatusCode(HttpStatus.OK.value())
            .withBody("Success"));

        return req;
    }
}
