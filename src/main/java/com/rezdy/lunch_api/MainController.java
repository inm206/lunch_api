package com.rezdy.lunch_api;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class MainController {

    @RequestMapping("/")
    public String index() {
        return "Got spring functional";
    }
}
