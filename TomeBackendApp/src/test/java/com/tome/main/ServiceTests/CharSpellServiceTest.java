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

import com.tome.main.Enitities.CharSpell;
import com.tome.main.Repos.CharSpellRepo;
import com.tome.main.Services.CharSpellServices;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CharSpellServiceTest {
	
	@MockBean
	CharSpellRepo repo;
	
	@Autowired
	CharSpellServices service;
	
	CharSpell charspell1 = new CharSpell(1,1);
	CharSpell charspell2 = new CharSpell(1,1,1);
	CharSpell charspell3 = new CharSpell(2,1,2);
	CharSpell charspell4 = new CharSpell(3,1,3);
	List<CharSpell> charspells = new ArrayList<CharSpell>();
	
	@Test
	public void charSpellIdsTest() {
		charspells.add(charspell2);
		charspells.add(charspell3);
		charspells.add(charspell4);
		when(this.repo.getCharSpellIds(1)).thenReturn(charspells);
		assertEquals(charspells, this.service.charSpellIds(1));
		verify(repo, Mockito.times(1)).getCharSpellIds(1);
	}
	
	@Test
	public void buySpellTest() {
		when(this.repo.save(charspell1)).thenReturn(charspell2);
		assertEquals(charspell2, this.service.buySpell(charspell1));
		verify(repo, Mockito.times(1)).save(charspell1);
	}
	
	@Test
	public void resetTest() {
		doNothing().doThrow(new RuntimeException()).when(repo).reset(1);
		this.service.reset(1);;
		verify(repo, Mockito.times(1)).reset(1);
	}

}