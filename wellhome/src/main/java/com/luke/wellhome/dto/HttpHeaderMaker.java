package com.luke.wellhome.dto;


import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class HttpHeaderMaker {
    public static HttpHeaders makeHeader(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
