CREATE TABLE IF NOT EXISTS cita (
    id_cita BIGINT AUTO_INCREMENT PRIMARY KEY,
    codigo_cita VARCHAR(20) NOT NULL UNIQUE,
    paciente VARCHAR(120) NOT NULL,
    dni VARCHAR(15) NOT NULL,
    especialidad VARCHAR(80) NOT NULL,
    medico VARCHAR(120) NOT NULL,
    consultorio VARCHAR(20),
    fecha DATE NOT NULL,
    hora TIME NOT NULL,
    tipo_atencion VARCHAR(30),
    motivo VARCHAR(255),
    observaciones VARCHAR(255),
    estado VARCHAR(30) NOT NULL
);

-- Cita para HOY (Vista Día)
INSERT INTO cita (codigo_cita, paciente, dni, especialidad, medico, consultorio, fecha, hora, tipo_atencion, motivo, observaciones, estado) 
VALUES ('CIT-2026-001', 'Juan Pérez', '12345678', 'Cardiología', 'Dr. Carlos Mendoza', '101', CURRENT_DATE(), '09:00:00', 'Presencial', 'Consulta de rutina', 'Ninguna', 'PENDIENTE');

-- Cita para esta SEMANA
INSERT INTO cita (codigo_cita, paciente, dni, especialidad, medico, consultorio, fecha, hora, tipo_atencion, motivo, observaciones, estado) 
VALUES ('CIT-2026-002', 'María Gómez', '87654321', 'Cardiología', 'Dr. Carlos Mendoza', '101', DATEADD('DAY', 2, CURRENT_DATE()), '11:30:00', 'Presencial', 'Revisión de exámenes', 'Traer análisis de sangre', 'CONFIRMADA');

-- Cita para este MES
INSERT INTO cita (codigo_cita, paciente, dni, especialidad, medico, consultorio, fecha, hora, tipo_atencion, motivo, observaciones, estado) 
VALUES ('CIT-2026-003', 'Luis Torres', '45678912', 'Cardiología', 'Dr. Carlos Mendoza', '101', DATEADD('DAY', 15, CURRENT_DATE()), '15:00:00', 'Virtual', 'Chequeo mensual', 'Vía Zoom', 'PENDIENTE');

-- Cita de OTRO médico
INSERT INTO cita (codigo_cita, paciente, dni, especialidad, medico, consultorio, fecha, hora, tipo_atencion, motivo, observaciones, estado) 
VALUES ('CIT-2026-004', 'Ana Ramírez', '32165498', 'Pediatría', 'Dra. Elena Ramos', '202', CURRENT_DATE(), '10:00:00', 'Presencial', 'Control de niño sano', 'Ninguna', 'PENDIENTE');