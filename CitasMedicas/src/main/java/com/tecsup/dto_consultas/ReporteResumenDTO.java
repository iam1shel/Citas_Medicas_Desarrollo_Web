package com.tecsup.dto_consultas;

public class ReporteResumenDTO {
    private String criterio;
    private long totalCitas;

    public ReporteResumenDTO(String criterio, long totalCitas) {
        this.criterio = criterio;
        this.totalCitas = totalCitas;
    }

    public String getCriterio() { return criterio; }
    public void setCriterio(String criterio) { this.criterio = criterio; }

    public long getTotalCitas() { return totalCitas; }
    public void setTotalCitas(long totalCitas) { this.totalCitas = totalCitas; }
}