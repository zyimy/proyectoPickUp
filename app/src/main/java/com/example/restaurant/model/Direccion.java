package com.example.restaurant.model;

public class Direccion {

    private Long id_direccion;

    private String nombre_calle;

    private String portal;

    private int codigo_postal;

    public Direccion(Long id_direccion, String nombre_calle, String portal, int codigo_postal) {
        this.id_direccion = id_direccion;
        this.nombre_calle = nombre_calle;
        this.portal = portal;
        this.codigo_postal = codigo_postal;
    }

    public Direccion() {
    }

    public Direccion(String nombre_calle, String portal, int codigo_postal) {
        this.nombre_calle = nombre_calle;
        this.portal = portal;
        this.codigo_postal = codigo_postal;
    }

    public Long getId_direccion() {
        return id_direccion;
    }

    public void setId_direccion(Long id_direccion) {
        this.id_direccion = id_direccion;
    }

    public String getNombre_calle() {
        return nombre_calle;
    }

    public void setNombre_calle(String nombre_calle) {
        this.nombre_calle = nombre_calle;
    }

    public String getPortal() {
        return portal;
    }

    public void setPortal(String portal) {
        this.portal = portal;
    }

    public int getCodigo_postal() {
        return codigo_postal;
    }

    public void setCodigo_postal(int codigo_postal) {
        this.codigo_postal = codigo_postal;
    }

    @Override
    public String toString() {
        return "Direccion{" +
                "id_direccion=" + id_direccion +
                ", nombre_calle='" + nombre_calle + '\'' +
                ", portal='" + portal + '\'' +
                ", codigo_postal=" + codigo_postal +
                '}';
    }
}
