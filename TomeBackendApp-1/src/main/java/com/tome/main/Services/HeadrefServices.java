package com.tome.main.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tome.main.Repos.HeadrefsRepo;

@Service
public class HeadrefServices {
	
	@Autowired
	HeadrefsRepo repo;
	
	public int getMaxXp() {
		return this.repo.getMaxXp();
	}
	
	public void setMaxXP(int number) {
		this.repo.setMaxXp(number);
	}

}
