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

import com.ttv.daos.TicketDao;
import com.ttv.models.Ticket;

@Repository
@Transactional
@EnableTransactionManagement
public class TicketDaoImpl implements TicketDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Ticket add(Ticket ticket) {
		Session session = sessionFactory.getCurrentSession();
		session.save(ticket);
		return ticket;
	}

	@Override
	public List<Ticket> findAll() {
		Session session = sessionFactory.getCurrentSession();
		List<Ticket> tickets = session.createQuery("FROM Ticket").list();
		return tickets;
	}

	@Override
	public Ticket findById(Long id) {
		Session session = sessionFactory.getCurrentSession();
		Ticket ticket = (Ticket) session.get(Ticket.class, id);
		return ticket;
	}

	@Override
	public void update(Ticket ticket) {
		Session session = sessionFactory.getCurrentSession();
		session.update(ticket);

	}

	@Override
	public void deleteById(Long id) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(session.get(Ticket.class, id));

	}

}
