package com.Ayush.ecom_project.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckerController {

    @GetMapping("/health-checker")
    public String message(){
        return "OK .. ✨✨";
    }
}
