package com.tecsup.controller_consultas;

import com.tecsup.dto_consultas.ReporteResumenDTO;
import com.tecsup.model.Cita;
import com.tecsup.service_consultas.CitaConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
@RequestMapping("/vistas/consultas")
public class CitaConsultaWebController {

    @Autowired
    private CitaConsultaService service;

    @GetMapping("/buscar")
    public String vistaBuscar(@RequestParam(required = false) String tipo,
                              @RequestParam(required = false) String valor,
                              Model model) {

        if (tipo != null && valor != null && !valor.trim().isEmpty()) {
            List<Cita> resultados = null;
            switch (tipo) {
                case "paciente": resultados = service.buscarPorPaciente(valor); break;
                case "medico": resultados = service.buscarPorMedico(valor); break;
                case "especialidad": resultados = service.buscarPorEspecialidad(valor); break;
                case "estado": resultados = service.buscarPorEstado(valor); break;
            }
            model.addAttribute("citas", resultados);
        }
        return "consultas/busqueda";
    }
    @GetMapping("/reporte")
    public String vistaReporte(@RequestParam(required = false) String medico,
                               @RequestParam(required = false) String especialidad,
                               Model model) {

        if (medico != null && especialidad != null && !medico.trim().isEmpty() && !especialidad.trim().isEmpty()) {
            ReporteResumenDTO reporte = service.generarReportePorMedicoYEspecialidad(medico, especialidad);
            model.addAttribute("reporte", reporte);
        }
        return "consultas/reporte";
    }
}