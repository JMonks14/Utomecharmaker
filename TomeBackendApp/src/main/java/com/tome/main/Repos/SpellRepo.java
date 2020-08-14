package com.tome.main.Repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tome.main.Enitities.Skill;
import com.tome.main.Enitities.Spell;

@Repository
public interface SpellRepo extends JpaRepository<Spell,Integer>{
	
	@Query(value="INSERT INTO char_skills(fk_char_id, fk_spell_id) VALUES (?1, ?2)", nativeQuery=true)
	public void buySpell(int char_id, int skill_id);
	
	@Query(value="select spell_id, spell_name, description, prerequisite, mana_cost, type from spells s join char_spells cs on s.spell_id=cs.fk_spell_id where cs.fk_char_id=?1", nativeQuery=true)
	public List<Spell> getCharSpells(int char_id);

}
