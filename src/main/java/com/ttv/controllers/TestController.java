package com.ttv.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ttv.models.Account;
import com.ttv.services.AccountService;

@Controller
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	private AccountService accountService;
	
	@GetMapping("/accounts")
	public List<Account> getAllAccount() {
		return accountService.findAll();
	}
	
	@RequestMapping(value="/hello", method=RequestMethod.GET)
	public ResponseEntity<String> sayHello() {
		return new ResponseEntity<>("Hello!", HttpStatus.OK);
	}
}
