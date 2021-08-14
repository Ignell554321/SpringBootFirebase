package com.firebase.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.firebase.Entity.Marcador;
import com.firebase.firebase.Iniciarfirebase;
import com.firebase.services.IPostManagementService;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;

@Service
public class PostManagementServiceImpl implements IPostManagementService {

	
	@Autowired
	private Iniciarfirebase firebase;
	
	@Override
	public List<Marcador> listar() {
		
	      List<Marcador> response = new ArrayList<>();
	      Marcador marcador;

	        ApiFuture<QuerySnapshot> querySnapshotApiFuture = getCollection().get();
	        
	        try {
	        	
	            for (DocumentSnapshot doc : querySnapshotApiFuture.get().getDocuments()) {
	            	
	            	marcador=new Marcador();
	            	marcador.setId(doc.getId());
	            	marcador.setNombre(doc.getString("nombre"));
	            	marcador.setActivo(doc.getBoolean("activo"));
	            	marcador.setLatitud( doc.getDouble("latitud"));
	            	marcador.setLongitud(doc.getDouble("longitud"));
	                response.add(marcador);
	            }
	            return response;
	        } catch (Exception e) {
	        	System.out.println(e.getMessage());
	            return null;
	        }
	        
	        
	}

	@Override
	public Boolean crear(Marcador entity) {
		
		Map<String,Object>docData=new HashMap<>();
		docData.put("title", entity.getLatitud());
		docData.put("", entity.getLatitud());
		
		Marcador marcador=new Marcador();
		marcador.setActivo(true);
		/*marcador.setLatitud("3.12");
		marcador.setLongitud("5.311");*/
		marcador.setNombre("prueba");
		
		CollectionReference marcadores= firebase.getFirestore().collection("Marcador");
		ApiFuture<WriteResult> writeResultApiFuture=marcadores.document().create(entity);
		
		if(null!=writeResultApiFuture){
			return Boolean.TRUE;
			}
		return Boolean.FALSE;

	}
	
    @Override
    public Boolean editar(String id, Marcador marcador) {
        ApiFuture<WriteResult> writeResultApiFuture = getCollection().document(id).set(marcador);
        try {
            if(null != writeResultApiFuture.get()){
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }
    
    private CollectionReference getCollection() {
        return firebase.getFirestore().collection("Marcador");
    }
    

}
