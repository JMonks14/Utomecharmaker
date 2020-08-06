package com.tome.main.Repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tome.main.Enitities.Player;

@Repository
public interface PlayerRepo extends JpaRepository<Player, Integer> {
	
	@Query(value="SELECT * FROM players ORDER BY player_id DESC LIMIT 1", nativeQuery=true)
	public Player findLatest ();

}
