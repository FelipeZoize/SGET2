package com.sget.mb;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import com.sgetejb.facade.ClienteFacade;
import com.sgetejb.model.Cliente;

@ManagedBean
@RequestScoped
public class ClienteMB {

	@EJB
	private ClienteFacade clienteFacade;
	
	private static final String LIST_ALL_CLIENTES = "listAllClientes";
	private static final String STAY_IN_THE_SAME_PAGE = null;
	
	private Cliente cliente;
	
	public String createCliente(){
		try {
			clienteFacade.save(cliente);
		} catch (EJBException e) {
			sendErrorMessageToUser("Erro, verificar se todos os campos estão corretos!");
			return STAY_IN_THE_SAME_PAGE;
		}
		
		sendInfoMessageToUser("Operação Realizada com Sucesso");
		
		return LIST_ALL_CLIENTES;
	}
	
	public void sendInfoMessageToUser(String message){
		FacesContext context = getContext();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,message,message));
	}
	public void sendErrorMessageToUser(String message){
		FacesContext context = getContext();
		context.addMessage(null, new FacesMessage (FacesMessage.SEVERITY_ERROR, message, message));
	}
	
	public FacesContext getContext(){
		FacesContext context = FacesContext.getCurrentInstance();
		return context;
	}

	public Cliente getCliente() {
		if (cliente == null){
			cliente = new Cliente();
		}
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
}
