package com.Ayush.SpringSecurityExample.Controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    @GetMapping("/health-check")
    public String message(HttpServletRequest request){
        return "ok" + request.getSession().getId();
    }
}
