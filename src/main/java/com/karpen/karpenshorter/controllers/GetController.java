package com.karpen.karpenshorter.controllers;

import com.karpen.karpenshorter.service.UrlShortService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetController {

    private UrlShortService shortService = new UrlShortService();

    @GetMapping
    public String getUrl(@RequestParam String r){
        if (r.isEmpty()){
            return "Var can't be empty";
        }

        String shortUrl = "https://karpens.ru?r=" + r;
        String orgUrl = "redirect://" + shortService.getOrgUrl(shortUrl);

        return orgUrl;
    }

}
