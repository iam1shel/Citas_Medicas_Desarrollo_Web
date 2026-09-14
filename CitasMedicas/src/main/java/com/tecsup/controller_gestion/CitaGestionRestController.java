package com.tecsup.controller_gestion;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tecsup.dto_gestion.CancelarRequest;
import com.tecsup.dto_gestion.EstadoRequest;
import com.tecsup.dto_gestion.ReprogramarRequest;
import com.tecsup.model.Cita;
import com.tecsup.model_gestion.HistorialCita;
import com.tecsup.service_gestion.CitaGestionService;

@RestController
@RequestMapping("/api/gestion")
public class CitaGestionRestController {

    private final CitaGestionService service;

    public CitaGestionRestController(CitaGestionService service) {
        this.service = service;
    }

    // RF-CIT-09
    @PutMapping("/{id}")
    public Cita modificar(@PathVariable Long id, @RequestBody Cita datos) {
        return service.modificar(id, datos, "api");
    }

    // RF-CIT-10 + RF-CIT-12
    @PutMapping("/{id}/reprogramar")
    public Cita reprogramar(@PathVariable Long id, @RequestBody ReprogramarRequest req) {
        return service.reprogramar(id, req.getFecha(), req.getHora(), req.getMedico(),
                req.getConsultorio(), req.getMotivo(), req.getUsuario());
    }

    // RF-CIT-11 + RF-CIT-12
    @PutMapping("/{id}/cancelar")
    public Cita cancelar(@PathVariable Long id, @RequestBody CancelarRequest req) {
        return service.cancelar(id, req.getMotivo(), req.getUsuario());
    }

    // RF-CIT-13
    @PutMapping("/{id}/estado")
    public Cita cambiarEstado(@PathVariable Long id, @RequestBody EstadoRequest req) {
        return service.cambiarEstado(id, req.getEstado(), req.getUsuario());
    }

    @GetMapping("/{id}/historial")
    public List<HistorialCita> historial(@PathVariable Long id) {
        return service.historial(id);
    }
}
