package com.luke.wellhome.repository;

import com.luke.wellhome.domain.VideoList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VideoRepository extends JpaRepository<VideoList, Integer> {
    List<VideoList> findByLocation(String location);
}
