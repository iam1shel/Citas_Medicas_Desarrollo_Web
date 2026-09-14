package com.tecsup.repository_gestion;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tecsup.model_gestion.HistorialCita;

public interface HistorialCitaRepository extends JpaRepository<HistorialCita, Long> {

    List<HistorialCita> findByIdCitaOrderByFechaRegistroDesc(Long idCita);
}
