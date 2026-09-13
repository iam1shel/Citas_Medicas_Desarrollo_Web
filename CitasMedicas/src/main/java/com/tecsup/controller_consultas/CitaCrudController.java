package com.tecsup.controller_consultas;

import com.tecsup.model.Cita;
import com.tecsup.repository_consultas.CitaConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/crud-citas")
public class CitaCrudController {

    @Autowired
    private CitaConsultaRepository repository;

    @GetMapping
    public List<Cita> listarTodas() {
        return repository.findAll();
    }

    @PostMapping
    public Cita crearCita(@RequestBody Cita cita) {
        return repository.save(cita);
    }

    @PutMapping("/{id}")
    public Cita actualizarCita(@PathVariable Long id, @RequestBody Cita cita) {
        cita.setIdCita(id);
        return repository.save(cita);
    }

    @DeleteMapping("/{id}")
    public void eliminarCita(@PathVariable Long id) {
        repository.deleteById(id);
    }
}