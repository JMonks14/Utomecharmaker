package com.tome.main.Repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tome.main.Enitities.Spell;

@Repository
public interface SpellRepo extends JpaRepository<Spell,Integer>{
	
		
	@Query(value="select * from spells where spell_id > 0", nativeQuery=true)
	public List<Spell> listAll();

}
