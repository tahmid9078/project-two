package com.ttv.daoImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.ttv.daos.AccountDao;
import com.ttv.models.Account;

@Repository
@Transactional
@EnableTransactionManagement
public class AccountDaoImpl implements AccountDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Long add(Account account) {
		Session session = sessionFactory.getCurrentSession();
		return (Long) session.save(account);
		
	}

	@Override
	public List<Account> findAll() {
		Session session = sessionFactory.getCurrentSession();
		List<Account> accounts = null;
		accounts = session.createQuery("FROM Account").list();
		return accounts;
	}

	@Override
	public Account findById(Long id) {
		Session session = sessionFactory.getCurrentSession();
		Account account = null;
		account = (Account) session.get(Account.class, id);
		return account;
	}

	@Override
	public void update(Account account) {

		Session session = sessionFactory.getCurrentSession();
		session.update(account);
	}

	@Override
	public void deleteById(Long id) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(session.get(Account.class, id));
	}

}
