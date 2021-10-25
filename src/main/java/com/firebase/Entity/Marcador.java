package com.firebase.Entity;

import lombok.Data;

@Data
public class Marcador {
	
	private String id;
	private Boolean activo;
	private Double latitud;
	private Double longitud;
	private String nombre;
    private String horaInicio;
	private String horaActualizacion;

}
