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

import com.ttv.daos.MovieShowTimeDao;
import com.ttv.models.MovieShowTime;

@Repository
@Transactional
@EnableTransactionManagement
public class MovieShowTimeDaoImpl implements MovieShowTimeDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public MovieShowTime add(MovieShowTime movieShowTime) {
		Session session = sessionFactory.getCurrentSession();
		session.save(movieShowTime);
		return movieShowTime;
	}

	@Override
	public List<MovieShowTime> findAll() {
		Session session = sessionFactory.getCurrentSession();
		List<MovieShowTime> movieShowTimes = session.createQuery("FROM MovieShowTime").list();
		return movieShowTimes;
	}

	@Override
	public MovieShowTime findById(Long id) {
		Session session = sessionFactory.getCurrentSession();
		MovieShowTime movieShowTime = (MovieShowTime) session.get(MovieShowTime.class, id);
		return movieShowTime;
	}

	@Override
	public void update(MovieShowTime movieShowTime) {
		Session session = sessionFactory.getCurrentSession();
		session.update(movieShowTime);
	}

	@Override
	public void deleteById(Long id) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(session.get(MovieShowTime.class, id));
	}

}
