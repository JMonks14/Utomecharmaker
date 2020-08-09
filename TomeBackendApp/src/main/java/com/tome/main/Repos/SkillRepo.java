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

}
