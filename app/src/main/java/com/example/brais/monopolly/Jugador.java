package com.example.brais.monopolly;

import android.graphics.Bitmap;

public class Jugador {
    Bitmap imagen;
    int posicion;
    int dinero;



    public Jugador(Bitmap imagen, int posicion, int dinero) {
        this.imagen = imagen;
        this.posicion = posicion;
        this.dinero = dinero;
    }

    public Bitmap getImagen() {
        return imagen;
    }

    public void setImagen(Bitmap imagen) {
        this.imagen = imagen;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public int getDinero() {
        return dinero;
    }

    public void setDinero(int dinero) {
        this.dinero = dinero;
    }
}
