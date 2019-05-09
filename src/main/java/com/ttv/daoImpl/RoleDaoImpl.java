package com.ttv.daoImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ttv.daos.RoleDao;
import com.ttv.models.Role;

@Repository
@Transactional
public class RoleDaoImpl implements RoleDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Role add(Role role) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			session.save(role);
			transaction.commit();
				
		} catch(HibernateException e) {
			if(session != null) session.close();
			if(transaction != null) transaction.rollback();
			e.printStackTrace();
		}
		return role;
	}

	@Override
	public List<Role> findAll() {
		Session session = sessionFactory.getCurrentSession();
		List<Role> roles = null;
		
		try {
			session.beginTransaction();
			roles = session.createQuery("FROM Role").list();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return roles;
	}

	@Override
	public Role findById(Long id) {
		Session session = sessionFactory.getCurrentSession();
		Role role = null;
		
		try {
			session.beginTransaction();
			role = (Role) session.get(Role.class, id);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return role;
	}

	@Override
	public Boolean update(Role role) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(role);
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
			session.delete(session.get(Role.class, id));
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
