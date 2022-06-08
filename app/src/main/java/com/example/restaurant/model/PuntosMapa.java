package com.example.restaurant.model;

public class PuntosMapa {

    private double latitude;
    private double longitud;
    private String titulo;
    private String direccion;
    private boolean disponible;


    public PuntosMapa(double latitude, double longitud, String titulo,String direccion,boolean disponible) {
        this.latitude = latitude;
        this.longitud = longitud;
        this.titulo = titulo;
        this.direccion = direccion;
        this.disponible = disponible;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public PuntosMapa() {
    }

    @Override
    public String toString() {
        return "PuntosMapa{" +
                "latitude=" + latitude +
                ", longitud=" + longitud +
                ", titulo='" + titulo + '\'' +
                '}';
    }
}
