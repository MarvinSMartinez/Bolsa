package com.g06.bolsa.clases_auxiliares;

public class Usuario {
    private String id;
    private String nombre;
    private String password;
    private String tipo;
    private int is_logged;

    public Usuario() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int isLogged() {
        return is_logged;
    }

    public void setLoggedState(int logingState) {
        this.is_logged = logingState;
    }
}
