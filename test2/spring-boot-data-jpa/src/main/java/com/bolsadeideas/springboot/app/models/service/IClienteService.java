package com.bolsadeideas.springboot.app.models.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bolsadeideas.springboot.app.models.entity.Cliente;

public interface IClienteService {

public List<Cliente> findAll();



	//Paginacion
	public Page<Cliente> findAll(Pageable pageable);
	
	//Guardar nuevo cliente en la base de datos 
	public void save (Cliente cliente);

	// editar un cliente 
	public Cliente findOne(Long id);
	
	
	//eliminar 
	
	public void delete(Long id);
	
}
