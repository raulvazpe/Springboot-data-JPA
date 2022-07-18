package com.bolsadeideas.springboot.dataJPA.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bolsadeideas.springboot.dataJPA.app.models.entity.dao.IClienteDao;

@Controller
public class ClienteController {
	
	@Autowired
	private IClienteDao clienteDao;
	
	@GetMapping("/listar")
	public String listar(Model model) {
		model.addAttribute("titulo", "Lista de clientes");
		model.addAttribute("clientes", clienteDao.findAll());
	
		return "listar";
	}
	
}
