package com.tome.main.ControllerTests;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tome.main.Enitities.CharSkills;
import com.tome.main.Enitities.Characters;
import com.tome.main.Enitities.Player;
import com.tome.main.Enitities.Skill;
import com.tome.main.Repos.CharSkillRepo;
import com.tome.main.Repos.CharacterRepo;
import com.tome.main.Repos.PlayerRepo;
import com.tome.main.Repos.SkillRepo;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CharSkillControllerTests {
	
	@Autowired
	private MockMvc mock;
	
	@Autowired
	CharSkillRepo CSrepo;
	
	@Autowired
	private SkillRepo repo;
	
	@Autowired
	private PlayerRepo Prepo;
	
	@Autowired
	private CharacterRepo Crepo;
	
	@Autowired
	private ObjectMapper mapper = new ObjectMapper();
	
	private Player testplayer = new Player("Bob", "Smith", "Smithy", "neilthebaby",0);
	private Characters testChar = new Characters("Steve","human","Ardglass","boring",1,true,3,3,0,0,0,0,0);
	int Pid;
	int Cid;
	
	Skill skill1 = new Skill(1,"skill1","a skill",1,0,0,0,0,0,false);
	Skill skill2 = new Skill(2,"skill2","another skill",1,0,0,0,0,0,false);
	Skill skill3 = new Skill(3,"skill3","a third skill",1,0,0,0,0,0,false);
	Skill skill4 = new Skill(4,"skill4","a 4th skill",1,0,0,0,0,0,true);
	List<Skill> skills = new ArrayList<>();
	
	CharSkills charskill1 = new CharSkills(4,1,1);
	CharSkills charskill2 = new CharSkills(1,1,1);
	CharSkills charskill3 = new CharSkills(2,1,2);
	CharSkills charskill4 = new CharSkills(3,1,3);
	List<CharSkills> charskills = new ArrayList<CharSkills>();
	
	@Before
	public void init() {
		CSrepo.deleteAll();
		Crepo.deleteAll();
		Prepo.deleteAll();
		skills=repo.findByTreeId(1);
		repo.deleteAll(skills);
		testplayer=Prepo.save(testplayer);
		Pid=testplayer.getPlayer_id();
		testChar.setFk_player_id(Pid);
		testChar=Crepo.save(testChar);
		Cid=testChar.getChar_id();
		skill1=repo.save(skill1);
		skill2=repo.save(skill2);
		skill3=repo.save(skill3);
		skill4=repo.save(skill4);
		skills.clear();
		skills.add(skill1);
		skills.add(skill2);
		skills.add(skill3);
		skills.add(skill4);
		charskill1.setFk_char_id(Cid);
		charskill1.setFk_skill_id(skill1.getSkill_id());
		charskill1=CSrepo.save(charskill1);
		charskill2.setFk_char_id(Cid);
		charskill2.setFk_skill_id(skill2.getSkill_id());
		charskill2=CSrepo.save(charskill2);
		charskill3.setFk_char_id(Cid);
		charskill3.setFk_skill_id(skill3.getSkill_id());
		charskill3=CSrepo.save(charskill3);
		charskill4.setFk_char_id(Cid);
		charskill4.setFk_skill_id(skill4.getSkill_id());
		charskill4=CSrepo.save(charskill4);	
	}
	@Test
	public void testBuy() throws JsonProcessingException, Exception {
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.POST, "/charskills/buy");
		
		mockRequest.contentType(MediaType.APPLICATION_JSON)
			.content(this.mapper.writeValueAsString(charskill1))
			.accept(MediaType.APPLICATION_JSON);
	
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isCreated();
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.mapper.writeValueAsString(charskill1));
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
	}
	
	@Test
	public void testCharSpellIds() throws JsonProcessingException, Exception {
		charskills.add(charskill1);
		charskills.add(charskill2);
		charskills.add(charskill3);
		charskills.add(charskill4);
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.GET, "/charskills/byid/" + Cid);
		
		mockRequest.contentType(MediaType.APPLICATION_JSON)
			.content(this.mapper.writeValueAsString(charskills))
			.accept(MediaType.APPLICATION_JSON);
	
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.mapper.writeValueAsString(charskills));
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
	}
	
	@Test
	public void testReset() throws JsonProcessingException, Exception {
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.DELETE, "/charskills/reset/" + Cid);
	
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isNoContent();
		this.mock.perform(mockRequest).andExpect(matchStatus);
	}
	
	@After
	public void erase() {
		CSrepo.deleteAll();
	}

}
