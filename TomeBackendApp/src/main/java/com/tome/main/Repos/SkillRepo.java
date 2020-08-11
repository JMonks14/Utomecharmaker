package com.tome.main.Repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tome.main.Enitities.Skill;

@Repository
public interface SkillRepo extends JpaRepository<Skill, Integer>{
	
	@Query(value="SELECT * FROM skills WHERE fk_tree_id=?1", nativeQuery=true)
	public List<Skill> findByTreeId(int tree_id);
	
	@Query(value="INSERT INTO char_skills(fk_char_id, fk_skill_id) VALUES (?1, ?2)", nativeQuery=true)
	public void buySkill(int char_id, int skill_id);
	
	@Query(value="SELECT fk_skill_id FROM char_skills WHERE fk_char_id=?1")
	public int[] getCharSkillIds(int char_id);

}
