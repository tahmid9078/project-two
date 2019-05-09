package com.ttv.daoImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ttv.daos.TmdbDao;
import com.ttv.models.Tmdb;

@Repository
@Transactional
public class TmdbDaoImpl implements TmdbDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Tmdb add(Tmdb tmdb) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			session.save(tmdb);
			transaction.commit();
				
		} catch(HibernateException e) {
			if(session != null) session.close();
			if(transaction != null) transaction.rollback();
			e.printStackTrace();
		}
		return tmdb;
	}

	@Override
	public List<Tmdb> findAll() {
		Session session = sessionFactory.getCurrentSession();
		List<Tmdb> tmdbs = null;
		
		try {
			session.beginTransaction();
			tmdbs = session.createQuery("FROM Tmdb").list();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return tmdbs;
	}

	@Override
	public Tmdb findById(Long id) {
		Session session = sessionFactory.getCurrentSession();
		Tmdb tmdb = null;
		
		try {
			session.beginTransaction();
			tmdb = (Tmdb) session.get(Tmdb.class, id);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return tmdb;
	}

	@Override
	public Boolean update(Tmdb tmdb) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(tmdb);
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
			session.delete(session.get(Tmdb.class, id));
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
