package com.parqueadero.Parqueadero.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.parqueadero.Parqueadero.entity.Usuario;
import com.parqueadero.Parqueadero.exception.EmailExistsException;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByEmail(String email);
    @Transactional
    @Modifying
    @Query("INSERT INTO Usuario( email, contrasena) VALUES (:#{#usuario.email}, :#{#usuario.contrasena})")
    Usuario registrarNuevoUsuario(Usuario usuario) throws EmailExistsException;
    List<Usuario> obtenerTodosLosUsuarios();
    Usuario obtenerUsuarioPorId(Long id);
    boolean existsByEmail(String email);
}