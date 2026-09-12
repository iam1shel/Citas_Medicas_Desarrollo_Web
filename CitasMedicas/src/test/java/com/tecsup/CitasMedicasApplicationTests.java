package com.tecsup;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tecsup.model.Cita;
import com.tecsup.service.CitaService;

@SpringBootTest
class CitasMedicasApplicationTests {

    @Autowired
    private CitaService citaService;

    @Test
    void contextLoads() {
    }

    @Test
    void registrarCitaGeneraCodigoUnico() {
        Cita primera = citaService.registrar(citaDe("Juan Pérez López"));
        Cita segunda = citaService.registrar(citaDe("María López"));

        assertThat(primera.getCodigoCita()).isEqualTo("CIT-000001");
        assertThat(segunda.getCodigoCita()).isEqualTo("CIT-000002");
        assertThat(primera.getCodigoCita()).isNotEqualTo(segunda.getCodigoCita());
        assertThat(primera.getEstado()).isEqualTo("PROGRAMADA");
        assertThat(segunda.getEstado()).isEqualTo("PROGRAMADA");
    }

    private Cita citaDe(String paciente) {
        Cita cita = new Cita();
        cita.setPaciente(paciente);
        cita.setDni("12345678");
        cita.setEspecialidad("Medicina General");
        cita.setMedico("Dr. Carlos Ramírez");
        cita.setConsultorio("C-205");
        cita.setFecha(LocalDate.of(2026, 9, 8));
        cita.setHora(LocalTime.of(10, 30));
        cita.setTipoAtencion("Presencial");
        cita.setMotivo("Control");
        return cita;
    }
}
