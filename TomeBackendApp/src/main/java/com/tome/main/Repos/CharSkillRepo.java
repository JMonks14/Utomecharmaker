package com.tome.main.Repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tome.main.Enitities.CharSkills;

@Repository
public interface CharSkillRepo extends JpaRepository<CharSkills, Integer>{
	
		
	@Query(value="SELECT line_id, fk_char_id, fk_skill_id FROM char_skills WHERE fk_char_id=?1", nativeQuery=true)
	public List<CharSkills> getCharSkillIds(int char_id);
	
	@Transactional
	@Modifying
	@Query(value="DELETE FROM char_skills where fk_char_id=?1",nativeQuery=true)
	public void reset(int fk_char_id);
	
}
