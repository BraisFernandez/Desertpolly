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
}
