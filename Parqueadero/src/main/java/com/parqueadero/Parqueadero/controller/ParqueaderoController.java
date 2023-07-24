package com.parqueadero.Parqueadero.controller;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parqueadero.Parqueadero.entity.Parqueadero;
import com.parqueadero.Parqueadero.exception.CamposNulosOVaciosException;
import com.parqueadero.Parqueadero.exception.ElementoNoEncontradoException;
import com.parqueadero.Parqueadero.service.ParqueaderoService;

@RestController
@RequestMapping("/api/parqueaderos")
public class ParqueaderoController {
    private final ParqueaderoService parqueaderoService;

    @Autowired
    public ParqueaderoController(ParqueaderoService parqueaderoService) {
        this.parqueaderoService = parqueaderoService;
    }
    @PostMapping
    public ResponseEntity<Parqueadero> crearParqueadero(@RequestBody Parqueadero parqueadero) {
        try {
            Parqueadero nuevoParqueadero = parqueaderoService.crearParqueadero(parqueadero);
            return new ResponseEntity<>(nuevoParqueadero, HttpStatus.CREATED);
        } catch (CamposNulosOVaciosException ex) {
            // Manejar la excepción cuando faltan campos
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /*@PostMapping("/{parqueaderoId}/asociar-socio/{socioId}")
    public ResponseEntity<Parqueadero> asociarSocio(@PathVariable Long parqueaderoId, @PathVariable Long socioId) {
        try {
            Parqueadero parqueadero = parqueaderoService.asociarSocio(parqueaderoId, socioId);
            return new ResponseEntity<>(parqueadero, HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            // Manejar la excepción cuando no se encuentra el parqueadero o el socio
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (ElementoNoEncontradoException ex) {
            // Manejar la excepción cuando no se encuentra el socio
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }*/
}