package com.evaluacion.ledesma.facadeImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evaluacion.ledesma.facade.ICliente;
import com.evaluacion.ledesma.modelo.Cliente;
import com.evaluacion.ledesma.repositorio.ClienteRepositorio;

@Service
public class ClienteDAO implements ICliente{
	@Autowired
	private ClienteRepositorio Clir;

	@Override
	public List<Cliente> listarTodosLosClientes() {
		return Clir.findAll();
	}

	@Override
	public Cliente guardarCliente(Cliente cliente) {
		return Clir.save(cliente);
	}

	@Override
	public Cliente obtenerClientePorId(Integer Numero_documento) {
		return Clir.findById(Numero_documento).get();
	}

	@Override
	public Cliente actualizarCliente(Cliente cliente) {
		return Clir.save(cliente);
	}

	@Override
	public void eliminarCliente(Integer Numero_documento) {
		Clir.deleteById(Numero_documento);
		
	}

}
