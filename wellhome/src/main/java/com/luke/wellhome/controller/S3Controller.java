package com.luke.wellhome.controller;

import com.luke.wellhome.service.S3Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
public class S3Controller {

    @Autowired
    private S3Service s3Service;

    @GetMapping(path = "/data")
    public ResponseEntity<byte[]> getImage(@RequestParam String fileName) {
        return s3Service.download(fileName);
    }
}