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

import com.ttv.daos.RoleDao;
import com.ttv.models.Role;

@Repository
@Transactional
@EnableTransactionManagement
public class RoleDaoImpl implements RoleDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Role add(Role role) {
		Session session = sessionFactory.getCurrentSession();
		session.save(role);
		return role;
	}

	@Override
	public List<Role> findAll() {
		Session session = sessionFactory.getCurrentSession();
		List<Role> roles = session.createQuery("FROM Role").list();
		session.getTransaction().commit();
		return roles;
	}

	@Override
	public Role findById(Long id) {
		Session session = sessionFactory.getCurrentSession();
		Role role = (Role) session.get(Role.class, id);
		return role;
	}

	@Override
	public void update(Role role) {
		Session session = sessionFactory.getCurrentSession();
		session.update(role);

	}

	@Override
	public void deleteById(Long id) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(session.get(Role.class, id));

	}

}
