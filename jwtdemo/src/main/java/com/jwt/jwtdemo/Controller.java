package com.jwt.jwtdemo;


import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class Controller {



    @GetMapping("/hello")
    ResponseEntity getHello() {
        return ResponseEntity.status(HttpStatus.OK).body("Hello!!!");
    }

}
