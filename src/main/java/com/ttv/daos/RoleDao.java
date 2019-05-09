package com.ttv.daos;

import java.util.List;

import com.ttv.models.Role;

public interface RoleDao {
	public Role add(Role role);
	public List<Role> findAll();
	public Role findById(Long id);
	public void update(Role role);
	public void deleteById(Long id);
}
