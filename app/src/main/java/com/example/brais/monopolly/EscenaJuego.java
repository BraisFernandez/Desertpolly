package com.example.brais.monopolly;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

//Hereda de Escena bc es una escena hija
public class EscenaJuego extends Escena {

    Paint pBoton, pVolver;
    Bitmap imgVolver;
    Bitmap fondoJuego;//la imagen de fondo

    public EscenaJuego(int numEscena, Context contexto, int anchoPantalla, int altoPantalla) {
        super(numEscena, contexto, anchoPantalla, altoPantalla);

        pBoton=new Paint();
        pBoton.setColor(Color.WHITE);
        pBoton.setAlpha(200);
        pBoton.setTextSize(70);

        pVolver=new Paint();
        pVolver.setColor(Color.WHITE);
        pVolver.setAlpha(0);
        pVolver.setTextSize(70);

        imgVolver = BitmapFactory.decodeResource(context.getResources(), R.drawable.flechamenu);
        imgVolver = Bitmap.createBitmap(imgVolver);
        imgVolver = Bitmap.createScaledBitmap(imgVolver, getPixels(50), getPixels(50), true);

        fondoJuego = BitmapFactory.decodeResource(context.getResources(), R.drawable.monopolyboard);
        fondoJuego = Bitmap.createScaledBitmap(fondoJuego, anchoPantalla, altoPantalla, true);
    }

    //Escena inicial (se le pasa el lienzo (el canvas))
    public void dibujar(Canvas c) {
        super.dibujar(c);
        c.drawBitmap(fondoJuego, 0, 0, null);
        c.drawBitmap(imgVolver, getPixels(0), altoPantalla - getPixels(50), null);
    }

    //Metodo encargado de actualizar elementos comunes
    public void actualizaFisica() {
        //Con el super dibujar primero al calse padre
        super.actualizarFisica();
    }

    //Gestiona las pulsaciones
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