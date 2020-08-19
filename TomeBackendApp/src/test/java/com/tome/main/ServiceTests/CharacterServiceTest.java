package com.tome.main.ServiceTests;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.tome.main.Enitities.Characters;
import com.tome.main.Enitities.Player;
import com.tome.main.Exceptions.PlayerNotFoundException;
import com.tome.main.Repos.CharacterRepo;
import com.tome.main.Services.CharacterServices;
import com.tome.main.Services.PlayerServices;

@RunWith(MockitoJUnitRunner.class)
public class CharacterServiceTest {
	
	@Mock
	private CharacterRepo Repo;
	
	@Mock
	private PlayerServices playerservice;
	
	@InjectMocks
	private CharacterServices service;
	
	Player player2 = new Player(1,"Paul", "James","paulinus","romeForever",0);
	Characters char1 = new Characters(1,"test", "test","test","test",1,true,3,3,1,0,0,0,0);
	Characters char2 = new Characters("test", "test","test","test",1,true,3,3,1,0,0,0,0);
	Characters char3 = new Characters(1,"test2", "test2","test2","test2",1,true,4,3,1,0,0,0,0);
	Characters chardead = new Characters(1,"test", "test","test","test",1,false,3,3,1,0,0,0,0);
	@Test
	public void charCreateTest() {
		Characters returned = char1;
		Mockito.when(this.playerservice.viewById(1)).thenReturn(player2);
		Mockito.when(this.Repo.save(char2)).thenReturn(char1);
		assertEquals(returned, service.create(char2));
		Mockito.verify(Repo, Mockito.times(1)).save(char2);
		}
	
	@Test
	public void viewByIdTest() {
		Characters checked = char1;
		Mockito.when(this.Repo.findById(1)).thenReturn(Optional.of(checked));
		assertEquals(checked, service.viewById(1));
		Mockito.verify(Repo, Mockito.times(1)).findById(1);
	}
	
	@Test
	public void updateTest() {
		Characters orig = char1;
		Characters up = char3;
		Mockito.when(this.Repo.findById(1)).thenReturn(Optional.of(orig));
		Mockito.when(this.Repo.save(orig)).thenReturn(up);
		assertEquals(up, this.service.update(up, 1));
		Mockito.verify(Repo, Mockito.times(1)).findById(1);
		Mockito.verify(Repo, Mockito.times(1)).save(orig);
	}
	@Test
	public void killTest() {
		Characters dead = chardead;
		Player player = player2;
		player.setActiveChar(1);
		Mockito.when(this.playerservice.viewById(1)).thenReturn(player);
		Mockito.when(this.Repo.findById(1)).thenReturn(Optional.of(char1));
		Mockito.when(this.Repo.save(char1)).thenReturn(dead);
		assertEquals(dead, this.service.kill(1));
		
		Mockito.verify(Repo, Mockito.times(1)).save(char1);
		Mockito.verify(playerservice, Mockito.times(1)).update(player,player.getPlayer_id());
		
	}

}
