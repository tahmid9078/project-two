package com.ttv.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ttv.daos.AccountDao;
import com.ttv.models.Account;

@Service
public class AccountService {
	
	@Autowired
	private AccountDao accountDao;
	
	public Account add(Account account) {
		return accountDao.add(account);
	}
	
	public List<Account> findAll(){
		return accountDao.findAll();
	}
	
	public Account findById(Long id){
		return accountDao.findById(id);
	}
	
	public Boolean update(Account account) {
		return accountDao.update(account);
	}
	
	public Boolean deleteById(Long id) {
		return accountDao.deleteById(id);
	}
}
