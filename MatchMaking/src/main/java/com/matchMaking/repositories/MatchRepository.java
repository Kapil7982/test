package com.matchMaking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.matchMaking.models.Match;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long>{

	public void saveMatch(Match match);

}
