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

import com.ttv.daos.TmdbDao;
import com.ttv.models.Tmdb;

@Repository
@Transactional
@EnableTransactionManagement
public class TmdbDaoImpl implements TmdbDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Tmdb add(Tmdb tmdb) {
		Session session = sessionFactory.getCurrentSession();
		session.save(tmdb);
		return tmdb;
	}

	@Override
	public List<Tmdb> findAll() {
		Session session = sessionFactory.getCurrentSession();
		List<Tmdb> tmdbs = session.createQuery("FROM Tmdb").list();
		return tmdbs;
	}

	@Override
	public Tmdb findById(Long id) {
		Session session = sessionFactory.getCurrentSession();
		Tmdb tmdb = (Tmdb) session.get(Tmdb.class, id);
		return tmdb;
	}

	@Override
	public void update(Tmdb tmdb) {
		Session session = sessionFactory.getCurrentSession();
		session.update(tmdb);
	}

	@Override
	public void deleteById(Long id) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(session.get(Tmdb.class, id));

	}

}
