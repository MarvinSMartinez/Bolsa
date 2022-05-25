package com.g06.bolsa.clases_auxiliares;

import java.util.Date;

public class OfertaLaboral {

    private String idOferta;
    private String idEmpresa;
    private String inicioOferta;
    private String finOferta;
    private String nombreOferta;

    public OfertaLaboral(){

    }

    public OfertaLaboral(String idOferta, String idEmpresa, String inicioOferta, String finOferta, String nombreOferta) {
        this.idOferta = idOferta;
        this.idEmpresa = idEmpresa;
        this.inicioOferta = inicioOferta;
        this.finOferta = finOferta;
        this.nombreOferta = nombreOferta;
    }

    public String getIdOferta() {
        return idOferta;
    }

    public void setIdOferta(String idOferta) {
        this.idOferta = idOferta;
    }

    public String getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(String idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getInicioOferta() {
        return inicioOferta;
    }

    public void setInicioOferta(String inicioOferta) {
        this.inicioOferta = inicioOferta;
    }

    public String getFinOferta() {
        return finOferta;
    }

    public void setFinOferta(String finOferta) {
        this.finOferta = finOferta;
    }

    public String getNombreOferta() {
        return nombreOferta;
    }

    public void setNombreOferta(String nombreOferta) {
        this.nombreOferta = nombreOferta;
    }

}
