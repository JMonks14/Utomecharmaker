package com.tome.main.Repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tome.main.Enitities.SkillTree;

@Repository
public interface SkillTreeRepo extends JpaRepository<SkillTree,Integer>{

}
