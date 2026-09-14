package com.tecsup.controller_gestion;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tecsup.model.Cita;
import com.tecsup.service_gestion.CitaGestionService;

@Controller
@RequestMapping("/vistas/gestion")
public class CitaGestionWebController {

    private static final String USUARIO_DEMO = "recepcion";

    private final CitaGestionService service;

    public CitaGestionWebController(CitaGestionService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public String formulario(@PathVariable Long id, Model model) {
        model.addAttribute("cita", service.obtener(id));
        model.addAttribute("historial", service.historial(id));
        return "gestion/gestionar";
    }

    @PostMapping("/{id}/modificar")
    public String modificar(@PathVariable Long id, @ModelAttribute Cita cita, RedirectAttributes flash) {
        try {
            service.modificar(id, cita, USUARIO_DEMO);
            flash.addFlashAttribute("mensaje", "Cita actualizada correctamente.");
        } catch (ResponseStatusException ex) {
            flash.addFlashAttribute("error", ex.getReason());
        }
        return "redirect:/vistas/gestion/" + id;
    }

    @PostMapping("/{id}/reprogramar")
    public String reprogramar(@PathVariable Long id,
                               @RequestParam(required = false) LocalDate fecha,
                               @RequestParam(required = false) LocalTime hora,
                               @RequestParam(required = false) String medico,
                               @RequestParam(required = false) String consultorio,
                               @RequestParam String motivo,
                               RedirectAttributes flash) {
        try {
            service.reprogramar(id, fecha, hora, medico, consultorio, motivo, USUARIO_DEMO);
            flash.addFlashAttribute("mensaje", "Cita reprogramada correctamente.");
        } catch (ResponseStatusException ex) {
            flash.addFlashAttribute("error", ex.getReason());
        }
        return "redirect:/vistas/gestion/" + id;
    }

    @PostMapping("/{id}/cancelar")
    public String cancelar(@PathVariable Long id, @RequestParam String motivo, RedirectAttributes flash) {
        try {
            service.cancelar(id, motivo, USUARIO_DEMO);
            flash.addFlashAttribute("mensaje", "Cita cancelada.");
        } catch (ResponseStatusException ex) {
            flash.addFlashAttribute("error", ex.getReason());
        }
        return "redirect:/vistas/gestion/" + id;
    }

    @PostMapping("/{id}/estado")
    public String estado(@PathVariable Long id, @RequestParam String estado, RedirectAttributes flash) {
        try {
            service.cambiarEstado(id, estado, USUARIO_DEMO);
            flash.addFlashAttribute("mensaje", "Estado actualizado a " + estado + ".");
        } catch (ResponseStatusException ex) {
            flash.addFlashAttribute("error", ex.getReason());
        }
        return "redirect:/vistas/gestion/" + id;
    }
}
