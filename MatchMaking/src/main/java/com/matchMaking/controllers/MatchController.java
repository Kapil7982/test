package com.matchMaking.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.matchMaking.exceptions.UserNotFoundException;
import com.matchMaking.models.Match;
import com.matchMaking.models.UserProfile;
import com.matchMaking.repositories.UserProfileRepository;
import com.matchMaking.service.MatchmakingService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MatchController {

    @Autowired
    private MatchmakingService matchmakingService;
    
    @Autowired
    private UserProfileRepository userRepository;

    @GetMapping("/match/{userId}")
    public List<Match> getMatches(@PathVariable long userId) {
        return matchmakingService.findMatches(userId);
    }

    @PostMapping("/match")
    public void initiateMatchmaking(@RequestParam long userId) {

        matchmakingService.findMatches(userId);
    }

    @PutMapping("/user/{userId}/premium")
    public void togglePremium(@PathVariable long userId, @RequestParam boolean premium) throws UserNotFoundException {
       
        UserProfile user = userRepository.getById(userId);
        if (user != null) {
            user.setPremium(premium);
            userRepository.save(user); 
        } else {
            
            throw new UserNotFoundException("User with ID " + userId + " not found");
        }
    }
}

