package com.example.brais.monopolly;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;

/**
 * EscenaAyuda, hereda de escena. Clase donde se muestra una imagen con una breve descripción de el ovjetivo del juego.
 */
public class EscenaAyuda extends Escena {

    Bitmap fondoescena;//la imagen de fondo

    /**
     * Constructor de la clase EscenaAyuda
     * @param numEscena
     * @param context
     * @param anchoPantalla
     * @param altoPantalla
     */
    public EscenaAyuda(int numEscena, Context context, int anchoPantalla, int altoPantalla) {
        super(numEscena, context, anchoPantalla, altoPantalla);

        fondoescena = BitmapFactory.decodeResource(context.getResources(), R.drawable.escenaayuda);
        fondoescena = Bitmap.createScaledBitmap(fondoescena, anchoPantalla, altoPantalla, true);
    }

    /**
     * Método donde se dibuja constantemente todos los elementos de la escena
     * @param c
     */
    public void dibujar(Canvas c) {
        super.dibujar(c);
        c.drawBitmap(fondoescena, 0, 0, null);
        c.drawBitmap(imgVolver, getPixels(0), altoPantalla - getPixels(50), null);
    }

    /**
     * Método que gestiona las pulsaciones del usuario en la pantalla.
     * @param event
     * @return
     */
    public int onTouchEvent(MotionEvent event) {
        //Llama al control de pulsaciones de la clase padre
        int nuevaEscena=super.onTouchEvent(event);
        int accion = event.getAction();

        switch (accion) {
            case MotionEvent.ACTION_POINTER_DOWN:
                if (bAnt.contains((int) event.getX(), (int) event.getY()) && numEscena > 1) {
                    return 1;
                }
                break;
            case MotionEvent.ACTION_DOWN:
                if (bAnt.contains((int) event.getX(), (int) event.getY()) && numEscena > 1) {
                    return 1;
                }
                break;
        }

        if(nuevaEscena!=numEscena){
            return nuevaEscena;
        }
        return numEscena;
    }
}
