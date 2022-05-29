package com.g06.bolsa.clases_auxiliares;
import java.util.Date;

public class Evaluacion {
    private String idEvaluacion;
    private String idAplicacion;
    private String tipo;
    private String fecha;
    private String estado;

    public Evaluacion(){

    }

    public String getId() {
        return idEvaluacion;
    }

    public void setId(String id) {
        this.idEvaluacion = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getIdAplicacion() {
        return idAplicacion;
    }

    public void setIdAplicacion(String idAplicacion) {
        this.idAplicacion = idAplicacion;
    }
}
