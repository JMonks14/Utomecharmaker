package com.tome.main.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tome.main.Services.HeadrefServices;

@RestController
@CrossOrigin
@RequestMapping("/headrefs")
public class HeadrefsController {
	
	@Autowired
	HeadrefServices serve;
	
	@GetMapping("/getmaxXP")
	public int getmaxXP() {
		return this.serve.getMaxXp();
	}
	
	@PutMapping("/setmaxXP/{number}")
	public void setMaxXP(@PathVariable int number) {
		this.serve.setMaxXP(number);
	}

}
