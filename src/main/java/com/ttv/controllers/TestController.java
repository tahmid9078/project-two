package com.ttv.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ttv.models.Account;
import com.ttv.models.Role;
import com.ttv.services.AccountService;
import com.ttv.services.RoleService;

@RestController
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private RoleService roleService;
	
	@GetMapping("")
	public String test() {
		return ".................";
	}
	
}
