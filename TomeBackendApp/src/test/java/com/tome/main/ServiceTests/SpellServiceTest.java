package com.tome.main.ServiceTests;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.tome.main.Enitities.Spell;
import com.tome.main.Repos.SpellRepo;
import com.tome.main.Services.SpellServices;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpellServiceTest {
	
	@MockBean
	SpellRepo repo;
	
	@Autowired
	SpellServices service;
	
	Spell spell1 = new Spell("blast",0,1,"ranged","blasts a guy");
	Spell spell2 = new Spell(1, "blast",0,1,"ranged","blasts a guy");
	Spell spell3 = new Spell(2,"fling",0,1,"fling","flings a guy");
	Spell spell4 = new Spell(3,"fling",2,3,"mass fling","flings lots of guys");
	
	@Test
	public void listSpellsTest() {
		List<Spell> spells = new ArrayList<Spell>();
		spells.add(spell2);
		spells.add(spell3);
		spells.add(spell4);
		when(this.repo.findAll()).thenReturn(spells);
		assertEquals(spells, service.listSpells());
		verify(repo, Mockito.times(1)).findAll();
	}
	
	@Test
	public void findByIdTest() {
		Spell checked = spell1;
		Mockito.when(this.repo.findById(1)).thenReturn(Optional.of(checked));
		assertEquals(checked, service.findById(1));
		Mockito.verify(repo, Mockito.times(1)).findById(1);
	}
	
	@Test
	public void getCharSpells() {
		List<Spell> spells = new ArrayList<Spell>();
		spells.add(spell4);
		spells.add(spell2);
		spells.add(spell3);		
		when(this.repo.getCharSpells(1)).thenReturn(spells);
		assertEquals(spells, service.getCharSpells(1));
		verify(repo, Mockito.times(1)).getCharSpells(1);
	}

}
