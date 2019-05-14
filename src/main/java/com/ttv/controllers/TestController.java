package com.ttv.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ttv.models.Role;
import com.ttv.services.AccountService;
import com.ttv.services.RoleService;

@CrossOrigin()
@RestController
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private RoleService roleService;
	
	@GetMapping("")
	public String test() {
		return "................";
	}
	
}
