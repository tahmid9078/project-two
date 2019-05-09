package com.ttv.daos;

import java.util.List;

import com.ttv.models.Account;

public interface AccountDao {
	public Account add(Account account);
	public List<Account> findAll();
	public Account findById(Long id);
	public Boolean update(Account account);
	public Boolean deleteById(Long id);
}
