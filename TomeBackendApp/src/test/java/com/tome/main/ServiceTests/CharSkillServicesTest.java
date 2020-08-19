package com.tome.main.ServiceTests;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.tome.main.Enitities.CharSkills;
import com.tome.main.Repos.CharSkillRepo;
import com.tome.main.Services.CharSkillServices;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CharSkillServicesTest {
	
	@MockBean
	CharSkillRepo repo;
	
	@Autowired
	CharSkillServices service;
	
	CharSkills charskill1 = new CharSkills(1,1);
	CharSkills charskill2 = new CharSkills(1,1,1);
	CharSkills charskill3 = new CharSkills(2,1,2);
	CharSkills charskill4 = new CharSkills(3,1,3);
	List<CharSkills> charskills = new ArrayList<CharSkills>();
	
	@Test
	public void charSkillIdsTest() {
		charskills.add(charskill2);
		charskills.add(charskill3);
		charskills.add(charskill4);
		when(this.repo.getCharSkillIds(1)).thenReturn(charskills);
		assertEquals(charskills, this.service.charSkillIds(1));
		verify(repo, Mockito.times(1)).getCharSkillIds(1);
	}
	
	@Test
	public void buySkillTest() {
		when(this.repo.save(charskill1)).thenReturn(charskill2);
		assertEquals(charskill2, this.service.buySkill(charskill1));
		verify(repo, Mockito.times(1)).save(charskill1);
	}
	
	@Test
	public void resetTest() {
		doNothing().doThrow(new RuntimeException()).when(repo).reset(1);
		this.service.reset(1);;
		verify(repo, Mockito.times(1)).reset(1);
	}

}
