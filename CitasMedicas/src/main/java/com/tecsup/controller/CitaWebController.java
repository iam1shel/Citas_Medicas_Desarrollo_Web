package com.tecsup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tecsup.model.Cita;
import com.tecsup.service.CitaService;

@Controller
public class CitaWebController {

    private final CitaService citaService;

    public CitaWebController(CitaService citaService) {
        this.citaService = citaService;
    }

    @GetMapping("/")
    public String inicio() {
        return "redirect:/citas/nueva";
    }

    @GetMapping("/citas/nueva")
    public String nueva(Model model) {
        if (!model.containsAttribute("cita")) {
            model.addAttribute("cita", new Cita());
        }
        return "citas/nueva";
    }

    @PostMapping("/citas")
    public String registrar(@ModelAttribute Cita cita, RedirectAttributes flash) {
        try {
            Cita guardada = citaService.registrar(cita);
            flash.addFlashAttribute("mensaje", "Cita registrada. Código: " + guardada.getCodigoCita());
            return "redirect:/citas/" + guardada.getIdCita();
        } catch (ResponseStatusException ex) {
            flash.addFlashAttribute("error", ex.getReason());
            flash.addFlashAttribute("cita", cita);
            return "redirect:/citas/nueva";
        }
    }

    @GetMapping("/citas")
    public String listar(Model model) {
        model.addAttribute("citas", citaService.listar());
        return "citas/lista";
    }

    @GetMapping("/citas/{id}")
    public String detalle(@PathVariable Long id, Model model) {
        Cita cita = citaService.obtener(id);
        if (cita == null) {
            return "redirect:/citas";
        }
        model.addAttribute("cita", cita);
        return "citas/detalle";
    }
}
