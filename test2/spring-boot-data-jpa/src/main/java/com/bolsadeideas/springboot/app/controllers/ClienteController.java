package com.bolsadeideas.springboot.app.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bolsadeideas.springboot.app.models.entity.Cliente;
import com.bolsadeideas.springboot.app.models.service.IClienteService;
import com.bolsadeideas.springboot.app.util.paginator.PageRender;

@Controller
@SessionAttributes("cliente")
public class ClienteController {
	

	@Autowired
	private IClienteService clienteService;
	
	
	//Controlador que maneja la pagina Home
	@GetMapping(value = {"","/","/home","/index"})
	public String HomePage(Model modelHome){
		modelHome.addAttribute("Titulo","Sistema Java Spring ");
		return "index";
	}	

	
	//Controlador para trabajar las consultas a la base seria un select de una base de datos
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	//paginacion
	public String listar(@RequestParam(name = "page", defaultValue = "0") int page,Model model) {
		
		Pageable pageRequest = PageRequest.of(page, 4);
		
		Page<Cliente> clientes = clienteService.findAll(pageRequest);
		
		
		PageRender<Cliente> pageRender = new PageRender<Cliente>("/listar", clientes);
		model.addAttribute("titulo", "Listado de clientes");
		model.addAttribute("clientes",clientes);
		model.addAttribute("page", pageRender);
		return "listar";
	}

	
	//Controlador vista para captar la información en formulario
	@RequestMapping(value = "/form")
	public String crear(Map<String, Object> model) {
		Cliente cliente = new Cliente();
		model.put("cliente", cliente);
		model.put("titulo", "Formulario de Cliente");

		return "form";
	}

	
	@RequestMapping(value="/form/{id}")
	public String editar(@PathVariable(value ="id") Long id, Map<String, Object> model, RedirectAttributes flash) {
		
		Cliente cliente = null;
		
		if (id > 0) {
			
			cliente = clienteService.findOne(id);
			if (cliente == null ) {
				
				 flash.addFlashAttribute("error", "El ID del cliente no existe en la base de datos!");
					return "redirect:/listar";
			}
			
		} else {
		
			 flash.addFlashAttribute("error", "El ID del cliente no puede ser cero!");
				return "redirect:/listar";
		}
		
		model.put("cliente", cliente);
		model.put("titulo", "Editar Cliente");
		return "form";
	}
	
	
	
	//Controlador que recibe la información del formulario y la inserta en la base de datos.
	// para las validaciones agregar @valid al metodo guardar que recibe el objeto
	// mapeado

	@RequestMapping(value = "/form", method = RequestMethod.POST)

	// bindingResult siempre va junto al objeto del formulario
	public String guardar(@Valid Cliente cliente, BindingResult result, Model model, RedirectAttributes flash, SessionStatus status) {

		// si contiene errores
		if (result.hasErrors()) {

			model.addAttribute("titulo", "Formulario de Cliente");
			return "form";

		}
		
		String mensajeFlash = (cliente.getId() != null)? "Cliente editado con exito" : "Cliente creado con exito ";

		clienteService.save(cliente);
		status.setComplete();
		 flash.addFlashAttribute("success", mensajeFlash );
		return "redirect:listar";
	}
	
	@RequestMapping(value="/eliminar/{id}")
	public String eliminar(@PathVariable(value="id") Long id, RedirectAttributes flash) {
		
		if(id > 0) {
			clienteService.delete(id);
			 flash.addFlashAttribute("success", "Cliente eliminado con exito!");
		}
		return "redirect:/listar";
	}
}
