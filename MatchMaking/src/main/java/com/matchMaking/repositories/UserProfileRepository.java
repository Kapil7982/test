package com.matchMaking.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.matchMaking.models.UserProfile;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long>{

	List<UserProfile> findBasicMatches(UserProfile user);

	public static List<UserProfile> findPremiumMatches(UserProfile user) {
		
		return null;
	}

}
