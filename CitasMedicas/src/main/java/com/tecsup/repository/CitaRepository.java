package com.tecsup.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tecsup.model.Cita;

public interface CitaRepository extends JpaRepository<Cita, Long> {

    boolean existsByCodigoCita(String codigoCita);

    Optional<Cita> findByCodigoCita(String codigoCita);

    @Query("SELECT COALESCE(MAX(c.idCita), 0) FROM Cita c")
    Long obtenerUltimoId();
}
