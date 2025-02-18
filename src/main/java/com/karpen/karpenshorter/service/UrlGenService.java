package com.karpen.karpenshorter.service;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class UrlGenService {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private final SecureRandom secureRandom = new SecureRandom();

    public String generateUrl(int length){
        StringBuilder url = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = secureRandom.nextInt(CHARACTERS.length());
            url.append(CHARACTERS.charAt(index));
        }

        return url.toString();
    }
}
