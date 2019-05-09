package com.ttv.daoImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ttv.daos.ReviewDao;
import com.ttv.models.Review;

@Repository
@Transactional
public class ReviewDaoImpl implements ReviewDao {

	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public Review add(Review review) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			session.save(review);
			transaction.commit();

		} catch (HibernateException e) {
			if (session != null)
				session.close();
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		}
		return review;
	}

	@Override
	public List<Review> findAll() {
		Session session = sessionFactory.getCurrentSession();
		List<Review> reviews = null;

		try {
			session.beginTransaction();
			reviews = session.createQuery("FROM Review").list();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return reviews;
	}

	@Override
	public Review findById(Long id) {
		Session session = sessionFactory.getCurrentSession();
		Review review = null;

		try {
			session.beginTransaction();
			review = (Review) session.get(Review.class, id);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return review;
	}

	@Override
	public Boolean update(Review review) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(review);
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
			session.delete(session.get(Review.class, id));
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

}
