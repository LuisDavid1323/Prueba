package com.evaluacion.ledesma.facade;

import java.util.List;

import com.evaluacion.ledesma.modelo.Cliente;


public interface ICliente {

public List<Cliente> listarTodosLosClientes();
	
	public Cliente guardarCliente(Cliente cliente);
	
	public Cliente obtenerClientePorId(Integer Numero_documento);
	
	public Cliente actualizarCliente(Cliente cliente);
	
	public void eliminarCliente(Integer Numero_documento);
		
	
}
