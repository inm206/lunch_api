package com.rezdy.lunch_api;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class MainController {

    @RequestMapping("/")
    public String index() {
        return "Got spring functional";
    }

    @GetMapping("/lunch")
    public RecipeList lunch() {
        
    }

}
