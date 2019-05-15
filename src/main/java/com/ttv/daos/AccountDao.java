package com.ttv.daos;

import java.util.List;

import com.ttv.models.Account;

public interface AccountDao {
	public Long add(Account account);
	public List<Account> findAll();
	public Account findById(Long id);
	public void update(Account account);
	public void deleteById(Long id);
}
