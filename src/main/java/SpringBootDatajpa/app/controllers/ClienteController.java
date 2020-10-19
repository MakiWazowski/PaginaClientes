package SpringBootDatajpa.app.controllers;

import java.lang.ProcessBuilder.Redirect;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import SpringBootDatajpa.app.models.dao.IClienteDao;
import SpringBootDatajpa.app.models.entity.Cliente;
import SpringBootDatajpa.app.models.service.IClienteService;
import SpringBootDatajpa.app.util.paginator.PageRender;

@Controller
@SessionAttributes("cliente")
public class ClienteController {

	
	//atributo del cliente dao para poder realizar la consulta
	@Autowired
	//@Qualifier("clienteDaoJPA")
	//sustituimos la inyeccion de IClienteDao por IClienteService
	private IClienteService clienteService;
	
	//metodo para listar los clientes
	@RequestMapping(value="/listar",method= RequestMethod.GET)
	public String listar(@RequestParam(name="page", defaultValue="0") int page,Model model) {
		
		//implementamos el paginador en listar
		Pageable pageRequest = PageRequest.of(page,5);
		Page<Cliente> clientes = clienteService.findAll(pageRequest);
		
		PageRender<Cliente> pageRender = new PageRender<>("/listar", clientes);
		
		model.addAttribute("titulo","Listado de clientes");
		//sustituimos clienteService.findAll() por clientes (que tiene la paginacion)
		model.addAttribute("clientes",clientes);
		//añadimos el paginador a la vista
		model.addAttribute("page", pageRender);
		return "listar";
	}
	
	
	//metodo para mostrar el formulario al usuario 
	@RequestMapping(value="/form",method= RequestMethod.GET)
	//pasamos los datos a la vista
	public String crear(Map<String,Object> model) {
		//creamos la instancia del objeto entidad que esta mappeado a la bbdd y al formulariob (es bidireccional)
		Cliente cliente = new Cliente();
		model.put("cliente", cliente);
		model.put("titulo", "Formulario de Cliente");
		return "form";
	}
	
	//metodo para procesar datos enviados por el usuario con el submit
	@RequestMapping(value="/form",method= RequestMethod.POST)
	public String guardar(@Valid Cliente cliente,BindingResult result,Model model,RedirectAttributes flash,SessionStatus status) {
		
		//si falla vuelve al formulario
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Cliente");
			return "form";
		}
		
		//mensaje flash si el cliente se guarda tras ser editado o creado
		String mensajeFlash = (cliente.getId() != null)? "Cliente editado con exito" : "Cliente creado con exito";
		
		//guardamos al objeto
		clienteService.save(cliente);
		//elimina el objeto cliente de la sesion para terminar con el proceso 
		status.setComplete();
		//añadimos mensaje flash
		flash.addFlashAttribute("success", mensajeFlash);
		//retornamos la vista de listado de clientes
		return "redirect:listar";
	}
	
	//metodo para editar 
	@RequestMapping(value="/form/{id}")
	//pasamos el id como argumento, seria un path variable que maneja string
	public String editar(@PathVariable(value="id") Long id , Map<String,Object> model, RedirectAttributes flash ) {
		
		Cliente cliente = null;
		
		if(id>0) {
			cliente = clienteService.findOne(id);
			if(cliente==null) {
			//añadimos mensaje flash
			flash.addFlashAttribute("error", "El ID del cliente no existe en la BBDD");
			return "redirect:/listar";
			}
		}
		else {
			//añadimos mensaje flash
			flash.addFlashAttribute("error", "El ID del cliente no puede ser 0");
			return "redirect:/listar";
		}
		model.put("cliente", cliente);
		model.put("titulo", "Editar Cliente");
		return "form";
	}
	
	
	@RequestMapping(value="/eliminar/{id}")
	public String eliminar(@PathVariable(value="id") Long id, RedirectAttributes flash ) {
	 
		//si el id es mayor que 0 se elimina
		if(id>0) {
			clienteService.delete(id);
			//añadimos mensaje flash
			flash.addFlashAttribute("success", "Cliente eliminado con exito");
		}
		return "redirect:/listar";
		
	}
}
