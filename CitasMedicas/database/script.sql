CREATE DATABASE IF NOT EXISTS citas_medicas;
USE citas_medicas;

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
