package com.luke.wellhome.controller;

import com.luke.wellhome.domain.VideoList;
import com.luke.wellhome.dto.HttpHeaderMaker;
import com.luke.wellhome.dto.SuccessResponse;
import com.luke.wellhome.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/video")
@RequiredArgsConstructor
public class VideoController {

    private final VideoService videoService;

    @GetMapping(value="/getbylocation", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SuccessResponse<List<VideoList>>> getVideoByLocation(@RequestParam("location") String location) {
        SuccessResponse<List<VideoList>> response = videoService.getVideoByLocation(location);
        return new ResponseEntity<>(response, HttpHeaderMaker.makeHeader(), response.getStatus());
    }

}
