# Evaluación 01 — Sistema de Citas Médicas

**Curso:** Desarrollo de Aplicaciones Web
**Semana:** 04
**Repositorio:** [Citas_Medicas_Desarrollo_Web](https://github.com/iam1shel/Citas_Medicas_Desarrollo_Web)
**Estudiante:** Luz Mishel Rojas Tuesta
**Tipo:** Trabajo grupal (aporte individual documentado abajo)

---

## 1. Presentación

Este repositorio corresponde a la **Evaluación 01** del curso de Desarrollo de Aplicaciones Web. El caso es un **sistema de citas médicas**.

El desarrollo completo es **grupal**. En esta entrega se documenta únicamente el alcance asignado a **Luz Mishel Rojas Tuesta**: el registro de citas y la generación automática del código único.

El resto de módulos corresponde a otros integrantes del equipo y **no se implementa aquí**.

---

## 2. Alcance individual

| Código | Requerimiento | Estado |
|---|---|---|
| **RF-CIT-01** | El sistema deberá permitir registrar citas médicas. | Asignado |
| **RF-CIT-02** | El sistema deberá generar automáticamente un código único para cada cita. | Asignado |

---

## 3. RF-CIT-01 — Registrar citas médicas

El sistema debe permitir **crear una nueva cita médica**.

### Qué debe hacer

- Registrar una cita con fecha, hora, paciente, médico y motivo (u otros campos que defina el equipo).
- Validar que los datos obligatorios estén completos antes de guardar.
- Confirmar el registro.
- Poder consultar la cita recién creada.

### Criterios de aceptación

1. Un usuario puede enviar los datos de una cita y el sistema la guarda.
2. Si falta un dato obligatorio, el sistema no registra la cita y avisa el error.
3. La cita queda persistida y se puede consultar después del registro.

---

## 4. RF-CIT-02 — Código único automático

Cada cita debe tener un **código único generado por el sistema**. El usuario **no** lo escribe a mano.

### Qué debe hacer

- Al registrar una cita (RF-CIT-01), el sistema asigna un código automáticamente.
- El código no se repite.
- El código se muestra al confirmar y al consultar la cita.

### Criterios de aceptación

1. Al crear una cita, el código se genera sin que el usuario lo ingrese.
2. Dos citas distintas nunca tienen el mismo código.
3. El código queda asociado a la cita.

---

## 5. Relación entre ambos requerimientos

1. El usuario registra la cita (**RF-CIT-01**).
2. El sistema genera el código único (**RF-CIT-02**).
3. Se guarda la cita con su código.

---

## 6. Notas para el equipo

Este README cubre **solo** RF-CIT-01 y RF-CIT-02. El resto se define en conjunto.
