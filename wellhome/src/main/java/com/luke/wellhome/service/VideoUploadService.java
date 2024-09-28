package com.luke.wellhome.service;

import com.luke.wellhome.domain.UserEntity;
import com.luke.wellhome.repository.UserRepository;
import com.luke.wellhome.repository.VideoListRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class VideoUploadService {

    private final VideoListRepository videoListRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final S3Service s3Service;


    public String createVideo(String fileName, MultipartFile imageFile) {
        if(imageFile.isEmpty()) {
            return null;
        }
        // 파일 저장 경로 지정 (서버의 "profile" 디렉토리)
        String uploadDir = "wellhome/video";
        String filePath = uploadDir + '/' + fileName + ".mp4";
        String originalFilename = imageFile.getOriginalFilename();
        logger.info("File path: {}", filePath);

        try {
            // 파일 저장
            s3Service.upload(fileName, imageFile, "mp4", uploadDir);
            return filePath;
        } catch (IOException e) {
            logger.error("IOException occurred while processing file {}", originalFilename, e);
            return null;
        } catch (Exception e) {
            logger.error("An unexpected error occurred while processing file {}", originalFilename, e);
            return null;
        }
    }

    @DeleteMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteVideo(int id) {
        try {
            s3Service.delete(videoListRepository.findById(id).getVideoTitle());
            logger.info("delete success in S3");
        }catch (Exception e){
            logger.error("An unexpected error occurred while deleting file in S3{}", id, e);
        }
    }
}
