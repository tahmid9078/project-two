package com.ttv.daoImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ttv.daos.ticketDao;
import com.ttv.models.Ticket;

@Repository
@Transactional
public class TicketDaoImpl implements ticketDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Ticket add(Ticket ticket) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			session.save(ticket);
			transaction.commit();
				
		} catch(HibernateException e) {
			if(session != null) session.close();
			if(transaction != null) transaction.rollback();
			e.printStackTrace();
		}
		return ticket;
	}

	@Override
	public List<Ticket> findAll() {
		Session session = sessionFactory.getCurrentSession();
		List<Ticket> tickets = null;
		
		try {
			session.beginTransaction();
			tickets = session.createQuery("FROM Ticket").list();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return tickets;
	}

	@Override
	public Ticket findById(Long id) {
		Session session = sessionFactory.getCurrentSession();
		Ticket ticket = null;
		
		try {
			session.beginTransaction();
			ticket = (Ticket) session.get(Ticket.class, id);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return ticket;
	}

	@Override
	public Boolean update(Ticket ticket) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(ticket);
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
			session.delete(session.get(Ticket.class, id));
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
