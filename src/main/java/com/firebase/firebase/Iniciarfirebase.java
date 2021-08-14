package com.firebase.firebase;
import java.io.IOException;
import java.io.InputStream;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class Iniciarfirebase {


	@PostConstruct
	private void inicializador() throws IOException {
		InputStream serviceAccount = getClass().getClassLoader().getResourceAsStream("private-key-firestorage.json");
				
				
		FirebaseOptions options = new FirebaseOptions.Builder()
		  .setCredentials(GoogleCredentials.fromStream(serviceAccount))
		  .setDatabaseUrl("https://app-tesis-fb715.firebaseio.com/")
		  .build();
		
		FirebaseApp.initializeApp(options);
		
		if(FirebaseApp.getApps().isEmpty()){
			
			FirebaseApp.initializeApp();
			
		}
	}
	
	
	public Firestore getFirestore(){
		return FirestoreClient.getFirestore();
	}
	
	
	

}
