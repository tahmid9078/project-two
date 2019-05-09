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

import com.ttv.daos.ReviewDao;
import com.ttv.models.Review;

@Repository
@Transactional
@EnableTransactionManagement
public class ReviewDaoImpl implements ReviewDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Review add(Review review) {
		Session session = sessionFactory.getCurrentSession();
		session.save(review);
		return review;
	}

	@Override
	public List<Review> findAll() {
		Session session = sessionFactory.getCurrentSession();
		List<Review> reviews = session.createQuery("FROM Review").list();
		return reviews;
	}

	@Override
	public Review findById(Long id) {
		Session session = sessionFactory.getCurrentSession();
		Review review = (Review) session.get(Review.class, id);
		return review;
	}

	@Override
	public void update(Review review) {
		Session session = sessionFactory.getCurrentSession();
		session.update(review);

	}

	@Override
	public void deleteById(Long id) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(session.get(Review.class, id));

	}

}
