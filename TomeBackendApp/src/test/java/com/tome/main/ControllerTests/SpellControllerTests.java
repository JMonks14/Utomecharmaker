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
import com.tome.main.Enitities.CharSpell;
import com.tome.main.Enitities.Characters;
import com.tome.main.Enitities.Player;
import com.tome.main.Enitities.Spell;
import com.tome.main.Repos.CharSpellRepo;
import com.tome.main.Repos.CharacterRepo;
import com.tome.main.Repos.PlayerRepo;
import com.tome.main.Repos.SpellRepo;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SpellControllerTests {
	
	@Autowired
	private MockMvc mock;
	
	@Autowired
	private SpellRepo repo;
	
	@Autowired
	CharSpellRepo CSrepo;
	
	@Autowired
	private PlayerRepo Prepo;
	
	@Autowired
	private CharacterRepo Crepo;
	
	@Autowired
	private ObjectMapper mapper = new ObjectMapper();
	
	Spell spell1 = new Spell("blast",0,1,"ranged","blasts a guy");
	Spell spell2 = new Spell("bind",0,1,"ranged","binds a guy");
	Spell spell3 = new Spell("fling",0,1,"fling","flings a guy");
	Spell spell4 = new Spell("mass fling",0,3,"mass","flings lots of guys");
	
	List<Spell> spells = new ArrayList<>();
	List <Spell> spellsreal = new ArrayList<>();
	private Characters testChar = new Characters("Steve","human","Ardglass","boring",1,true,3,3,1,0,0,0,0);
	private Player testplayer = new Player("Bob", "Smith", "Smithy", "neilthebaby",0);
	int Pid;
	int Cid;
	
	@Before
	public void init () {
		CSrepo.deleteAll();
		spells=repo.listAll();
		repo.deleteInBatch(spells);
		spell1=repo.save(spell1);
		spell2=repo.save(spell2);
		spell3=repo.save(spell3);
		spell4=repo.save(spell4);
		spells.clear();
		spells.add(spell1);
		spells.add(spell2);
		spells.add(spell3);
		spells.add(spell4);
		Crepo.deleteAll();
		Prepo.deleteAll();
		testplayer=Prepo.save(testplayer);
		Pid=testplayer.getPlayer_id();
		testChar.setFk_player_id(Pid);
		testChar=Crepo.save(testChar);
		Cid=testChar.getChar_id();
	}
	
	@Test
	public void testListAll() throws JsonProcessingException, Exception {
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.GET, "/spell/listall");
		
		mockRequest.contentType(MediaType.APPLICATION_JSON)
			.content(this.mapper.writeValueAsString(spells))
			.accept(MediaType.APPLICATION_JSON);
	
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.mapper.writeValueAsString(spells));
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
	}
	
	@Test
	public void testFindbyId() throws JsonProcessingException, Exception {
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.GET, "/spell/find/"+spell1.getSpell_id());
		
		mockRequest.contentType(MediaType.APPLICATION_JSON)
			.content(this.mapper.writeValueAsString(spell1))
			.accept(MediaType.APPLICATION_JSON);
	
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.mapper.writeValueAsString(spell1));
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
	}
	
	@Test
	public void testFindByChar() throws JsonProcessingException, Exception {
		CharSpell charspell2 = new CharSpell(1,testChar.getChar_id(),spell1.getSpell_id());
		CharSpell charspell3 = new CharSpell(2,testChar.getChar_id(),spell2.getSpell_id());
		CharSpell charspell4 = new CharSpell(3,testChar.getChar_id(),spell3.getSpell_id());
		CharSpell charspell1 = new CharSpell(4,testChar.getChar_id(),spell4.getSpell_id());
		List<CharSpell> charspells = new ArrayList<CharSpell>();
		charspells.add(charspell1);
		charspells.add(charspell2);
		charspells.add(charspell3);
		charspells.add(charspell4);
		CSrepo.saveAll(charspells);
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.GET, "/spell/findbychar/" + Cid);
		
		mockRequest.contentType(MediaType.APPLICATION_JSON)
			.content(this.mapper.writeValueAsString(spells))
			.accept(MediaType.APPLICATION_JSON);
	
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.mapper.writeValueAsString(spells));
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
	}

}
