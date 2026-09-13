package com.tecsup.service_consultas;

import com.tecsup.dto_consultas.ReporteResumenDTO;
import com.tecsup.model.Cita;
import com.tecsup.repository_consultas.CitaConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CitaConsultaService {

    @Autowired
    private CitaConsultaRepository repo;

    public List<Cita> buscarPorPaciente(String paciente) {
        return repo.findByPacienteContaining(paciente);
    }

    public List<Cita> buscarPorMedico(String medico) {
        return repo.findByMedico(medico);
    }

    public List<Cita> buscarPorEspecialidad(String especialidad) {
        return repo.findByEspecialidad(especialidad);
    }

    public List<Cita> buscarPorEstado(String estado) {
        return repo.findByEstado(estado);
    }

    public ReporteResumenDTO generarReportePorMedicoYEspecialidad(String medico, String especialidad) {
        List<Cita> citas = repo.findByMedicoAndEspecialidad(medico, especialidad);
        return new ReporteResumenDTO(medico + " - " + especialidad, citas.size());
    }
}