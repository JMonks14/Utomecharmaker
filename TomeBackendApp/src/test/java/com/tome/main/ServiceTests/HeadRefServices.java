package com.tome.main.ServiceTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.tome.main.Repos.HeadrefsRepo;
import com.tome.main.Services.HeadrefServices;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HeadRefServices {
	
	@MockBean
	HeadrefsRepo repo;
	
	@Autowired
	HeadrefServices service;
	
	@Test
	public void getMaxXPTest() {
		int maxXP = 10;
		when(this.repo.getMaxXp()).thenReturn(10);
		assertEquals(maxXP, this.service.getMaxXp());
		verify(repo, Mockito.times(1)).getMaxXp();
	}

}
