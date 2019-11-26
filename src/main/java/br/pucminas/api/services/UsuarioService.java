package br.pucminas.api.services;

import java.util.Optional;

import br.pucminas.api.entities.Usuario;

public interface UsuarioService {
	
	Optional<Usuario> buscarPorEmail(String email);

}
