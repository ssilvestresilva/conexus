package br.pucminas.api.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.pucminas.api.entities.Usuario;
import br.pucminas.api.repositories.UsuarioRepository;
import br.pucminas.api.services.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	
	private static final Logger log = LoggerFactory.getLogger(UsuarioServiceImpl.class);

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Usuario persistir(Usuario funcionario) {
		log.info("Persistindo funcionário: {}", funcionario);
		return this.usuarioRepository.save(funcionario);
	}
	
	public Optional<Usuario> buscarPorEmail(String email) {
		log.info("Buscando funcionário pelo email {}", email);
		return Optional.ofNullable(this.usuarioRepository.findByEmail(email));
	}

}
