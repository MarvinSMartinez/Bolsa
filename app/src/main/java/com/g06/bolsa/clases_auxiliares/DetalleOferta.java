package com.g06.bolsa.clases_auxiliares;

public class DetalleOferta {

    private String idDetalleOferta;
    private String idOferta;
    private String perfil;
    private String salarioOferta;

    public DetalleOferta(){}

    public DetalleOferta(String idDetalleOferta, String idOferta, String perfil, String salarioOferta) {
        this.idDetalleOferta = idDetalleOferta;
        this.idOferta = idOferta;
        this.perfil = perfil;
        this.salarioOferta = salarioOferta;
    }


    public String getIdDetalleOferta() {
        return idDetalleOferta;
    }

    public void setIdDetalleOferta(String idDetalleOferta) {
        this.idDetalleOferta = idDetalleOferta;
    }

    public String getIdOferta() {
        return idOferta;
    }

    public void setIdOferta(String idOferta) {
        this.idOferta = idOferta;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public String getSalarioOferta() {
        return salarioOferta;
    }

    public void setSalarioOferta(String salarioOferta) {
        this.salarioOferta = salarioOferta;
    }


}
