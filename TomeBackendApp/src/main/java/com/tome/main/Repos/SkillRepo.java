package com.tome.main.Repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tome.main.Enitities.CharSkillGroup;
import com.tome.main.Enitities.Skill;

@Repository
public interface SkillRepo extends JpaRepository<Skill, Integer>{
	
	@Query(value="SELECT * FROM skills WHERE fk_tree_id=?1", nativeQuery=true)
	public List<Skill> findByTreeId(int tree_id);
	
	@Query(value="select skill_id, skill_name, description, fk_tree_id, prerequisite_1, prerequisite_2, prerequisite_3, prerequisite_4, prerequisite_5, is_multibuy from skills s join char_skills cs on s.skill_id=cs.fk_skill_id where cs.fk_char_id=?1", nativeQuery=true)
	public List<Skill> getCharSkills(int char_id);
	
	

}
