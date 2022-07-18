package com.bolsadeideas.springboot.dataJPA.app.models.entity.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bolsadeideas.springboot.dataJPA.app.models.entity.Cliente;


@Repository
public class ClienteDaoImpl implements IClienteDao {

	@PersistenceContext
	private EntityManager em; //necesario para realizar todas las operaciones en la base de datos a nivel de Objeto
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true) //Solo lectura
	@Override
	public List<Cliente> findAll() {

		return em.createQuery("from Cliente").getResultList();
	}

}
