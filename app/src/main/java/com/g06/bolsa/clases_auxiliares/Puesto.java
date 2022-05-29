package com.g06.bolsa.clases_auxiliares;

public class Puesto {
    private String idPuesto;
    private String nomPuesto;
    private String vacPuesto;
    private String idOferta;
    private String idArea;

    public Puesto(){
    }

    public String getId() {
        return idPuesto;
    }

    public void setId(String id) {
        this.idPuesto = id;
    }

    public String getNomPuesto() {
        return nomPuesto;
    }

    public void setNomPuesto(String nomPuesto) {
        this.nomPuesto = nomPuesto;
    }

    public String getVacPuesto() {
        return vacPuesto;
    }

    public void setVacPuesto(String vacPuesto) {
        this.vacPuesto = vacPuesto;
    }

    public String getIdOferta() {
        return idOferta;
    }

    public void setIdOferta(String idOferta) {
        this.idOferta = idOferta;
    }

    public String getIdArea() {
        return idArea;
    }

    public void setIdArea(String idArea) {
        this.idArea = idArea;
    }
}
