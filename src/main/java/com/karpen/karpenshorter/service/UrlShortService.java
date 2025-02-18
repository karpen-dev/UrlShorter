package com.karpen.karpenshorter.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.karpen.karpenshorter.models.Url;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UrlShortService {
    private List<Url> urls = new ArrayList<>();
    private UrlGenService genService = new UrlGenService();

    private ObjectMapper objectMapper = new ObjectMapper();

    public String encodeUrl(String orgUrl){
        String shortUrl = "https://karpens.ru?r=" + genService.generateUrl(10);

        loadUrls();

        Url url = new Url();
        url.setOriginalUrl(orgUrl.replace("https://", "").replace("http://", "").trim());
        url.setShortUrl(shortUrl);

        if (findUrl(shortUrl)){
            return "Error, try again";
        }

        urls.add(url);

        saveUrls();

        return shortUrl;
    }

    public String getOrgUrl(String shortUrl) {
        loadUrls();

        return urls.stream()
                .filter(url -> url.getShortUrl().equals(shortUrl))
                .map(Url::getOriginalUrl)
                .findFirst()
                .orElse("Error url searching");
    }

    private boolean findUrl(String shortUrl) {
        loadUrls();

        return urls.stream().anyMatch(url -> url.getShortUrl().equals(shortUrl));
    }


    private void saveUrls(){
        try {
            objectMapper.writeValue(new File("urls.json"), urls);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void loadUrls(){
        try {
            urls = objectMapper.readValue(new File("urls.json"), objectMapper.getTypeFactory().constructCollectionType(List.class, Url.class));
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
