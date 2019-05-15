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

import com.ttv.daos.TicketTypeDao;
import com.ttv.models.TicketType;

@Repository
@Transactional
@EnableTransactionManagement
public class TicketTypeDaoImpl implements TicketTypeDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public TicketType add(TicketType ticketType) {
		Session session = sessionFactory.getCurrentSession();
		session.save(ticketType);
		return ticketType;
	}

	@Override
	public List<TicketType> findAll() {
		Session session = sessionFactory.getCurrentSession();
		List<TicketType> ticketTypes = session.createQuery("FROM TicketType").list();
		return ticketTypes;
	}

	@Override
	public TicketType findById(Long id) {
		Session session = sessionFactory.getCurrentSession();
		TicketType ticketType = (TicketType) session.get(TicketType.class, id);
		return ticketType;
	}

	@Override
	public void update(TicketType ticketType) {
		Session session = sessionFactory.getCurrentSession();
		session.update(ticketType);

	}

	@Override
	public void deleteById(Long id) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(session.get(TicketType.class, id));

	}

}
