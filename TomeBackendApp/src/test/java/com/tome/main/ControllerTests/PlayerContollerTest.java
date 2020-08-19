package com.tome.main.ControllerTests;

import java.util.ArrayList;
import java.util.List;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
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
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tome.main.Enitities.Player;
import com.tome.main.Repos.PlayerRepo;

import org.springframework.test.web.servlet.ResultMatcher;
import com.fasterxml.jackson.core.JsonProcessingException;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PlayerContollerTest {
	
	@Autowired
	private MockMvc mock;
	
	@Autowired
	private PlayerRepo repo;
	
	@Autowired
	private ObjectMapper mapper = new ObjectMapper();
	
	private Player testplayer;
	private Player newtestplayer;
	private Player testplayerwithId;
	private int id;
	private List<Player> playlist = new ArrayList<>();
	
	@Before
	public void init() {
		this.repo.deleteAll();
		this.testplayer= new Player("Bob", "Smith", "Smithy", "neilthebaby",0);
		this.newtestplayer= new Player("Jack","green", "Beardy", "releaseyourchains",0);
		this.testplayerwithId = this.repo.save(testplayer);
		this.id=this.testplayerwithId.getPlayer_id();
		this.testplayer.setPlayer_id(id);
		this.playlist.add(testplayer);
	}
	
	@Test
	public void testCreate() throws JsonProcessingException, Exception {
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.POST, "/player/register");
		
		mockRequest.contentType(MediaType.APPLICATION_JSON)
			.content(this.mapper.writeValueAsString(testplayerwithId))
			.accept(MediaType.APPLICATION_JSON);
	
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isCreated();
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.mapper.writeValueAsString(testplayerwithId));
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
	}
	
	@Test
	public void testView() throws JsonProcessingException, Exception {
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.GET, "/player/view/"+testplayerwithId.getPlayer_id());
		
		mockRequest.contentType(MediaType.APPLICATION_JSON)
			.content(this.mapper.writeValueAsString(testplayerwithId))
			.accept(MediaType.APPLICATION_JSON);
	
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.mapper.writeValueAsString(testplayerwithId));
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
	}
	
	@Test
	public void testViewLatest() throws JsonProcessingException, Exception {
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.GET, "/player/viewlatest");
		
		mockRequest.contentType(MediaType.APPLICATION_JSON)
			.content(this.mapper.writeValueAsString(testplayerwithId))
			.accept(MediaType.APPLICATION_JSON);
	
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.mapper.writeValueAsString(testplayerwithId));
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
	}
	
	@Test
	public void testUpdate() throws JsonProcessingException, Exception {
	newtestplayer.setPlayer_id(id);
	MockHttpServletRequestBuilder mockRequest = 
	MockMvcRequestBuilders.request(HttpMethod.POST, "/player/update/" + id);
		
	mockRequest.contentType(MediaType.APPLICATION_JSON)
	.content(this.mapper.writeValueAsString(newtestplayer))
	.accept(MediaType.APPLICATION_JSON);
    
	ResultMatcher matchStatus = MockMvcResultMatchers.status().isAccepted();
	ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.mapper.writeValueAsString(newtestplayer));
	this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
	}
	
	@Test
	public void testFindByUsername() throws JsonProcessingException, Exception {
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.GET, "/player/find/"+testplayerwithId.getUsername());
		
		mockRequest.contentType(MediaType.APPLICATION_JSON)
			.content(this.mapper.writeValueAsString(testplayerwithId))
			.accept(MediaType.APPLICATION_JSON);
	
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.mapper.writeValueAsString(testplayerwithId));
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
	}
	
}


