package com.tecsup.controller_agenda;

import com.tecsup.model.Cita;
import com.tecsup.service_agenda.AgendaService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/agenda")
public class AgendaController {

    private final AgendaService agendaService;

    public AgendaController(AgendaService agendaService) {
        this.agendaService = agendaService;
    }

    @GetMapping
    public String verAgenda(
            @RequestParam(required = false, defaultValue = "") String medico,
            @RequestParam(required = false, defaultValue = "dia") String vista,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha,
            Model model) {

        if (fecha == null) {
            fecha = LocalDate.now();
        }

        List<Cita> citas = agendaService.obtenerAgenda(medico, vista, fecha);

        model.addAttribute("citas", citas);
        model.addAttribute("medico", medico);
        model.addAttribute("vista", vista);
        model.addAttribute("fecha", fecha);

        return "agenda/agenda";
    }
}