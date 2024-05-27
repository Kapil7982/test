package com.streamingapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.streamingapp.models.Video;

public interface VideoRepository extends JpaRepository<Video, Long> {
    
}
