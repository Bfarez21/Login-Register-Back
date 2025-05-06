package com.practicabf.jwt.loginregisterformback.repository;

import com.practicabf.jwt.loginregisterformback.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // servicios por atributos lo que quermos encontrar...
    Optional<Usuario> findByUsername(String correo);
}
