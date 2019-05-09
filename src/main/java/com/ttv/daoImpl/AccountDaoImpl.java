package com.ttv.daoImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ttv.daos.AccountDao;
import com.ttv.models.Account;

@Repository
@Transactional
public class AccountDaoImpl  implements AccountDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Account add(Account account) {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.save(account);
			transaction.commit();
				
		} catch(HibernateException e) {
			if(session != null) session.close();
			if(transaction != null) transaction.rollback();
			e.printStackTrace();
		}
		return account;
	}

	@Override
	public List<Account> findAll() {
		Session session = sessionFactory.getCurrentSession();
		List<Account> accounts = null;
		
		try {
			session.beginTransaction();
			accounts = session.createQuery("FROM Account").list();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return accounts;
	}

	@Override
	public Account findById(Long id) {
		Session session = sessionFactory.getCurrentSession();
		Account account = null;
		
		try {
			session.beginTransaction();
			account = (Account) session.get(Account.class, id);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return account;
	}

	@Override
	public Boolean update(Account account) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(account);
			tx.commit();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			tx.rollback();
			return false;
		} finally {
			session.close();
		}
	}

	@Override
	public Boolean deleteById(Long id) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.delete(session.get(Account.class, id));
			tx.commit();
			return true;
		} catch(HibernateException e) {
			e.printStackTrace();
			tx.rollback();
			return false;
		} finally {
			session.close();
		}
	}

}
