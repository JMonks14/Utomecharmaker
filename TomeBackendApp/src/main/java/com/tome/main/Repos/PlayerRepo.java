package com.tome.main.Repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tome.main.Enitities.Player;

@Repository
public interface PlayerRepo extends JpaRepository<Player, Integer> {

}
