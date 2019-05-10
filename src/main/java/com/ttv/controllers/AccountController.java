package com.ttv.controllers;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ttv.models.Account;
import com.ttv.services.AccountService;
import com.ttv.services.RoleService;

@CrossOrigin(origins = "*", allowedHeaders="*")
@RestController
@RequestMapping("/account")
public class AccountController {
	@Autowired
	AccountService accountService;
	@Autowired
	RoleService roleService;

	@PostMapping("")
	public Map<String, Boolean> insertAccount(@RequestBody Account account) {
		if(accountService.verifyRegistration(account) != false) {
			accountService.add(account);
			return Collections.singletonMap("success", true);
		} 
		return Collections.singletonMap("success", false);	
	}
	
	@PostMapping("/login") 
	public Account verifyAccount(@RequestBody Account account) {
		if(accountService.verifyAccount(account) == true) {
			return accountService.findByUsername(account.getUsername());
		}
		return new Account();	
	}

}
