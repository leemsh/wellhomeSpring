package com.luke.wellhome.service;


import com.luke.wellhome.domain.VideoList;
import com.luke.wellhome.dto.SuccessResponse;
import com.luke.wellhome.repository.VideoListRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VideoService {

    private final VideoUploadService videoUploadService;
    private final VideoListRepository videoListRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public SuccessResponse<List<VideoList>> getVideoByLocation(String location){
        List<VideoList> videoListList = videoListRepository.findByLocation(location);
        return new SuccessResponse<>(videoListList, 200);
    }

    public SuccessResponse<List<VideoList>> getVideoList(){
        List<VideoList> videoListList = videoListRepository.findAll();
        return new SuccessResponse<>(videoListList, 200);
    }

    public SuccessResponse<String> createVideo(VideoList video, MultipartFile file){
        try {
            String filePath = videoUploadService.createVideo(video.getVideoTitle(), file);
            video.setFilePath(filePath);
            video.setConnection(0);
            videoListRepository.save(video);
        }catch (Exception e){
            logger.error(e.getMessage());
            return new SuccessResponse<>("create failed", 500);
        }
        return new SuccessResponse<>("create Successfully", 200);
    }

    public SuccessResponse<String> deleteVideo(int id){
        try{
            videoUploadService.deleteVideo(id);
            videoListRepository.deleteById(id);
        }catch (Exception e){
            logger.error(e.getMessage());
            return new SuccessResponse<>("delete failed", 500);
        }
        return new SuccessResponse<>("delete Successfully", 200);
    }
}
