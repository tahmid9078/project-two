package com.ttv.services;

import static util.PasswordHashing.getSecurePassword;

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
		//encryp the password
		String encryptedPW = getSecurePassword(account.getPassword()); 
		//set the password as the encrypted password
		account.setPassword(encryptedPW);
		//default and set role to 'user'
		Role role = roleService.findById((long)1);
		account.setRole(role);
		//add account to DB
		return accountDao.add(account);
	}

	public List<Account> findAll() {
		return accountDao.findAll();
	}

	public Account findById(Long id) {
		return accountDao.findById(id);
	}

	public void update(Account account) {
		//get the account by ID
		Account a = accountDao.findById(account.getId()); 
		//encrypt the password
		account.setPassword(a.getPassword());
		//update account in DB
		accountDao.update(account);
	}

	public void deleteById(Long id) {
		accountDao.deleteById(id);
	}
	
	public boolean verifyAccount(Account account) {
		List<Account> aList = accountDao.findAll();
		for(Account a : aList) {
			//if account username AND password match an account in the DB
			if((account.getUsername().equals(a.getUsername())) 
					&& (getSecurePassword(account.getPassword()).equals(a.getPassword()))) {
				return true;	
			}
		}
		return false;
	}
	public boolean verifyRegistration(Account account) {
		List<Account> aList = accountDao.findAll();
		for(Account a : aList) {
			//compare to see if account username match an account in the DB
			if(account.getUsername().equals(a.getUsername())) {
				//return false if there is match because it has been taken.
				return false;
			}
		}
		return true;
	}
	
	public Account findByUsername(String username) {
		List<Account> aList = accountDao.findAll();
		//loop through accounts from DB to find the account with the username specified
		for(Account a : aList) {
			if(a.getUsername().equals(username)) {
				return a;
			}
		}
		return null;
	}

}
