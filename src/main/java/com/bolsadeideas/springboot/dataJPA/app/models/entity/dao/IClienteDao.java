package com.bolsadeideas.springboot.dataJPA.app.models.entity.dao;


import org.springframework.data.repository.CrudRepository;

import com.bolsadeideas.springboot.dataJPA.app.models.entity.Cliente;

public interface IClienteDao extends CrudRepository<Cliente, Long> {

	


	
}
