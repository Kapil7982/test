package com.matchMaking.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Match {
    
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long matchId;
    private long user1Id;
    private long user2Id;
    private int compatibilityScore;

    public Match(long userId, Long userId2, int compatibilityScore2) {
		// TODO Auto-generated constructor stub
	}
}

