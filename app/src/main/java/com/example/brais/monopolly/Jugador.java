package com.example.brais.monopolly;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Clase jugador donde gestiono los datos necesarios de los jugadores
 */
public class Jugador {
    Bitmap imagen;
    int posicion;
    int dinero;
    boolean tengoTurno, dVertical = true, subir = false;
    Context context;
    int posVertPlayer, posHorizPlayer;
    boolean entrando, alacarcel;


    /**
     * Constructor de la clase Jugador
     *
     * @param imagen
     * @param dinero
     * @param tengoTurno
     */
    public Jugador(Bitmap imagen, int dinero, boolean tengoTurno, Context context) {
        this.context = context;
        this.imagen = imagen;
        this.posicion = 0;
        this.dinero = dinero;
        this.tengoTurno = tengoTurno;
        this.posVertPlayer = getPixels(110);
        this.posHorizPlayer = getPixels(35);

    }

    public int getPosVertPlayer() {
        return posVertPlayer;
    }

    public void setPosVertPlayer(int posVertPlayer) {
        this.posVertPlayer = posVertPlayer;
    }

    public int getPosHorizPlayer() {
        return posHorizPlayer;
    }

    public void setPosHorizPlayer(int posHorizPlayer) {
        this.posHorizPlayer = posHorizPlayer;
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

    public void turnoJugador(int numAle) {
        if (dVertical) {
            if (subir) {
                posVertPlayer -= mover(numAle);
            } else {
                posVertPlayer += mover(numAle);
            }
        } else {
            if (subir) {
                posHorizPlayer -= mover(numAle);
            } else {
                posHorizPlayer += mover(numAle);
            }
        }
        llegarMeta(numAle);

        posicion = (posicion + numAle) % 40;

        if (cambiarPosicion()) {
            posicion = 10;
            posVertPlayer += mover(20);
            posHorizPlayer -= mover(20);
        }
        if (posVertPlayer <= getPixels(110) && posHorizPlayer <= getPixels(35)) {
            dVertical = true;
            subir = false;
            posVertPlayer -= posHorizPlayer - getPixels(35);
            posHorizPlayer = getPixels(35);
        } else if (posVertPlayer >= getPixels(380) && posHorizPlayer <= getPixels(35)) {
            dVertical = false;
            subir = false;
            posHorizPlayer += posVertPlayer - getPixels(380);
            posVertPlayer = getPixels(380);
        } else if (posVertPlayer >= getPixels(380) && posHorizPlayer >= getPixels(305)) {
            dVertical = true;
            subir = true;
            posVertPlayer -= posHorizPlayer - getPixels(305);
            posHorizPlayer = getPixels(305);
        } else if (posVertPlayer <= getPixels(110) && posHorizPlayer >= getPixels(305)) {
            dVertical = false;
            subir = true;
            posHorizPlayer += posVertPlayer - getPixels(110);
            posVertPlayer = getPixels(110);
        }
    }

    public void llegarMeta(int numAle) {
        if (posicion > (posicion + numAle) % 40) {
            dinero += 200;
            entrando = true;
        }
    }

    /**
     * Método cambiar posición que indica si el jugador 1 ha caído en la carcel
     * Return: boolean
     */
    public boolean cambiarPosicion() {
        if (posicion == 30) {
            alacarcel = true;
            return true;
        }
        return false;
    }

    int getPixels(float dp) {
        DisplayMetrics metrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(metrics);
        return (int) (dp * metrics.density);
    }

    public float mover(int dados) {
        return dados * getPixels(27);
    }
}
