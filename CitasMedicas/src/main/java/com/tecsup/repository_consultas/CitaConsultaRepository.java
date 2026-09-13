package com.tecsup.repository_consultas;

import com.tecsup.model.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CitaConsultaRepository extends JpaRepository<Cita, Long> {

    List<Cita> findByPacienteContaining(String paciente);
    List<Cita> findByMedico(String medico);
    List<Cita> findByEspecialidad(String especialidad);
    List<Cita> findByEstado(String estado);

    List<Cita> findByMedicoAndEspecialidad(String medico, String especialidad);
}