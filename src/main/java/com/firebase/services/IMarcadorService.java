package com.firebase.services;

import java.util.List;

import com.firebase.Entity.Marcador;

public interface IMarcadorService {

	 List<Marcador> listar();
	 Boolean crear(Marcador entity);
	 Boolean editar(String id, Marcador entity);
}
