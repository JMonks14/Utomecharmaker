package com.tome.main.ServiceTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.tome.main.Repos.HeadrefsRepo;
import com.tome.main.Services.HeadrefServices;

@RunWith(MockitoJUnitRunner.class)
public class HeadRefServices {
	
	@Mock
	HeadrefsRepo repo;
	
	@InjectMocks
	HeadrefServices service;
	
	@Test
	public void getMaxXPTest() {
		int maxXP = 10;
		when(this.repo.getMaxXp()).thenReturn(10);
		assertEquals(maxXP, this.service.getMaxXp());
		verify(repo, Mockito.times(1)).getMaxXp();
	}

}
