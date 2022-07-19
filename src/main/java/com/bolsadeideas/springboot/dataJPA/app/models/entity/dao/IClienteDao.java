package com.bolsadeideas.springboot.dataJPA.app.models.entity.dao;

import java.util.List;

import com.bolsadeideas.springboot.dataJPA.app.models.entity.Cliente;

public interface IClienteDao {

	public List<Cliente> findAll();
	
	public void save(Cliente cliente);
}
