package com.sgetejb.facade;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.sgetejb.dao.ClienteDAO;
import com.sgetejb.model.Cliente;

@Stateless
public class ClienteFacadeImp implements ClienteFacade{

	@EJB
	private ClienteDAO clienteDAO;
	
	@Override
	public void save(Cliente cliente) {
		isClienteWithAllData(cliente);
		
		clienteDAO.save(cliente);
	}

	@Override
	public Cliente update(Cliente cliente) {
		isClienteWithAllData(cliente);
		
		return clienteDAO.update(cliente);
	}

	@Override
	public void delete(Cliente cliente) {
		clienteDAO.delete(cliente);
	}

	@Override
	public Cliente find(int entityID) {
		
		return clienteDAO.find(entityID);
	}

	private void isClienteWithAllData(Cliente cliente){
		boolean hasError = false;
		
		if(cliente == null){
			hasError = true;
		}
		
		if(cliente.getNome() == null || cliente.getNome().trim().isEmpty()){
			hasError = true;
		}		
		
		if(hasError){
			throw new IllegalArgumentException("Falta informações no Cliente!");
		}
		
		
	}
}
