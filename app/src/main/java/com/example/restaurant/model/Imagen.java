package com.example.restaurant.model;

public class Imagen {

    private Long id_imagen;

    private String imagen;

    public Imagen(Long id_imagen, String imagen) {
        this.id_imagen = id_imagen;
        this.imagen = imagen;
    }

    public Imagen(String imagen) {
        this.imagen = imagen;
    }

    public Imagen() {
    }

    public Long getId_imagen() {
        return id_imagen;
    }

    public void setId_imagen(Long id_imagen) {
        this.id_imagen = id_imagen;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    @Override
    public String toString() {
        return "Imagen{" +
                "id_imagen=" + id_imagen +
                ", imagen='" + imagen + '\'' +
                '}';
    }
}
