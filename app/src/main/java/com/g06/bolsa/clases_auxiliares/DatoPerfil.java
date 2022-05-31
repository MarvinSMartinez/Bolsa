package com.g06.bolsa.clases_auxiliares;

public class DatoPerfil {

    String idCandidato;
    String departamento;
    String idUsuario;
    String nombreCandidato;
    String apellidoCandidato;
    String dui;
    String nit;

    public DatoPerfil() {
    }

    public DatoPerfil(String idCandidato, String departamento,String idUsuario, String nombreCandidato,String apellidoCandidato,String dui,String nit) {
        this.idCandidato = idCandidato;
        this.departamento = departamento;
        this.idUsuario=idUsuario;
        this.nombreCandidato = nombreCandidato;
        this.apellidoCandidato=apellidoCandidato;
        this.dui=dui;
        this.nit=nit;
    }

    public String getIdCandidato() {
        return idCandidato;
    }
    public void setIdCandidato(String idCandidato) {
        this.idCandidato = idCandidato;
    }
    public String getDepartamento() {
        return departamento;
    }
    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
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

    public String getDui() {
        return dui;
    }
    public void setDui(String dui) {
        this.dui = dui;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }
}
