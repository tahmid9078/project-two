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

@Service
public class AccountService {

	@Autowired
	private AccountDao accountDao;

	public Long add(Account account) throws NoSuchAlgorithmException, NoSuchProviderException {
		String encryptedPW = getSecurePassword(account.getPassword());
		account.setPassword(encryptedPW);
		return accountDao.add(account);
	}

	public List<Account> findAll() {
		return accountDao.findAll();
	}

	public Account findById(Long id) {
		return accountDao.findById(id);
	}

	public void update(Account account) throws NoSuchAlgorithmException, NoSuchProviderException {
		Account a = accountDao.findById(account.getId()); //get the id
		account.setPassword(a.getPassword()); //makesure the pw is updated so the encrypted one does not get replaced

		System.out.println("the pw " + getSecurePassword("password") + "\nDB pw " + account.getPassword());
		
		accountDao.update(account);
	}

	public void deleteById(Long id) {
		accountDao.deleteById(id);
	}
	
	public boolean verifyAccount(String username, String password) {
		List<Account> aList = accountDao.findAll();
		for(Account a : aList) {
			if((username == a.getUsername()) && (getSecurePassword(password) == a.getPassword())) {
				return true;
			}
		}
		return false;
	}

}
