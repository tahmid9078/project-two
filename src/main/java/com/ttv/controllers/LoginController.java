package com.ttv.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ttv.services.AccountService;

@Controller
@RequestMapping("/account")
public class LoginController {
	@Autowired 
	AccountService as;
	
	
	
}
