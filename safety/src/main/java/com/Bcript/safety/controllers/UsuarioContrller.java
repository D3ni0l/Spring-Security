package com.Bcript.safety.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Bcript.safety.dtos.UsuarioDTO;
import com.Bcript.safety.services.UsuarioService;

import jakarta.validation.Valid;

@RequestMapping("usuario")
@RestController
public class UsuarioContrller {

	@Autowired
	UsuarioService usuarioService;
	
	@PostMapping(value = "/salvar")
	public ResponseEntity<?> salvar(@Valid @RequestBody UsuarioDTO dto){
	dto = usuarioService.salvarUsuario(dto);
		return ResponseEntity.ok(dto);	
	}
	
	@PostMapping(value = "/login")
	public ResponseEntity<?> login(@RequestBody UsuarioDTO dto){
		boolean login = usuarioService.autenticarUsuario(dto);
		
		if(login) {
			return ResponseEntity.ok("sucesso");
		} 
		
		else {
			return ResponseEntity.status(401).body("Email ou senha invalidos");
		}

	}
	
}
