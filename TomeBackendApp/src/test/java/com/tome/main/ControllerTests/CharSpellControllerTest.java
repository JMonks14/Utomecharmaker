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
public class CharSpellControllerTest {
	
	@Autowired
	private MockMvc mock;
	
	@Autowired
	CharSpellRepo CSrepo;
	
	@Autowired
	private SpellRepo repo;
	
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
	private Player testplayer = new Player("Bob", "Smith", "Smithy", "neilthebaby",0);
	private Characters testChar = new Characters("Steve","human","Ardglass","boring",1,true,3,3,0,0,0,0,0);
	int Pid;
	int Cid;
	
	CharSpell charspell2 = new CharSpell(1,0,0);
	CharSpell charspell3 = new CharSpell(2,0,0);
	CharSpell charspell4 = new CharSpell(3,0,0);
	CharSpell charspell1 = new CharSpell(4,0,0);
	List<CharSpell> charspells = new ArrayList<CharSpell>();
	
	@Before
	public void init () {
		CSrepo.deleteAll();
		Crepo.deleteAll();
		Prepo.deleteAll();
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
		testplayer=Prepo.save(testplayer);
		Pid=testplayer.getPlayer_id();
		testChar.setFk_player_id(Pid);
		testChar=Crepo.save(testChar);
		Cid=testChar.getChar_id();
		charspell1.setFk_char_id(Cid);
		charspell1.setFk_spell_id(spell1.getSpell_id());
		charspell1=CSrepo.save(charspell1);
		charspell2.setFk_char_id(Cid);
		charspell2.setFk_spell_id(spell2.getSpell_id());
		charspell2=CSrepo.save(charspell2);
		charspell3.setFk_char_id(Cid);
		charspell3.setFk_spell_id(spell3.getSpell_id());
		charspell3=CSrepo.save(charspell3);
		charspell4.setFk_char_id(Cid);
		charspell4.setFk_spell_id(spell4.getSpell_id());
		charspell4=CSrepo.save(charspell4);		
	}
	@Test
	public void testBuy() throws JsonProcessingException, Exception {
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.POST, "/charspell/buy");
		
		mockRequest.contentType(MediaType.APPLICATION_JSON)
			.content(this.mapper.writeValueAsString(charspell1))
			.accept(MediaType.APPLICATION_JSON);
	
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isCreated();
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.mapper.writeValueAsString(charspell1));
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
	}
	
	@Test
	public void testCharSpellIds() throws JsonProcessingException, Exception {
		charspells.add(charspell1);
		charspells.add(charspell2);
		charspells.add(charspell3);
		charspells.add(charspell4);
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.GET, "/charspell/byid/" + Cid);
		
		mockRequest.contentType(MediaType.APPLICATION_JSON)
			.content(this.mapper.writeValueAsString(charspells))
			.accept(MediaType.APPLICATION_JSON);
	
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.mapper.writeValueAsString(charspells));
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
	}
	
	@Test
	public void testReset() throws JsonProcessingException, Exception {
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.DELETE, "/charspell/reset/" + Cid);
		
//		mockRequest.contentType(MediaType.APPLICATION_JSON)
//			.content(this.mapper.writeValueAsString(charspells))
//			.accept(MediaType.APPLICATION_JSON);
	
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isNoContent();
		this.mock.perform(mockRequest).andExpect(matchStatus);
	}
	
}
