package com.example.firebase_conexion.cuestionario;

public class MainModel {

    String compra,descripcion,foto,modelo;

    MainModel(){

    }
    public MainModel(String comprar, String descripcion, String foto, String modelo) {
        this.compra = comprar;
        this.descripcion = descripcion;
        this.foto = foto;
        this.modelo = modelo;
    }

    public String getCompra() {
        return compra;
    }

    public void setCompra(String compra) {
        this.compra = compra;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
}
