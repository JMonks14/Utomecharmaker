package com.tome.main.ControllerTests;

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
import com.tome.main.Enitities.Characters;
import com.tome.main.Enitities.Player;
import com.tome.main.Repos.CharacterRepo;
import com.tome.main.Repos.PlayerRepo;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CharacterControllerTests {
	
	@Autowired
	private MockMvc mock;
	
	@Autowired
	private PlayerRepo Prepo;
	
	@Autowired
	private CharacterRepo repo;
	
	@Autowired
	private ObjectMapper mapper = new ObjectMapper();
	
	private Characters testChar = new Characters("Steve","human","Ardglass","boring",1,true,3,3,1,0,0,0,0);
	private Characters newtestchar= new Characters("Steve","human","Ardglass","boring",1,true,4,4,1,4,8,0,0);
	private Characters testcharwithId;
	private Player testplayer = new Player("Bob", "Smith", "Smithy", "neilthebaby",0);
	int id;
	
	@Before
	public void init() {
		this.repo.deleteAll();
		this.Prepo.deleteAll();
		this.testplayer=this.Prepo.save(testplayer);
		this.testChar.setFk_player_id(testplayer.getPlayer_id());
		this.testcharwithId=this.repo.save(testChar);
		this.id=this.testcharwithId.getChar_id();
		this.testChar.setFk_player_id(testplayer.getPlayer_id());
		this.newtestchar.setFk_player_id(testplayer.getPlayer_id());
	}
	
	@Test
	public void testCreate() throws JsonProcessingException, Exception {
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.POST, "/character/create");
		
		mockRequest.contentType(MediaType.APPLICATION_JSON)
			.content(this.mapper.writeValueAsString(testcharwithId))
			.accept(MediaType.APPLICATION_JSON);
	
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isCreated();
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.mapper.writeValueAsString(testcharwithId));
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
	}
	
	@Test
	public void testView() throws JsonProcessingException, Exception {
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.GET, "/character/view/"+testcharwithId.getChar_id());
		
		mockRequest.contentType(MediaType.APPLICATION_JSON)
			.content(this.mapper.writeValueAsString(testcharwithId))
			.accept(MediaType.APPLICATION_JSON);
	
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.mapper.writeValueAsString(testcharwithId));
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
	}
	
	@Test
	public void testKill() throws JsonProcessingException, Exception {
		Characters dead = testChar;
		dead.setAlive(false);
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.POST, "/character/kill/" + id);
					
				mockRequest.contentType(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(dead))
				.accept(MediaType.APPLICATION_JSON);
			    
				ResultMatcher matchStatus = MockMvcResultMatchers.status().isAccepted();
				ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.mapper.writeValueAsString(dead));
				this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
				
	}
	@Test
	public void testUpdate() throws JsonProcessingException, Exception {
		newtestchar.setChar_id(id);
		MockHttpServletRequestBuilder mockRequest = 
		MockMvcRequestBuilders.request(HttpMethod.POST, "/character/update/" + id);
			
		mockRequest.contentType(MediaType.APPLICATION_JSON)
		.content(this.mapper.writeValueAsString(newtestchar))
		.accept(MediaType.APPLICATION_JSON);
	    
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isAccepted();
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.mapper.writeValueAsString(newtestchar));
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
		}
}
