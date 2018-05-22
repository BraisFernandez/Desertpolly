package com.example.brais.monopolly;

import android.graphics.Bitmap;

/**
 * Clase jugador donde gestiono los datos necesarios de los jugadores
 */
public class Jugador {
    Bitmap imagen;
    int posicion;
    int dinero;
    boolean tengoTurno;

    /**
     * Constructor de la clase Jugador
     * @param imagen
     * @param posicion
     * @param dinero
     * @param tengoTurno
     */
    public Jugador(Bitmap imagen, int posicion, int dinero, boolean tengoTurno) {
        this.imagen = imagen;
        this.posicion = posicion;
        this.dinero = dinero;
        this.tengoTurno = tengoTurno;
    }

    public boolean isTengoTurno() {
        return tengoTurno;
    }

    public void setTengoTurno(boolean tengoTurno) {
        this.tengoTurno = tengoTurno;
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
