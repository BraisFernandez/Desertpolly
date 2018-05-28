package com.example.brais.monopolly;

import android.graphics.Bitmap;
import android.graphics.Matrix;

/**
 * Clase casillas, donde gestiono todas las casillas del tablero
 */
public class Casillas {

    public Jugador dueno;
    public int precio;
    public int numero;
    public boolean especiales;
    public int tamano;
    public Bitmap imgCelda;
    public int cobrar;

    /**
     * Constructor de la clase Casillas
     * @param precio
     * @param numero
     * @param especiales
     * @param tamano
     * @param imgCelda
     * @param cobrar
     */
    public Casillas(int precio, int numero, boolean especiales, int tamano, Bitmap imgCelda, int cobrar) {
        this.dueno = null;
        this.cobrar = cobrar;
        this.precio = precio;
        this.numero = numero;
        this.especiales = especiales;
        this.tamano = tamano;
        this.imgCelda = imgCelda;
    }

    public Jugador getDueno() {
        return dueno;
    }

    public void setDueno(Jugador dueno) {
        this.dueno = dueno;
    }

    public int getCobrar() {
        return cobrar;
    }

    public void setCobrar(int cobrar) {
        this.cobrar = cobrar;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public Bitmap RotateBitmap(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
    }
}
