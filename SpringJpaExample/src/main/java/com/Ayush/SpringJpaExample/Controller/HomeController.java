package com.Ayush.SpringJpaExample.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @ResponseBody
    @RequestMapping("/")
    public String greet(){
        return "Welcome Ayush !";
    }

    @ResponseBody
    @RequestMapping("/about")
    public String about(){
        return "About section";
    }
}
