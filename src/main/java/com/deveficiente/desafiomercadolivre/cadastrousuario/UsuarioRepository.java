package com.deveficiente.desafiomercadolivre.cadastrousuario;

import java.util.Optional;

import javax.validation.constraints.Email;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	public Optional<Usuario> findByEmail(@Email String email);
}
