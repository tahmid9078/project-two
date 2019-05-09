package com.ttv.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ttv.daos.RoleDao;
import com.ttv.models.Role;

@Service
public class RoleService {
	@Autowired
	private RoleDao roleDao;
	
	public Role add(Role role) {
		return roleDao.add(role);
	}

	public List<Role> findAll() {
		return roleDao.findAll();
	}

	public Role findById(Long id) {
		return roleDao.findById(id);
	}

	public void update(Role role) {
		roleDao.update(role);
	}

	public void deleteById(Long id) {
		roleDao.deleteById(id);
	}
}
