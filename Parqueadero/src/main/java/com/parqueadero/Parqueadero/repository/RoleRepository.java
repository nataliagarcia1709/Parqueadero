package com.parqueadero.Parqueadero.repository;

import java.util.List;

import com.parqueadero.Parqueadero.entity.Role;

public interface RoleRepository {

	Object findByName(String string);

	List<Role> findAll();

}
