package com.tome.main.ServiceTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import com.tome.main.Enitities.Player;
import com.tome.main.Exceptions.PlayerNotFoundException;
import com.tome.main.Repos.PlayerRepo;
import com.tome.main.Services.PlayerServices;

@RunWith(MockitoJUnitRunner.class)
public class PlayerServiceTest {
	
	@Mock
	private PlayerRepo Repo;
	
	@InjectMocks
	private PlayerServices service;
	
	Player player = new Player("Paul", "James","paulinus","romeForever",0);
	Player player2 = new Player(1,"Paul", "James","paulinus","romeForever",0);
	Player player3 = new Player(1, "Steve", "Bunce", "Buncey", "boxinglife",0);
	
	@Test
	public void playerCreateTest() {
	Player returned = player;
	Mockito.when(this.Repo.save(player)).thenReturn(player);
	assertEquals(returned, service.create(player));
	Mockito.verify(Repo, Mockito.times(1)).save(player);
	}
	
	@Test
	public void viewByIdTest() {
		Player checked = player;
		checked.setPlayer_id(1);
		Mockito.when(this.Repo.findById(1)).thenReturn(Optional.of(checked));
		assertEquals(checked, service.viewById(1));
		Mockito.verify(Repo, Mockito.times(1)).findById(1);
	}
	
	@Test
	public void viewLatestTest() {
		Player checked = player;
		checked.setPlayer_id(1);
		Mockito.when(this.Repo.findLatest()).thenReturn(checked);
		assertEquals(checked, service.viewLatest());
		Mockito.verify(Repo, Mockito.times(1)).findLatest();
	}
	@Test
	public void updateTest() {
		Player orig = player2;
		Player up = player3;
		player3.setActiveChar(2);
		Mockito.when(this.Repo.findById(1)).thenReturn(Optional.of(orig));
		Mockito.when(this.Repo.save(orig)).thenReturn(up);
		assertEquals(up, this.service.update(up, 1));
		Mockito.verify(Repo, Mockito.times(1)).findById(1);
		Mockito.verify(Repo, Mockito.times(1)).save(orig);		
	}
	
	@Test
	public void findByUsernameTest() {
		Player find = player2;
		Mockito.when(this.Repo.findByUsername("paulinus")).thenReturn(Optional.of(find));
		assertEquals(find, this.service.findByUsername("paulinus"));
		Mockito.verify(Repo, Mockito.times(1)).findByUsername("paulinus");
	}

}
