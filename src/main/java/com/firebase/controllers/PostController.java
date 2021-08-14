package com.firebase.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.firebase.Entity.Marcador;
import com.firebase.services.impl.PostManagementServiceImpl;


@Controller
public class PostController {
	
	@Autowired
	private PostManagementServiceImpl postManagementServiceImpl;
	
	@GetMapping(value = "/Listar")
	public ResponseEntity<java.util.List<Marcador>> List() {
		
	   return new ResponseEntity<java.util.List<Marcador>>(postManagementServiceImpl.listar(),HttpStatus.OK);
	}
	
	@PostMapping(value = "/Guardar")
	public ResponseEntity<Object> Guardar(@RequestBody Marcador marcador) {
		
	   return new ResponseEntity<Object>(postManagementServiceImpl.crear(marcador),HttpStatus.OK);
		
	}
	
    @PutMapping(value = "/Editar/{id}")
	public ResponseEntity<Object> edit(@PathVariable(value = "id") String id, @RequestBody Marcador marcador){
    	
	   return new ResponseEntity<Object>(postManagementServiceImpl.editar(id,marcador), HttpStatus.OK);
	   
	}
	

}
