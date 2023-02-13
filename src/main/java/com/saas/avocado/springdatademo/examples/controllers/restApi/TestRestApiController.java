package com.saas.avocado.springdatademo.examples.controllers.restApi;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/hello-word")
public class TestRestApiController {

    Logger logger = LoggerFactory.getLogger(TestRestApiController.class);

    @GetMapping("/{id}")
    public ResponseEntity<?> getHelloWorld(@PathVariable(value = "id") Integer id) {

        Map<String, String> testMessage = new HashMap<>();
        testMessage.put("key", "hello-world");
        testMessage.put("id", String.valueOf(id));

        return new ResponseEntity<>(testMessage, HttpStatus.OK);
    }
}
