package com.firebase.task;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.firebase.Entity.Marcador;
import com.firebase.services.impl.MarcadorServiceImpl;

@Component
public class ActualizarEstadoMarcador {
	
	@Autowired
	private MarcadorServiceImpl marcadorServiceImpl;
	
	@Scheduled(fixedRate = 60000)
	public void tarea(){
		
		 DateFormat dateFormat = new SimpleDateFormat("HH:mm");
		 Date date = new Date();
		 System.out.println("Hora actual: " + dateFormat.format(date));
		  /*
		  Calendar calendar = Calendar.getInstance();
		  calendar.setTime(date); //tuFechaBase es un Date;
		  calendar.add(Calendar.MINUTE, 10); //minutosASumar es int.
		 // calendar.add(Calendar.HOUR,   horasASumar); //horasASumar es int.
		  //lo que más quieras sumar
		  Date fechaSalida = calendar.getTime(); //Y ya tienes la fecha sumada.*/
		
		List<Marcador>lista= marcadorServiceImpl.listar();
		
		for (Marcador marcador : lista) {
			
			if(marcador.getHoraActualizacion().equals(dateFormat.format(date))) {
				marcador.setActivo(false);
				marcadorServiceImpl.editar(marcador.getId(), marcador);
				
				System.out.println("Marcador: " + marcador.getId()+" Actualizado");
			}
		}
		  
		
		
	}

}
