package com.parqueadero.Parqueadero.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parqueadero.Parqueadero.entity.Parqueadero;
import com.parqueadero.Parqueadero.entity.Usuario;
import com.parqueadero.Parqueadero.exception.CamposNulosOVaciosException;
import com.parqueadero.Parqueadero.exception.ElementoNoEncontradoException;
import com.parqueadero.Parqueadero.repository.ParqueaderoRepository;

@Service
public class ParqueaderoService {
    private final ParqueaderoRepository parqueaderoRepository;
    private final UsuarioService usuarioService;

    @Autowired
    public ParqueaderoService(ParqueaderoRepository parqueaderoRepository,UsuarioService usuarioService) {
        this.parqueaderoRepository = parqueaderoRepository;
        this.usuarioService = usuarioService;
    }

    //Método para crear el parqueadero
    public Parqueadero crearParqueadero(Parqueadero parqueadero) {
        
        if (parqueadero.getNombre() == null || parqueadero.getNombre().isEmpty()) {
            throw new CamposNulosOVaciosException("El nombre del parqueadero no puede estar vacío.");
        }
        if (parqueadero.getCapacidad() <= 0) {
            throw new CamposNulosOVaciosException("La capacidad del parqueadero debe ser mayor a cero.");
        }
        return parqueaderoRepository.save(parqueadero);
    }

    public List<Parqueadero> obtenerTodosLosParqueaderos() {
        return parqueaderoRepository.findAll();
    }

    public Parqueadero obtenerParqueaderoPorId(Long id) {
        return parqueaderoRepository.findById(id)
                .orElseThrow(() -> new ElementoNoEncontradoException("Parqueadero no encontrado con ID: " + id));
    }

    public void eliminarParqueaderoPorId(Long id) {
        parqueaderoRepository.deleteById(id);
    }

    public void agregarSocio(Long parqueaderoId, Usuario socio) {
        Parqueadero parqueadero = parqueaderoRepository.findById(parqueaderoId)
                .orElseThrow(() -> new NoSuchElementException("Parqueadero no encontrado con ID: " + parqueaderoId));

        parqueadero.getSocios().add(socio);

        parqueaderoRepository.save(parqueadero);
    }
    
    /*
    // Método para asociar un socio a un parqueadero
    public Parqueadero asociarSocio(Long parqueaderoId, Long socioId) {
        Parqueadero parqueadero = obtenerParqueaderoPorId(parqueaderoId);
        Usuario socio = usuarioService.obtenerUsuarioPorId(socioId);

        if (socio == null) {
            throw new ElementoNoEncontradoException("Socio no encontrado con ID: " + socioId);
        }

        agregarSocio(parqueaderoId,socio);
        return parqueaderoRepository.save(parqueadero);
    } */
}