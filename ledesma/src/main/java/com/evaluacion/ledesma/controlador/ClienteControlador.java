package com.evaluacion.ledesma.controlador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.evaluacion.ledesma.facadeImpl.ClienteDAO;
import com.evaluacion.ledesma.modelo.Cliente;





@Controller
public class ClienteControlador {

	
	@Autowired
	private ClienteDAO CliDAO;
	
	/* listarCliente */
	@GetMapping({ "/clientes", "/" })

	public String listarCliente(Model modelo) {
		modelo.addAttribute("clientes", CliDAO.listarTodosLosClientes());
		return "clientes";
	}

	@GetMapping("/listar")

	public ResponseEntity<Map<String, Object>> allClientes() {
		Map<String, Object> respon = new HashMap<String, Object>();

		try {
			List<Cliente> lstC = CliDAO.listarTodosLosClientes();

			respon.put("Mensaje", "Se listo correctamente los clientes");
			respon.put("data", lstC);
			respon.put("Status", HttpStatus.OK);

		} catch (Exception e) {
			respon.put("Mensaje", "Fallo el listar de los clientes");
			respon.put("Status", HttpStatus.BAD_REQUEST);
			return new ResponseEntity<>(respon, HttpStatus.BAD_REQUEST);

		}
		return new ResponseEntity<>(respon, HttpStatus.OK);
	}

	/* listarCliente */
	
	/* CrearCliente */
	@PostMapping("/crear")
	public ResponseEntity<Map<String, Object>> createUsuario(@RequestBody Map<String, Object> request) {

		Map<String, Object> respon = new HashMap<String, Object>();
   /*Mostrar_en_Posman*/
		try {
			Cliente cli = new Cliente();
			cli.setApellido((String) request.get("Apellido"));
			cli.setCiudad((String) request.get("Ciudad"));
			cli.setCorreo((String) request.get("Correo"));
			cli.setFecha_Nacimiento((String) request.get("Fecha_Nacimiento"));
			cli.setNombre((String) request.get("Nombre"));
			cli.setOcupacion((String) request.get("Ocupacion"));
			cli.setTelefono((String) request.get("Telefono"));
			this.CliDAO.guardarCliente(cli);
			respon.put("Mensaje", "Correcto");
			respon.put("data", request);
			respon.put("Status", HttpStatus.OK);
		} catch (Exception e) {
			respon.put("Mensaje", "incorrecto");
			return new ResponseEntity<>(respon, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(respon, HttpStatus.OK);
	}
	  /*Mostrar_en_Posman*/

	@GetMapping("/clientes/nuevo")
	public String mostrarFormularioDeRegistrtarUsuarios(Model modelo) {
		Cliente cliente = new Cliente();
		modelo.addAttribute("clientes", cliente);
		return "Crear_Cliente";
	}

	@PostMapping("/clientes")
	public String guardarUsuario(@ModelAttribute("cliente") Cliente cliente) {
		CliDAO.guardarCliente(cliente);
		return "redirect:/clientes";
	}
	/* CrearCliente */
	
	/* ActualizarCliente */
	@GetMapping(path = "/actualizar/{Numero_documento}")
	public ResponseEntity<Map<String, Object>> actualizar(@PathVariable String Numero_documento,
			@RequestBody Map<String, Object> request) {
		Map<String, Object> respon = new HashMap<String, Object>();
		/*Mostrar_en_Posman*/
		try {
			Cliente Cli = this.CliDAO.obtenerClientePorId(Integer.parseInt(Numero_documento));

			Cli.setApellido((String) request.get("Apellido"));
			Cli.setCiudad((String) request.get("Ciudad"));
			Cli.setCorreo((String) request.get("Correo"));
			Cli.setFecha_Nacimiento((String) request.get("Fecha_Nacimiento"));
			Cli.setNombre((String) request.get("Nombre"));
			Cli.setOcupacion((String) request.get("Ocupacion"));
			Cli.setTelefono((String) request.get("Telefono"));
			this.CliDAO.guardarCliente(Cli);
			respon.put("Message", "La base de datos se ha actualizado");
			respon.put("data", request);
			respon.put("Status", HttpStatus.OK);

		} catch (Exception e) {
			respon.put("Message", "La base de datos no se ha actualizado");
			respon.put("Status", HttpStatus.BAD_REQUEST);
			return new ResponseEntity<>(respon, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(respon, HttpStatus.OK);
	}
	/*Mostrar_en_Posman*/
	
	@GetMapping("/clientes/editar/{id}")
	public String mostrarFormularioDeEditar(@PathVariable Integer id, Model modelo) {
		modelo.addAttribute("clientes", CliDAO.obtenerClientePorId(id));
		return "Editar_Cliente";
	}

	@PostMapping("/clientes/{id}")
	public String actualizarEstudiante(@PathVariable Integer id, @ModelAttribute("usuario") Cliente cliente,
			Model modelo) {
		Cliente Cliente = CliDAO.obtenerClientePorId(id);
		Cliente.setNumero_documento(id);
		Cliente.setNombre(cliente.getNombre());
		Cliente.setApellido(cliente.getApellido());
		Cliente.setFecha_Nacimiento(cliente.getFecha_Nacimiento());
		Cliente.setCiudad(cliente.getCiudad());
		Cliente.setCorreo(cliente.getCorreo());
		Cliente.setTelefono(cliente.getTelefono());
		Cliente.setOcupacion(cliente.getOcupacion());

		CliDAO.actualizarCliente(Cliente);
		return "redirect:/clientes";
	}
	
	/* ActualizarCliente */
	
	/*EliminarCliente*/
	@GetMapping (path ="/eliminar/{Numero_documento}")
	public ResponseEntity <Map<String, Object>> eliminarclient(
			@PathVariable String Numero_documento){
		 Map<String, Object> respon= new HashMap<String,Object>();  
			
		 try {
			Cliente c =this.CliDAO.obtenerClientePorId(Integer.parseInt(Numero_documento));
			this.CliDAO.actualizarCliente(c);
			respon.put("Mensaje","Eliminacion exitosa");
			List<Cliente> lstC= this.CliDAO.listarTodosLosClientes();
			respon.put("data", lstC);
			respon.put("status", HttpStatus.OK);
		} catch (Exception e) {
			respon.put("Mensaje", "Eliminacion fallida");
			respon.put("status", HttpStatus.BAD_REQUEST);
		}
		 
		 
		 return new ResponseEntity<>(respon, HttpStatus.OK);

	}
	
	@GetMapping("/clientes/{id}")
	public String eliminarCliente(@PathVariable Integer id) {
		CliDAO.eliminarCliente(id);
		return "redirect:/clientes";
	}
	
	/*EliminarCliente*/
	
	
	
}
