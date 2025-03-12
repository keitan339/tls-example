package com.example.svc.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/example")
public class ExampleController {
    @PostMapping(value = "/execute")
    public ExampleResponse execute(@RequestBody ExampleRequest request) {
        System.out.println("svc-example");
        ExampleResponse response = new ExampleResponse();
        response.setItem1("svc+" + request.getItem1());
        return response;
    }
}
