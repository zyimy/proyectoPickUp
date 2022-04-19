package com.example.restaurant.model;

import java.util.List;

public class Restaurante {

    private Long id_restaurante;

    private String nombre_restaurante;

    private String email;

    private String contrasena;

    private String nombre_calle;

    private int numero_calle;

    private int codigo_postal;

    private String nombre;

    private String descripcion;

    private String imagen;

    private String direccion;

    private double precio;

    public Restaurante(Long id_restaurante, String nombre_restaurante, String email, String contrasena, String nombre_calle, int numero_calle, int codigo_postal, String nombre, String descripcion, String imagen, String direccion, double precio, List<Pack> packs) {
        this.id_restaurante = id_restaurante;
        this.nombre_restaurante = nombre_restaurante;
        this.email = email;
        this.contrasena = contrasena;
        this.nombre_calle = nombre_calle;
        this.numero_calle = numero_calle;
        this.codigo_postal = codigo_postal;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.direccion = direccion;
        this.precio = precio;
        this.packs = packs;
    }

    private List<Pack> packs;

    public Restaurante(int codigo_postal,String contrasena,String email, String nombre_calle,String nombre_restaurante,int numero_calle) {
        this.nombre_restaurante = nombre_restaurante;
        this.email = email;
        this.contrasena = contrasena;
        this.nombre_calle = nombre_calle;
        this.numero_calle = numero_calle;
        this.codigo_postal = codigo_postal;
    }

    public Restaurante(Long id_restaurante, String nombre_restaurante, String email, String contrasena, String nombre_calle, int numero_calle, int codigo_postal) {
        this.id_restaurante = id_restaurante;
        this.nombre_restaurante = nombre_restaurante;
        this.email = email;
        this.contrasena = contrasena;
        this.nombre_calle = nombre_calle;
        this.numero_calle = numero_calle;
        this.codigo_postal = codigo_postal;
    }

    public Restaurante(String nombre_restaurante, String email, String contrasena, String nombre_calle, int numero_calle, int codigo_postal, List<Pack> pack) {
        this.nombre_restaurante = nombre_restaurante;
        this.email = email;
        this.contrasena = contrasena;
        this.nombre_calle = nombre_calle;
        this.numero_calle = numero_calle;
        this.codigo_postal = codigo_postal;
        this.packs = pack;
    }

    public Restaurante() {
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

    public List<Pack> getPacks() {
        return packs;
    }

    public void setPacks(List<Pack> packs) {
        this.packs = packs;
    }

    public List<Pack> getPack() {
        return packs;
    }

    public void setPack(List<Pack> pack) {
        this.packs = pack;
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

    public String getNombre_calle() {
        return nombre_calle;
    }

    public void setNombre_calle(String nombre_calle) {
        this.nombre_calle = nombre_calle;
    }

    public int getNumero_calle() {
        return numero_calle;
    }

    public void setNumero_calle(int numero_calle) {
        this.numero_calle = numero_calle;
    }

    public int getCodigo_postal() {
        return codigo_postal;
    }

    public void setCodigo_postal(int codigo_postal) {
        this.codigo_postal = codigo_postal;
    }

    @Override
    public String toString() {
        return "Restaurante{" +
                "id_restaurante=" + id_restaurante +
                ", nombre_restaurante='" + nombre_restaurante + '\'' +
                ", email='" + email + '\'' +
                ", contrasena='" + contrasena + '\'' +
                ", nombre_calle='" + nombre_calle + '\'' +
                ", numero_calle=" + numero_calle +
                ", codigo_postal=" + codigo_postal +
                ", packs=" + packs +
                '}';
    }
}
