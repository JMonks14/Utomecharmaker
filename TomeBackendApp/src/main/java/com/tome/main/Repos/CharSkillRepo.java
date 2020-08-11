package com.tome.main.Repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tome.main.Enitities.CharSkills;

@Repository
public interface CharSkillRepo extends JpaRepository<CharSkills, Integer>{
	
		
	@Query(value="SELECT fk_char_id, fk_skill_id FROM char_skills WHERE fk_char_id=?1", nativeQuery=true)
	public List<CharSkills> getCharSkillIds(int char_id);

}
