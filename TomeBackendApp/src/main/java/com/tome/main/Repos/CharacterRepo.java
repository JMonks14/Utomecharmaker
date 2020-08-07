package com.tome.main.Repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tome.main.Enitities.Characters;
@Repository
public interface CharacterRepo extends JpaRepository<Characters, Integer>{
	
	

}
