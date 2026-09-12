package com.tecsup.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tecsup.model.Cita;
import com.tecsup.service.CitaService;

@RestController
@RequestMapping("/api/citas")
public class CitaRestController {

    private final CitaService citaService;

    public CitaRestController(CitaService citaService) {
        this.citaService = citaService;
    }

    @GetMapping
    public List<Cita> listar() {
        return citaService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cita> obtener(@PathVariable Long id) {
        Cita cita = citaService.obtener(id);
        if (cita == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cita);
    }

    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<Cita> obtenerPorCodigo(@PathVariable String codigo) {
        Cita cita = citaService.obtenerPorCodigo(codigo);
        if (cita == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cita);
    }

    @PostMapping
    public ResponseEntity<Cita> registrar(@RequestBody Cita cita) {
        return ResponseEntity.status(201).body(citaService.registrar(cita));
    }
}
