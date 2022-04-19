package com.example.restaurant.model;

public class RestaurantePack {

    private  Long id_pack;

    private Long id_restaurante;

    private String nombre_restaurante;

    private String nombre;

    private String descripcion;

    private String imagen;

    private String direccion;

    private double precio;

    public RestaurantePack(Long id_pack, String nombre, String descripcion, String imagen, String direccion, double precio) {
        this.id_pack = id_pack;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.direccion = direccion;
        this.precio = precio;
    }

    public RestaurantePack(Long id_pack, Long id_restaurante, String nombre_restaurante, String nombre, String descripcion, String imagen, String direccion, double precio) {
        this.id_pack = id_pack;
        this.id_restaurante = id_restaurante;
        this.nombre_restaurante = nombre_restaurante;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.direccion = direccion;
        this.precio = precio;
    }

    public Long getId_restaurante() {
        return id_restaurante;
    }

    public void setId_restaurante(Long id_restaurante) {
        this.id_restaurante = id_restaurante;
    }

    public String getNombre_restaurante() {
        return nombre_restaurante;
    }

    public void setNombre_restaurante(String nombre_restaurante) {
        this.nombre_restaurante = nombre_restaurante;
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
