# Evaluacion 01 - Sistema de Citas Medicas

Institucion: TECSUP
Curso: Desarrollo de Aplicaciones Web
Docente: Coello Palomino, Ricardo
Semana: 04
Tipo: Trabajo grupal
Repositorio: https://github.com/iam1shel/Citas_Medicas_Desarrollo_Web

## Equipo

- Mishel Rojas - Registro - RF-CIT-01, RF-CIT-02
- Jordy Ponce - Agenda - RF-CIT-07, RF-CIT-08
- Luis Abad - Gestion - RF-CIT-09, RF-CIT-10, RF-CIT-11, RF-CIT-12, RF-CIT-13
- David Valcarcel - Consultas y reportes - RF-CIT-17, RF-CIT-20

## Descripcion

Modulo Citas Medicas de un sistema web de gestion hospitalaria.
Permite registrar, programar, consultar y reportar citas, con Spring Boot, MySQL, APIs REST y Thymeleaf.

Cada cita tiene un codigo automatico (ejemplo: CIT-000125) y estados como PROGRAMADA, CONFIRMADA, EN ESPERA, EN ATENCION, ATENDIDA, CANCELADA o NO ASISTIO.

## Registro - Mishel Rojas

RF-CIT-01: El sistema debera permitir registrar citas medicas.
RF-CIT-02: El sistema debera generar automaticamente un codigo unico para cada cita.

Datos de la cita: ID, codigo, paciente, DNI, especialidad, medico, consultorio, fecha, hora, tipo de atencion, motivo, observaciones y estado.

## Agenda - Jordy Ponce

RF-CIT-07: El sistema debera mostrar la agenda de cada medico.
RF-CIT-08: El sistema debera permitir consultar la agenda por dia, semana y mes.

Cada medico visualiza sus citas. No se puede reservar un horario ocupado.

## Gestion - Luis Abad

RF-CIT-09: El sistema debera permitir modificar una cita.
RF-CIT-10: El sistema debera permitir reprogramar una cita.
RF-CIT-11: El sistema debera permitir cancelar una cita.
RF-CIT-12: El sistema debera registrar el motivo de cancelacion o reprogramacion.
RF-CIT-13: El sistema debera permitir cambiar el estado de la cita.

La cita no se elimina: queda CANCELADA y se guarda el historial.

## Consultas y reportes - David Valcarcel

RF-CIT-17: El sistema debera permitir buscar citas por paciente, medico, especialidad, fecha y estado.
RF-CIT-20: El sistema debera generar reportes de citas por medico y especialidad.

Tambien: citas del dia, semana y mes, atendidas, canceladas, no asistieron y porcentaje de asistencia.

## Como ejecutar

1. Entra a la carpeta CitasMedicas
2. Configura MySQL en src/main/resources/application.properties
3. Ejecuta: mvnw.cmd spring-boot:run
