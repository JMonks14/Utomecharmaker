package com.tome.main.Repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tome.main.Enitities.CharSpell;

@Repository
public interface CharSpellRepo extends JpaRepository<CharSpell,Integer> {
	
	@Query(value="SELECT line_id, fk_char_id, fk_spell_id FROM char_spells WHERE fk_char_id=?1", nativeQuery=true)
	public List<CharSpell> getCharSpellIds(int char_id);

}
