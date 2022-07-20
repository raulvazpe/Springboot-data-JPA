package com.bolsadeideas.springboot.dataJPA.app.models.entity.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.bolsadeideas.springboot.dataJPA.app.models.entity.Cliente;

@Repository
public class ClienteDaoImpl implements IClienteDao {

	@PersistenceContext
	private EntityManager em; // necesario para realizar todas las operaciones en la base de datos a nivel de
								// Objeto

	@SuppressWarnings("unchecked")
	@Override
	public List<Cliente> findAll() {

		return em.createQuery("from Cliente").getResultList();
	}

	@Override
	public void save(Cliente cliente) {
		
		/* Si el id del cliente es diferente a null y es mayor que 0 haremos un merge(actualiza) si no
		 * haremos un persist(crea) */
		if (cliente.getId() != 0L && cliente.getId() > 0) {
			em.merge(cliente);
		} else {
			em.persist(cliente);
		}

	}

	@Override
	public Cliente findOne(Long id) {
		return em.find(Cliente.class, id);
	}

	@Override
	public Cliente delete(Long id) {
		Cliente cliente = findOne(id);
		em.remove(cliente);
		return null;
	}

}
