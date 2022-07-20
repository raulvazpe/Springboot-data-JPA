package com.bolsadeideas.springboot.dataJPA.app.controllers;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.bolsadeideas.springboot.dataJPA.app.models.entity.Cliente;
import com.bolsadeideas.springboot.dataJPA.app.models.services.IClienteService;

@Controller
public class ClienteController {
	
	@Autowired
	private IClienteService clienteService;
	
	@GetMapping("/listar")
	public String listar(Model model) {
		model.addAttribute("titulo", "Lista de clientes");
		model.addAttribute("clientes", clienteService.findAll());
	
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
	public String editar(@PathVariable Long id, Model model) { //MOSTRAMOS EL FORMULARIO
		
		Cliente cliente = null;
		
		//Si el id del cliente es mayor que 0 le enviamos al metodo de buscar el id, si no, nos retornara a la lista
		if(id>0) {
			cliente = clienteService.findOne(id);
		}else{
			return "redirect: /listar";
		}
		
		model.addAttribute("cliente", cliente); // enviaremos al cliente que ha encontrado
		model.addAttribute("titulo", "Editar Cliente");
		return "form";
	}
	
	
	@PostMapping("/form")
	public String guardar(@Valid Cliente cliente, BindingResult result,Model model) {
		
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Cliente");
			return "form";
		}

		clienteService.save(cliente);
		return "redirect:listar";
	}
	
	@GetMapping("form/eliminar/{id}")
	public String delete(@PathVariable Long id) {
		
		if(id>0) {
			clienteService.delete(id);
		}
	
	return "redirect:/listar";
	}
	
}
