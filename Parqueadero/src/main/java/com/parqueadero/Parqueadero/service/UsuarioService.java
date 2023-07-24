package com.parqueadero.Parqueadero.service;


import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.parqueadero.Parqueadero.entity.Usuario;
import com.parqueadero.Parqueadero.exception.EmailExistsException;
import com.parqueadero.Parqueadero.repository.UsuarioRepository;

@Service
public class UsuarioService {
	private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    
 // Método para verificar si el email ya existe en la base de datos
    private boolean emailExist(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    // Método para registrar un nuevo usuario
    public Usuario registrarNuevoUsuario(String email, String password) throws EmailExistsException {
        if (emailExist(email)) {
            throw new EmailExistsException("Existe una cuenta con esa dirección de correo electrónico: " + email);
        }

        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setPassword(password);

        return usuarioRepository.save(usuario);
    }
	

    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado con ID: " + id));
    }

}