package com.example.restaurant.model;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("id_user")
    private Long id_user;

    @SerializedName("nombre")
    private String nombre;

    @SerializedName("email")
    private String email;

    @SerializedName("contrasena")
    private String contrasena;

    public User(Long id_user, String nombre, String email, String contrasena) {
        this.id_user = id_user;
        this.nombre = nombre;
        this.email = email;
        this.contrasena = contrasena;
    }

    public User(String contrasena, String email ,String nombre) {
        this.nombre = nombre;
        this.email = email;
        this.contrasena = contrasena;
    }

    public User() {
    }

    public Long getId_user() {
        return id_user;
    }

    public void setId_user(Long id_user) {
        this.id_user = id_user;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    @Override
    public String toString() {
        return "User{" +
                "id_user=" + id_user +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", contrasena='" + contrasena + '\'' +
                '}';
    }
}
