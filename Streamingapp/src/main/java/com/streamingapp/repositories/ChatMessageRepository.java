package com.streamingapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.streamingapp.models.ChatMessage;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    List<ChatMessage> findByVideoId(Long videoId);
}