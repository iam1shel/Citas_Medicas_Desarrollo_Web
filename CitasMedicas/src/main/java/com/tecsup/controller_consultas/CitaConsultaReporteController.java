package com.tecsup.controller_consultas;

import com.tecsup.dto_consultas.ReporteResumenDTO;
import com.tecsup.model.Cita;
import com.tecsup.service_consultas.CitaConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/consultas")
public class CitaConsultaReporteController {

    @Autowired
    private CitaConsultaService service;

    @GetMapping("/paciente")
    public List<Cita> buscarPorPaciente(@RequestParam String nombre) {
        return service.buscarPorPaciente(nombre);
    }

    @GetMapping("/medico")
    public List<Cita> buscarPorMedico(@RequestParam String medico) {
        return service.buscarPorMedico(medico);
    }

    @GetMapping("/especialidad")
    public List<Cita> buscarPorEspecialidad(@RequestParam String especialidad) {
        return service.buscarPorEspecialidad(especialidad);
    }

    @GetMapping("/estado")
    public List<Cita> buscarPorEstado(@RequestParam String estado) {
        return service.buscarPorEstado(estado);
    }

    @GetMapping("/reporte")
    public ReporteResumenDTO obtenerReporte(@RequestParam String medico, @RequestParam String especialidad) {
        return service.generarReportePorMedicoYEspecialidad(medico, especialidad);
    }
}