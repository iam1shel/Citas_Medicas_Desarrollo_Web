package com.tecsup.service_agenda;

import com.tecsup.model.Cita;
import com.tecsup.repository_agenda.AgendaRepository;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@Service
public class AgendaService {

    private final AgendaRepository agendaRepository;

    public AgendaService(AgendaRepository agendaRepository) {
        this.agendaRepository = agendaRepository;
    }

    public List<Cita> obtenerAgenda(String medico, String vista, LocalDate fecha) {
        LocalDate inicio;
        LocalDate fin;

        // RF-CIT-08: Cálculo de rangos para Día, Semana y Mes
        switch (vista.toLowerCase()) {
            case "semana":
                inicio = fecha.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
                fin = fecha.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
                break;
            case "mes":
                inicio = fecha.with(TemporalAdjusters.firstDayOfMonth());
                fin = fecha.with(TemporalAdjusters.lastDayOfMonth());
                break;
            case "dia":
            default:
                inicio = fecha;
                fin = fecha;
                break;
        }

        if (medico != null && !medico.trim().isEmpty()) {
            return agendaRepository.findByMedicoContainingIgnoreCaseAndFechaBetween(medico, inicio, fin);
        } else {
            return agendaRepository.findByFechaBetween(inicio, fin);
        }
    }
}