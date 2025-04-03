package com.Bcript.safety.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Bcript.safety.dtos.UsuarioDTO;
import com.Bcript.safety.entities.Usuario;
import com.Bcript.safety.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public UsuarioDTO salvarUsuario(UsuarioDTO dto) {
		Usuario user = new Usuario();
		user.setEmail(dto.getEmail());
		user.setSenha(passwordEncoder.encode(dto.getSenha()));
		user = usuarioRepository.save(user);
		return new UsuarioDTO(user);
	}
	
	//indica que esse método retorna um valor de tipo booleano (ou seja, true ou false).
	public boolean autenticarUsuario(UsuarioDTO dto) {
		Usuario usuario = usuarioRepository.findByEmail(dto.getEmail());
		
		if(usuario == null) {
			return false;
		}
		//compara o que eu to recebendo com o que ta no banco, verifica se a senha esta compativel
		return passwordEncoder.matches(dto.getSenha(), usuario.getSenha());
	}
	
}
