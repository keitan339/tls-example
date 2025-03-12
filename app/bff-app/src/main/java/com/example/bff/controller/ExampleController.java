package com.example.bff.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RestController
@RequestMapping(value = "/example")
public class ExampleController {
    @PostMapping(value = "/execute")
    public ExampleResponse execute(@RequestBody ExampleRequest request) {

        final String SVC_URL = "http://svc-app:8080/example/execute";

        RestClient restClient = RestClient.create();
        ExampleResponse result =
                restClient.post().uri(SVC_URL).body(request).retrieve().body(ExampleResponse.class);
        System.out.println(result);

        return result;
    }
}
