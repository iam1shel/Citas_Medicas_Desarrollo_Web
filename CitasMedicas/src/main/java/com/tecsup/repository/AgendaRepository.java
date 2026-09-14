package com.tecsup.repository_agenda;

import com.tecsup.model.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AgendaRepository extends JpaRepository<Cita, Long> {
    
    // Busca citas por nombre exacto o parcial del médico y por el rango de fechas
    List<Cita> findByMedicoContainingIgnoreCaseAndFechaBetween(String medico, LocalDate fechaInicio, LocalDate fechaFin);

    // Consulta por si no se ingresa ningún médico (trae todas las citas del rango)
    List<Cita> findByFechaBetween(LocalDate fechaInicio, LocalDate fechaFin);
}