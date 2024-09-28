package com.luke.wellhome.service;


import com.luke.wellhome.domain.VideoList;
import com.luke.wellhome.dto.SuccessResponse;
import com.luke.wellhome.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VideoService {

    private final VideoRepository videoRepository;

    public SuccessResponse<List<VideoList>> getVideoByLocation(String location){
        List<VideoList> videoListList = videoRepository.findByLocation(location);
        return new SuccessResponse<>(videoListList, 200);
    }
}
