package com.tome.main.ServiceTests;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.tome.main.Enitities.Characters;
import com.tome.main.Enitities.Skill;
import com.tome.main.Repos.SkillRepo;
import com.tome.main.Services.SkillServices;

@RunWith(MockitoJUnitRunner.class)
public class SkillServiceTest {
	
	@Mock
	SkillRepo repo;
	
	@InjectMocks
	SkillServices service;
	
	Skill skill1 = new Skill(1,"skill1","a skill",1,0,0,0,0,0,false);
	Skill skill2 = new Skill(2,"skill2","another skill",1,0,0,0,0,0,false);
	Skill skill3 = new Skill(3,"skill3","a third skill",1,2,0,0,0,0,false);
	Skill skill4 = new Skill(4,"skill4","a 4th skill",1,2,3,0,0,0,true);
	
	@Test
	public void listByTreeTest() {
		List<Skill> skills = new ArrayList<Skill>();
		skills.add(skill1);
		skills.add(skill2);
		skills.add(skill3);
		skills.add(skill4);
		when(this.repo.findByTreeId(1)).thenReturn(skills);
		assertEquals(skills, service.skillsByTreeId(1));
		verify(repo, Mockito.times(1)).findByTreeId(1);
	}
	
	@Test
	public void findByIdTest() {
		Skill checked = skill1;
		Mockito.when(this.repo.findById(1)).thenReturn(Optional.of(checked));
		assertEquals(checked, service.findById(1));
		Mockito.verify(repo, Mockito.times(1)).findById(1);
	}
	
	@Test
	public void getCharSkills() {
		List<Skill> skills = new ArrayList<Skill>();
		skills.add(skill1);
		skills.add(skill2);
		skills.add(skill3);		
		when(this.repo.getCharSkills(1)).thenReturn(skills);
		assertEquals(skills, service.getCharSkills(1));
		verify(repo, Mockito.times(1)).getCharSkills(1);
	}

}
