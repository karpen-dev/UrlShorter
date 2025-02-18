package com.karpen.karpenshorter.controllers;

import com.karpen.karpenshorter.service.UrlShortService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class UrlController {

    private UrlShortService shortService = new UrlShortService();

    @GetMapping("/create")
    public String createUrl(@RequestParam String url){
        if (url.isEmpty()){
            return "Url can't be empty";
        }

        return shortService.encodeUrl(url);
    }
}
