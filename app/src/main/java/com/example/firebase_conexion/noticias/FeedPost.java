package com.example.firebase_conexion.noticias;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class FeedPost {
    /*En esta clase instanciamos los objetos que vamos a recuperar de la URL*/

    String title;
    String content;
    String link;
    String fecha;

    public FeedPost(String title, String content, String link, String fecha)
    {
        this.title = title;
        this.content = content;
        this.link = link;
        this.fecha = fecha;

    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getLink() {
        return link;
    }

    public String getFecha() {
        return fecha;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
