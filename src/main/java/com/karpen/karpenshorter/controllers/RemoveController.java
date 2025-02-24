package com.karpen.karpenshorter.controllers;

import com.karpen.karpenshorter.service.UrlShortService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/delete")
public class RemoveController {

    private UrlShortService shortService = new UrlShortService();

    @GetMapping
    public String delete(@RequestParam String r){
        if (r.isEmpty()){
            return "Error, url can't be empty!";
        }

        if (shortService.deleteUrl(r)){
            return "Done, url " + r + " deleted!";
        } else {
            return "Error deleting url!";
        }
    }
}
