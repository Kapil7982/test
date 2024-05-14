package com.matchMaking.service;

import java.util.List;

import com.matchMaking.models.Match;

public interface MatchmakingService {
    List<Match> findMatches(long userId);
}

