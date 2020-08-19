package com.tome.main.ControllerTests;

import java.util.ArrayList;
import java.util.List;

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
public class SkillControllerTest {
	
	@Autowired
	private MockMvc mock;
	
	@Autowired
	private SkillRepo repo;
	
	@Autowired
	CharSkillRepo CSrepo;
	
	@Autowired
	private PlayerRepo Prepo;
	
	@Autowired
	private CharacterRepo Crepo;
	
	@Autowired
	private ObjectMapper mapper = new ObjectMapper();
	
	Skill skill1 = new Skill(1,"skill1","a skill",1,0,0,0,0,0,false);
	Skill skill2 = new Skill(2,"skill2","another skill",1,0,0,0,0,0,false);
	Skill skill3 = new Skill(3,"skill3","a third skill",1,0,0,0,0,0,false);
	Skill skill4 = new Skill(4,"skill4","a 4th skill",1,0,0,0,0,0,true);
	List<Skill> skills = new ArrayList<>();
	private Characters testChar = new Characters("Steve","human","Ardglass","boring",1,true,3,3,1,0,0,0,0);
	private Player testplayer = new Player("Bob", "Smith", "Smithy", "neilthebaby",0);
	int Pid;
	int Cid;
	
	@Before
	public void init () {
		CSrepo.deleteAll();
		skills = repo.findByTreeId(1);
		repo.deleteInBatch(skills);
		skill1=repo.save(skill1);
		skill2=repo.save(skill2);
		skill3=repo.save(skill3);
		skill4=repo.save(skill4);
		skills.clear();
		skills.add(skill1);
		skills.add(skill2);
		skills.add(skill3);
		skills.add(skill4);
		Crepo.deleteAll();
		Prepo.deleteAll();
		testplayer=Prepo.save(testplayer);
		Pid=testplayer.getPlayer_id();
		testChar.setFk_player_id(Pid);
		testChar=Crepo.save(testChar);
		Cid=testChar.getChar_id();
	}
	
	@Test
	public void testListByTree() throws JsonProcessingException, Exception {
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.GET, "/skills/listbytree/1");
		
		mockRequest.contentType(MediaType.APPLICATION_JSON)
			.content(this.mapper.writeValueAsString(skills))
			.accept(MediaType.APPLICATION_JSON);
	
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.mapper.writeValueAsString(skills));
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
	}
	
	@Test
	public void testFindbyId() throws JsonProcessingException, Exception {
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.GET, "/skills/findbyid/"+skill1.getSkill_id());
		
		mockRequest.contentType(MediaType.APPLICATION_JSON)
			.content(this.mapper.writeValueAsString(skill1))
			.accept(MediaType.APPLICATION_JSON);
	
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.mapper.writeValueAsString(skill1));
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
	}
	
	@Test
	public void testFindByChar() throws JsonProcessingException, Exception {
		CharSkills charskill2 = new CharSkills(1,testChar.getChar_id(),skill1.getSkill_id());
		CharSkills charskill3 = new CharSkills(2,testChar.getChar_id(),skill2.getSkill_id());
		CharSkills charskill4 = new CharSkills(3,testChar.getChar_id(),skill3.getSkill_id());
		CharSkills charskill1 = new CharSkills(4,testChar.getChar_id(),skill4.getSkill_id());
		List<CharSkills> charskills = new ArrayList<CharSkills>();
		charskills.add(charskill2);
		charskills.add(charskill3);
		charskills.add(charskill4);
		charskills.add(charskill1);
		CSrepo.saveAll(charskills);
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.GET, "/skills/findbychar/" + Cid);
		
		mockRequest.contentType(MediaType.APPLICATION_JSON)
			.content(this.mapper.writeValueAsString(skills))
			.accept(MediaType.APPLICATION_JSON);
	
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.mapper.writeValueAsString(skills));
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
	}

}
