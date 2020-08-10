package com.tome.main.Repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tome.main.Enitities.Spell;

@Repository
public interface SpellRepo extends JpaRepository<Spell,Integer>{
	
	@Query(value="INSERT INTO char_skills(fk_char_id, fk_spell_id) VALUES (?1, ?2)", nativeQuery=true)
	public void buySpell(int char_id, int skill_id);

}
