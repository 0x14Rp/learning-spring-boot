package com.bolsadeideas.springboot.app.models.service;

import java.io.IOException;
import java.net.MalformedURLException;


import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public interface IUploadFileService {

	
	//mostrar la imagen 
	public Resource load(String filename) throws MalformedURLException;
	
	
	
	// nombre unico de la imagen
	public String copy(MultipartFile file) throws IOException;
	
	
	
	// retorna un boolean para saber si lo elimino o no
	public boolean delete(String filename);
	
	
	//borrar directorio uploads
	
	public void deleteAll();
	
	//crear nuevamente el directorio
	
	public void init() throws IOException;
	
	
}
