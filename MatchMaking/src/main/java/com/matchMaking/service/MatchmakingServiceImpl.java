package com.matchMaking.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.matchMaking.models.Match;
import com.matchMaking.models.UserProfile;
import com.matchMaking.repositories.MatchRepository;
import com.matchMaking.repositories.UserProfileRepository;

@Service
public class MatchmakingServiceImpl implements MatchmakingService {

    
    private UserProfileRepository userProfileRepository;
    private MatchRepository matchRepository;

    
    public MatchmakingServiceImpl(UserProfileRepository userProfileRepository, MatchRepository matchRepository) {
        this.userProfileRepository = userProfileRepository;
        this.matchRepository = matchRepository;
    }

    @Override
    public List<Match> findMatches(long userId) {
        
        UserProfile user = userProfileRepository.getById(userId);
        if (user == null) {
            
            return new ArrayList<>();
        }

        List<Match> matches = new ArrayList<>();

        
        List<UserProfile> basicMatches = userProfileRepository.findBasicMatches(user);
        for (UserProfile match : basicMatches) {
            int compatibilityScore = calculateCompatibility(user, match);
            matches.add(new Match(userId, match.getUserId(), compatibilityScore));
        }

        if (user.isPremium()) {
            List<UserProfile> premiumMatches = UserProfileRepository.findPremiumMatches(user);
            for (UserProfile match : premiumMatches) {
                int compatibilityScore = calculateCompatibility(user, match);
                matches.add(new Match(userId, match.getUserId(), compatibilityScore));
            }
        }

        for (Match match : matches) {
            matchRepository.saveMatch(match);
        }

        return matches;
    }

    private int calculateCompatibility(UserProfile user1, UserProfile user2) {
        int compatibilityScore = 0;

        int ageDifference = Math.abs(user1.getAge() - user2.getAge());
        compatibilityScore += 100 - ageDifference; // Higher age similarity results in a higher score

        if (user1.getPreferences().containsKey("gender") && user2.getGender().equals(user1.getPreferences().get("gender"))) {
            compatibilityScore += 50; 
        }

        for (String key : user1.getPreferences().keySet()) {
            if (user2.getPreferences().containsKey(key) && user2.getPreferences().get(key).equals(user1.getPreferences().get(key))) {
                compatibilityScore += 20; 
            }
        }

        return compatibilityScore;
    }

}

