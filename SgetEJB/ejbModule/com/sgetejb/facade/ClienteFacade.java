package com.sgetejb.facade;

import java.util.List;

import javax.ejb.Local;

import com.sgetejb.model.Cliente;

@Local
public interface ClienteFacade {
	
	public abstract void save(Cliente cliente);
	public abstract Cliente update(Cliente cliente);
	public abstract void delete(Cliente cliente);
	public abstract Cliente find(int entityID);
	public abstract List<Cliente> findAll();


}
