package com.example.rk.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/example")
@RequiredArgsConstructor
public class ExampleController {

    @PostMapping(value = "/execute")
    public ExampleResponse execute(@RequestBody ExampleRequest request) {
        System.out.println("rk-example");
        ExampleResponse response = new ExampleResponse();
        response.setItem1("rk + " + request.getItem1());
        return response;
    }
}

