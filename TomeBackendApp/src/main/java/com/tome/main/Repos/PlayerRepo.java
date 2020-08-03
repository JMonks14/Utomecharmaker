package com.tome.main.Repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tome.main.Enitities.Player;

public interface PlayerRepo extends JpaRepository<Player, Integer> {

}
