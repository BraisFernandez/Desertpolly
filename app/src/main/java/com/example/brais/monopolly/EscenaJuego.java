package com.example.brais.monopolly;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;

//Hereda de Escena bc es una escena hija
public class EscenaJuego extends Escena {

    Paint pBoton, pNum;
    Bitmap imgVolver, fondoJuego, tablero, dados, player1, player2;
    int numAle;
    Rect rectDados;
    boolean tirarDados = false;

    public EscenaJuego(int numEscena, Context contexto, int anchoPantalla, int altoPantalla) {
        super(numEscena, contexto, anchoPantalla, altoPantalla);

        pBoton=new Paint();
        pBoton.setColor(Color.RED);
        pBoton.setAlpha(50);
        pBoton.setTextSize(70);

        imgVolver = BitmapFactory.decodeResource(context.getResources(), R.drawable.flechamenu);
        imgVolver = Bitmap.createBitmap(imgVolver);
        imgVolver = Bitmap.createScaledBitmap(imgVolver, getPixels(50), getPixels(50), true);

        fondoJuego = BitmapFactory.decodeResource(context.getResources(), R.drawable.fondoescena);
        fondoJuego = Bitmap.createScaledBitmap(fondoJuego, anchoPantalla, altoPantalla, true);

        tablero = BitmapFactory.decodeResource(context.getResources(), R.drawable.monopolyboard);
        tablero = Bitmap.createBitmap(tablero);
        tablero = Bitmap.createScaledBitmap(tablero, getPixels(355), getPixels(355), true);

        dados = BitmapFactory.decodeResource(context.getResources(), R.drawable.dados);
        dados = Bitmap.createBitmap(dados);
        dados = Bitmap.createScaledBitmap(dados, getPixels(80), getPixels(80), true);

        pNum = new Paint();
        pNum.setColor(Color.RED);
        pNum.setTextSize(getPixels(20));

        rectDados = new Rect(getPixels(120), getPixels(200), anchoPantalla - getPixels(120), altoPantalla - getPixels(330));

        numAle = numDados();

        Jugador jugador1 = new Jugador(player1, 0, 1000);
        Jugador jugador2 = new Jugador(player2, 0, 1000);


    }

    //Escena inicial (se le pasa el lienzo (el canvas))
    public void dibujar(Canvas c) {
        super.dibujar(c);
        c.drawBitmap(fondoJuego, 0, 0, null);
        c.drawBitmap(imgVolver, getPixels(0), altoPantalla - getPixels(50), null);
        c.drawBitmap(tablero, getPixels(2), altoPantalla - getPixels(550), null);
        c.drawBitmap(dados, getPixels(140), altoPantalla - getPixels(425), null);
        c.drawRect(rectDados, pBoton);
        if(tirarDados)
        c.drawText(numAle+"", getPixels(160), getPixels(350), pNum);

    }

    public int numDados(){
        return (int)(Math.random() * (12 + 1)-1) + 1;
    }

    //Metodo encargado de actualizar elementos comunes
    public void actualizarFisica() {
        //Con el super dibujar primero al clase padre
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
                if (rectDados.contains((int) event.getX(), (int) event.getY())) {
                    tirarDados = true;
                    numAle = numDados();
                }
                break;
        }

        if(nuevaEscena!=numEscena){
            return nuevaEscena;
        }
        return numEscena;
    }
}