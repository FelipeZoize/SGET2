package com.sgetejb.dao;

import javax.ejb.Stateless;

import com.sgetejb.dao.GenericDAO;
import com.sgetejb.model.Cliente;

@Stateless
public class ClienteDAO extends GenericDAO<Cliente> {

	public ClienteDAO() {
		super(Cliente.class);
	}

	public void delete(Cliente cliente){
		super.delete(cliente.getId(), Cliente.class);
	}
}
