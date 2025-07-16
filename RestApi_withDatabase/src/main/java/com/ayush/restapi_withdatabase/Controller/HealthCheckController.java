package com.ayush.restapi_withdatabase.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    @GetMapping("/health-check")
    public String message(){
        return "ok! ..";
    }
}
