package com.sget.mb;

import java.util.List;

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
	private static final String FORM_CLIENTE = "createCliente";
	
	private Cliente cliente;
	
	/** String para ser usada no form de cadastrar/atualizar  */
	private String action = "Cadastrar";
	
	public String createCliente(){
		try {
			clienteFacade.save(cliente);
		} catch (EJBException e) {
			sendErrorMessageToUser("Erro, verificar se todos os campos estão corretos!");
			return STAY_IN_THE_SAME_PAGE;
		}
		
		sendInfoMessageToUser("Operação Realizada com Sucesso");
		
		cliente = null;
		
		return LIST_ALL_CLIENTES;
	}
	
	public String updateCliente(){
		return FORM_CLIENTE;
	}
	
	public List<Cliente> getAllClientes(){
		return clienteFacade.findAll();
	}
	
	public String deleteCliente(){
		try {
			clienteFacade.delete(cliente);
		} catch (EJBException e) {
			sendErrorMessageToUser("Erro ao deletar o cliente!");
			
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

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	
	
}
