package com.g06.bolsa.clases_auxiliares;

public class DetalleExperiencia {
    private String id;
    private String idCandidato;
    private String lugarExperiencia;
    private String tiempoExperiencia;
    private String puestoExperiencia;

    public String getPuestoExperiencia() {
        return puestoExperiencia;
    }

    public void setPuestoExperiencia(String puestoExperiencia) {
        this.puestoExperiencia = puestoExperiencia;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdCandidato() {
        return idCandidato;
    }

    public void setIdCandidato(String idCandidato) {
        this.idCandidato = idCandidato;
    }

    public String getLugarExperiencia() {
        return lugarExperiencia;
    }

    public void setLugarExperiencia(String lugarExperiencia) {
        this.lugarExperiencia = lugarExperiencia;
    }

    public String getTiempoExperiencia() {
        return tiempoExperiencia;
    }

    public void setTiempoExperiencia(String tiempoExperiencia) {
        this.tiempoExperiencia = tiempoExperiencia;
    }

    public DetalleExperiencia() {

    }

}
