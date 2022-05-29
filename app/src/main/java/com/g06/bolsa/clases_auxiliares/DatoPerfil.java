package com.g06.bolsa.clases_auxiliares;

public class DatoPerfil {

    String idCandidato;
    String nombreCandidato;
    String apellidoCandidato;
    String departamento;
    String municipio;

    public DatoPerfil() {
    }

    public DatoPerfil(String idCandidato, String nombreCandidato,String apellidoCandidato, String departamento, String municipio) {
        this.idCandidato = idCandidato;
        this.nombreCandidato = nombreCandidato;
        this.apellidoCandidato=apellidoCandidato;
        this.departamento = departamento;
        this.municipio = municipio;
    }

    public String getIdCandidato() {
        return idCandidato;
    }
    public void setIdCandidato(String idCandidato) {
        this.idCandidato = idCandidato;
    }
    public String getNombreCandidato() {
        return nombreCandidato;
    }
    public void setNombreCandidato(String nombreCandidato) {
        this.nombreCandidato = nombreCandidato;
    }
    public String getApellidoCandidato() {
        return apellidoCandidato;
    }
    public void setApellidoCandidato(String apellidoCandidato) {
        this.apellidoCandidato = apellidoCandidato;
    }
    public String getDepartamento() {
        return departamento;
    }
    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
    public String getMunicipio() {
        return municipio;
    }
    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }
}
