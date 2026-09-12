# Evaluación 01

## Sistema de Citas Médicas

| Dato | Detalle |
| --- | --- |
| Institución | TECSUP |
| Curso | Desarrollo de Aplicaciones Web |
| Docente | Coello Palomino, Ricardo |
| Semana | 04 |
| Tipo | Trabajo grupal |
| Repositorio | https://github.com/iam1shel/Citas_Medicas_Desarrollo_Web |

---

## Equipo

| Integrante | Módulo | Requerimientos |
| --- | --- | --- |
| Mishel Rojas | Registro | RF-CIT-01, RF-CIT-02 |
| Jordy Ponce | Agenda | RF-CIT-07, RF-CIT-08 |
| Luis Abad | Gestión | RF-CIT-09, RF-CIT-10, RF-CIT-11, RF-CIT-12, RF-CIT-13 |
| David Valcarcel | Consultas y reportes | RF-CIT-17, RF-CIT-20 |

---

## Descripción

Módulo **Citas Médicas** de un sistema web de gestión hospitalaria.

Permite registrar, programar, consultar y reportar citas, usando Spring Boot, MySQL, APIs REST y Thymeleaf.

Cada cita recibe un código automático, por ejemplo `CIT-000125`.

**Estados de la cita:** PROGRAMADA, CONFIRMADA, EN ESPERA, EN ATENCIÓN, ATENDIDA, CANCELADA, NO ASISTIÓ.

---

## Registro

**Responsable:** Mishel Rojas

| Código | Requerimiento |
| --- | --- |
| RF-CIT-01 | El sistema deberá permitir registrar citas médicas. |
| RF-CIT-02 | El sistema deberá generar automáticamente un código único para cada cita. |

**Datos de la cita**

- ID
- Código
- Paciente
- DNI
- Especialidad
- Médico
- Consultorio
- Fecha
- Hora
- Tipo de atención
- Motivo
- Observaciones
- Estado

---

## Agenda

**Responsable:** Jordy Ponce

| Código | Requerimiento |
| --- | --- |
| RF-CIT-07 | El sistema deberá mostrar la agenda de cada médico. |
| RF-CIT-08 | El sistema deberá permitir consultar la agenda por día, semana y mes. |

Cada médico visualiza sus citas. No se puede reservar un horario ocupado.

---

## Gestión

**Responsable:** Luis Abad

| Código | Requerimiento |
| --- | --- |
| RF-CIT-09 | El sistema deberá permitir modificar una cita. |
| RF-CIT-10 | El sistema deberá permitir reprogramar una cita. |
| RF-CIT-11 | El sistema deberá permitir cancelar una cita. |
| RF-CIT-12 | El sistema deberá registrar el motivo de cancelación o reprogramación. |
| RF-CIT-13 | El sistema deberá permitir cambiar el estado de la cita. |

La cita no se elimina. Queda en estado CANCELADA y se guarda el historial.

---

## Consultas y reportes

**Responsable:** David Valcarcel

| Código | Requerimiento |
| --- | --- |
| RF-CIT-17 | El sistema deberá permitir buscar citas por paciente, médico, especialidad, fecha y estado. |
| RF-CIT-20 | El sistema deberá generar reportes de citas por médico y especialidad. |

También se contemplan reportes de:

- Citas del día
- Citas de la semana
- Citas del mes
- Citas atendidas
- Citas canceladas
- Pacientes que no asistieron
- Porcentaje de asistencia

---

## Cómo ejecutar

1. Entra a la carpeta CitasMedicas
2. Configura MySQL en src/main/resources/application.properties
3. Ejecuta: mvnw.cmd spring-boot:run
