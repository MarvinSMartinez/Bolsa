package com.g06.bolsa.clases_auxiliares;

public class Aspirante {

    private String idAspirante;
    private String idDetalleOferta;
    private String idEmpresa;
    private int estado;

    public Aspirante(){}

    public Aspirante(String idAspirante, String idDetalleOferta, String idEmpresa, int estado) {
        this.idAspirante = idAspirante;
        this.idDetalleOferta = idDetalleOferta;
        this.idEmpresa = idEmpresa;
        this.estado = estado;
    }

    public String getIdAspirante() {
        return idAspirante;
    }

    public void setIdAspirante(String idAspirante) {
        this.idAspirante = idAspirante;
    }

    public String getIdDetalleOferta() {
        return idDetalleOferta;
    }

    public void setIdDetalleOferta(String idDetalleOferta) {
        this.idDetalleOferta = idDetalleOferta;
    }

    public String getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(String idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }



}
