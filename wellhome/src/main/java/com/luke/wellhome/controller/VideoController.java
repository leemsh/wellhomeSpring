package com.luke.wellhome.controller;

import com.luke.wellhome.domain.VideoList;
import com.luke.wellhome.dto.HttpHeaderMaker;
import com.luke.wellhome.dto.SuccessResponse;
import com.luke.wellhome.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @PostMapping(value="/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SuccessResponse<String>> createVideo(
            @RequestPart VideoList videoList,
            @RequestPart MultipartFile file) {

        SuccessResponse<String> response = videoService.createVideo(videoList, file);
        return new ResponseEntity<>(response, HttpHeaderMaker.makeHeader(), response.getStatus());
    }


    @DeleteMapping(value="/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SuccessResponse<String>> deleteVideo(@RequestParam int id){
        SuccessResponse<String> response = videoService.deleteVideo(id);
        return new ResponseEntity<>(response, HttpHeaderMaker.makeHeader(), response.getStatus());
    }
}
