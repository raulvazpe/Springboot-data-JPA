package com.bolsadeideas.springboot.dataJPA.app.controllers;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bolsadeideas.springboot.dataJPA.app.models.entity.Cliente;
import com.bolsadeideas.springboot.dataJPA.app.models.services.IClienteService;
import com.bolsadeideas.springboot.dataJPA.app.util.paginador.PageRender;

@Controller
public class ClienteController {
	
	@Autowired
	private IClienteService clienteService;
	
	@GetMapping("/listar")
	public String listar(@RequestParam (name= "page", defaultValue ="0") int page, Model model) {
		
		//indicamos que queremos buscar 5 registros de tipo Page de la clase Cliente
		Page<Cliente> clientes = clienteService.findAll(PageRequest.of(page, 5));
		PageRender<Cliente> pageRender = new PageRender<Cliente>("/listar", clientes);
		
		model.addAttribute("page", pageRender);
		model.addAttribute("titulo", "Lista de clientes");
		model.addAttribute("clientes", clientes);
	
		return "listar";
	}
	
	@GetMapping("/form")
	public String crear(Model model) { //MOSTRAMOS EL FORMULARIO
		Cliente cliente = new Cliente();
		model.addAttribute("cliente", cliente);
		model.addAttribute("titulo", "Formulario de Cliente");
		return "form";
	}
	
	@GetMapping("/form/{id}")
	public String editar(@PathVariable Long id, Model model, RedirectAttributes message) { //MOSTRAMOS EL FORMULARIO
		
		Cliente cliente = null;
		
		//Si el id del cliente es mayor que 0 le enviamos al metodo de buscar el id, si no, nos retornara a la lista
		if(id>0) {
			cliente = clienteService.findOne(id);
			if(cliente == null) {
				message.addFlashAttribute("error", "El cliente no existe en la BDD");
				return "redirect:/listar";
			}
		}else{
			return "redirect: /listar";
		}
		
		model.addAttribute("cliente", cliente); // enviaremos al cliente que ha encontrado
		model.addAttribute("titulo", "Editar Cliente");
		return "form";
	}
	
	
	@PostMapping("/form")
	public String guardar(@Valid Cliente cliente, BindingResult result,Model model, RedirectAttributes message) {
		
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Cliente");
			return "form";
		}

		clienteService.save(cliente);
		if(cliente.getId()==0L) {
			message.addFlashAttribute("success", "Creado correctamente");
		}else {
			message.addFlashAttribute("success", "Editado correctamente");
		}

		return "redirect:listar";
	}
	
	@GetMapping("form/eliminar/{id}")
	public String delete(@PathVariable Long id, RedirectAttributes message) {
		
		if(id>0) {
			clienteService.delete(id);
			message.addFlashAttribute("success", "eliminado correctamente");
		}
	
	return "redirect:/listar";
	}
	
}
