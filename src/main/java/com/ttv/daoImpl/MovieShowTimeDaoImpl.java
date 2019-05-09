package com.ttv.daoImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ttv.daos.MovieShowTimeDao;
import com.ttv.models.MovieShowTime;

@Repository
@Transactional
public class MovieShowTimeDaoImpl implements MovieShowTimeDao {

	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public MovieShowTime add(MovieShowTime movieShowTime) {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.save(movieShowTime);
			transaction.commit();
				
		} catch(HibernateException e) {
			if(session != null) session.close();
			if(transaction != null) transaction.rollback();
			e.printStackTrace();
		}
		return movieShowTime;
	}

	@Override
	public List<MovieShowTime> findAll() {
		Session session = sessionFactory.getCurrentSession();
		List<MovieShowTime> movieShowTimes = null;
		
		try {
			session.beginTransaction();
			movieShowTimes = session.createQuery("FROM MovieShowTime").list();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return movieShowTimes;
	}

	@Override
	public MovieShowTime findById(Long id) {
		Session session = sessionFactory.getCurrentSession();
		MovieShowTime movieShowTime = null;
		
		try {
			session.beginTransaction();
			movieShowTime = (MovieShowTime) session.get(MovieShowTime.class, id);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return movieShowTime;
	}

	@Override
	public Boolean update(MovieShowTime movieShowTime) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(movieShowTime);
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
			session.delete(session.get(MovieShowTime.class, id));
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
