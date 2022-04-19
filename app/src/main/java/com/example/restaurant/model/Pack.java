package com.example.restaurant.model;

public class Pack {

    private  Long id_pack;

    private String nombre;

    private String descripcion;

    private String imagen;

    private String direccion;

    private double precio;

    public Pack(Long id_pack, String nombre, String descripcion, String imagen, String direccion, double precio) {
        this.id_pack = id_pack;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.direccion = direccion;
        this.precio = precio;
    }

    public Long getId_pack() {
        return id_pack;
    }

    public void setId_pack(Long id_pack) {
        this.id_pack = id_pack;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Pack{" +
                "id_pack=" + id_pack +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", imagen='" + imagen + '\'' +
                ", direccion='" + direccion + '\'' +
                ", precio=" + precio +
                '}';
    }
}
