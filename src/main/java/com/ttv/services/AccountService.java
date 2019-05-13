package com.ttv.services;

import static util.PasswordHashing.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ttv.daos.AccountDao;
import com.ttv.models.Account;
import com.ttv.models.Role;

@Service
public class AccountService {

	@Autowired
	private AccountDao accountDao;
	@Autowired
	private RoleService roleService;

	public Long add(Account account) {
		String encryptedPW = getSecurePassword(account.getPassword());
		account.setPassword(encryptedPW);
		Role role = roleService.findById((long)1);
		account.setRole(role);
		
		return accountDao.add(account);
	}

	public List<Account> findAll() {
		return accountDao.findAll();
	}

	public Account findById(Long id) {
		return accountDao.findById(id);
	}

	public void update(Account account) {
		Account a = accountDao.findById(account.getId()); //get the id
		account.setPassword(a.getPassword()); //makesure the pw is updated so the encrypted one does not get replaced
		System.out.println("the pw " + getSecurePassword("password") + "\nDB pw " + account.getPassword());
		accountDao.update(account);
	}

	public void deleteById(Long id) {
		accountDao.deleteById(id);
	}
	
	public boolean verifyAccount(Account account) {
		List<Account> aList = accountDao.findAll();
		System.out.println(account);
		for(Account a : aList) {
			if((account.getUsername().equals(a.getUsername())) && (getSecurePassword(account.getPassword()).equals(a.getPassword()))) {
				return true;	
			}
		}
		return false;
	}
	public boolean verifyRegistration(Account account) {
		List<Account> aList = accountDao.findAll();
		for(Account a : aList) {
			if(account.getUsername().equals(a.getUsername())) {
				System.out.println("equals");
				return false;
			}
		}
		System.out.println("no match");
		return true;
	}
	
	public Account findByUsername(String username) {
		List<Account> aList = accountDao.findAll();
		for(Account a : aList) {
			if(a.getUsername().equals(username)) {
				return a;
			}
		}
		return null;
	}

}
