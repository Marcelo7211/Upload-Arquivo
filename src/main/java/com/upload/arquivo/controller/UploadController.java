package com.upload.arquivo.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.upload.arquivo.entity.Upload;
import com.upload.arquivo.repository.UploadRepository;

@RestController
@RequestMapping("/upload")
@CrossOrigin(value = "*", allowedHeaders = "*")
public class UploadController {

	@Autowired
	private UploadRepository repository;

	@GetMapping
	public ResponseEntity<List<Upload>> getAll() {
		return ResponseEntity.ok(repository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Upload> getById(@PathVariable long id) {
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.badRequest().build());
	}
	
	@GetMapping("/imagem/{id}")
	public ResponseEntity<MultipartFile> getByIdImagem(@PathVariable long id) {

		return null;
	}

	@PostMapping
	ResponseEntity<Upload> post(MultipartFile file, String json) throws IOException {
		
		//Criando um objeto do tipo Upload
		ObjectMapper mapper = new ObjectMapper();	    	
		Upload arquivo = mapper.readValue(json, Upload.class);

		//Salvar no servidor
		OutputStream out = new FileOutputStream("C:/Users/celin/Desktop/Teste/arquivo--" + file.getOriginalFilename());
		out.write(file.getBytes());
		out.close();
		
		//Atualizar a model e salvar no banco 
		arquivo.setImagem(file.getBytes());
		
		return ResponseEntity.ok(repository.save(arquivo));		
	}
	
	

}
