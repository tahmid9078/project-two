package com.ttv.daoImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ttv.daos.TicketTypeDao;
import com.ttv.models.TicketType;

@Repository
@Transactional
public class TicketTypeDaoImpl implements TicketTypeDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public TicketType add(TicketType ticketType) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			session.save(ticketType);
			transaction.commit();
				
		} catch(HibernateException e) {
			if(session != null) session.close();
			if(transaction != null) transaction.rollback();
			e.printStackTrace();
		}
		return ticketType;
	}

	@Override
	public List<TicketType> findAll() {
		Session session = sessionFactory.getCurrentSession();
		List<TicketType> ticketTypes = null;
		
		try {
			session.beginTransaction();
			ticketTypes = session.createQuery("FROM TicketType").list();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return ticketTypes;
	}

	@Override
	public TicketType findById(Long id) {
		Session session = sessionFactory.getCurrentSession();
		TicketType ticketType = null;
		
		try {
			session.beginTransaction();
			ticketType = (TicketType) session.get(TicketType.class, id);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return ticketType;
	}

	@Override
	public Boolean update(TicketType ticketType) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(ticketType);
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
			session.delete(session.get(TicketType.class, id));
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
