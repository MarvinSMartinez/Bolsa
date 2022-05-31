package com.g06.bolsa.clases_auxiliares;

public class Recomendacion {
    private String id;
    private String id_usuario;
    private String id_usuario_rep;
    private String titulo;
    private String cuerpo;

    public Recomendacion(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getId_usuario_rep() {
        return id_usuario_rep;
    }

    public void setId_usuario_rep(String id_usuario_rep) {
        this.id_usuario_rep = id_usuario_rep;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }
}
