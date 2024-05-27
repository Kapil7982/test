package com.streamingapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.streamingapp.models.Video;
import com.streamingapp.repositories.VideoRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VideoService {

    @Autowired
    private VideoRepository videoRepository;

    public List<Video> getAllVideos() {
        return videoRepository.findAll();
    }

    public Video uploadVideo(Video video) {
        video.setUploadDate(LocalDateTime.now());
        return videoRepository.save(video);
    }

    public Video getVideoById(Long id) {
        return videoRepository.findById(id).orElseThrow(() -> new RuntimeException("Video not found"));
    }

    public List<Video> getRecentVideos() {
        return getAllVideos().stream()
                .filter(video -> video.getUploadDate().isAfter(LocalDateTime.now().minusDays(7)))
                .collect(Collectors.toList());
    }
}
