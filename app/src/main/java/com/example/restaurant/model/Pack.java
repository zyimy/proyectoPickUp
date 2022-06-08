package com.example.restaurant.model;

import android.os.Parcelable;
import org.parceler.Parcel;

import java.io.Serializable;


public class Pack implements Serializable {

    private  Long id_pack;

    private String nombre;

    private String descripcion;

    private String imagen;

    private String direccion;

    private String status;

    private String hora_disponible;

    private double precio;

    public Pack() {
    }

    public Pack(Long id_pack, String nombre, String descripcion, String imagen, String direccion, String status, String hora_disponible, double precio) {
        this.id_pack = id_pack;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.direccion = direccion;
        this.status = status;
        this.hora_disponible = hora_disponible;
        this.precio = precio;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHora_disponible() {
        return hora_disponible;
    }

    public void setHora_disponible(String hora_disponible) {
        this.hora_disponible = hora_disponible;
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
                ", status='" + status + '\'' +
                ", hora_disponible='" + hora_disponible + '\'' +
                ", precio=" + precio +
                '}';
    }
}
