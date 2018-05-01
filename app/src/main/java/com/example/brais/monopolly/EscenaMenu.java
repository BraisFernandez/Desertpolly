package com.example.brais.monopolly;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Vibrator;
import android.support.annotation.RequiresApi;
import android.view.MotionEvent;

import java.util.Timer;
import java.util.TimerTask;

import static android.content.Context.VIBRATOR_SERVICE;

public class EscenaMenu extends Escena {

    Rect btnJugar, btnLogros, btnRecords, btnOpc, btnCreditos, btnAyuda, btnSalir;//Creación de rectángulos para gestionar el cambio de escenas
    Typeface faw;//tipo de fuente
    Paint p, pTitulo;//paints del las opciones y del titulo
    MediaPlayer musicaFondo;
    Bitmap fondoMenu;//la imagen de fondo
    
    /*
     * Constructor para gestionar las propiedades de la escena y pintar los rectángulos
     * parametros: int numEscena, para saber la escena
     * Context context, el contexto de escena
     * int colorFondo, el fondo de la escena
     * int anchoPantalla, el ancho de la pantalla
     * int altoPantalla, el alto de la pantalla
     */

    public EscenaMenu(int numEscena, Context context,  int anchoPantalla, int altoPantalla) {
        super(numEscena, context, anchoPantalla, altoPantalla);
        p = new Paint();
        p.setTypeface(faw);
        pTitulo = new Paint();
        pTitulo.setTypeface(faw);
        pTitulo.setColor(Color.BLACK);
        pTitulo.setTextSize(70);
        pTitulo.setTextAlign(Paint.Align.CENTER);

        // Texto Escena
        //pTexto = new Paint();
        p.setColor(Color.BLUE);
        p.setTextSize(getPixels(30));

        fondoMenu = BitmapFactory.decodeResource(context.getResources(), R.drawable.bananamenu);
        fondoMenu = Bitmap.createScaledBitmap(fondoMenu, anchoPantalla, altoPantalla, true);

//        fondoMenu = BitmapFactory.decodeResource(context.getResources(), R.drawable.fondoverde);
//        fondoMenu = Bitmap.createScaledBitmap(fondoMenu, anchoPantalla, altoPantalla, true);
//         Gestionamos la colocación de los rectángulos en el menú principal
        int alto = getPixels(40);
        int separacion = getPixels(20);
        int tamano = (altoPantalla / 3) - getPixels(50);
        btnJugar = new Rect((anchoPantalla / 2) - getPixels(100), tamano, (anchoPantalla / 2) + getPixels(100), tamano + alto);
        tamano += alto + separacion;
        btnLogros = new Rect((anchoPantalla / 2) - getPixels(100), tamano, (anchoPantalla / 2) + getPixels(100), tamano + alto);
        tamano += alto + separacion;
        btnRecords = new Rect((anchoPantalla / 2) - getPixels(100), tamano, (anchoPantalla / 2) + getPixels(100), tamano + alto);
        tamano += alto + separacion;
        btnOpc = new Rect((anchoPantalla / 2) - getPixels(100), tamano, (anchoPantalla / 2) + getPixels(100), tamano + alto);
        tamano += alto + separacion;
        btnCreditos = new Rect((anchoPantalla / 2) - getPixels(100), tamano, (anchoPantalla / 2) + getPixels(100), tamano + alto);
        tamano += alto + separacion;
        btnAyuda = new Rect((anchoPantalla / 2) - getPixels(100), tamano, (anchoPantalla / 2) + getPixels(100), tamano + alto);
        tamano += alto + separacion;
        btnSalir = new Rect((anchoPantalla / 2) - getPixels(100), tamano, (anchoPantalla / 2) + getPixels(100), tamano + alto);
    }

    /*
     * Metodo dibujar que dibuja de forma constante en la pantalla mediante un lienzo (Canvas)
     * Parametros: Canvas c, lienzo donde dibujamos y mosntramos por pantalla
     * Return: void
     * */
    public void dibujar(Canvas c) {
        super.dibujar(c);
        int alto = getPixels(40);
        int separacion = getPixels(20);
        int coorx = (anchoPantalla / 2) - getPixels(80);
        int coory = (((altoPantalla / 3) - getPixels(60)) + alto);
        //c.drawBitmap(fondoMenu, 0, 0, null);
        c.drawBitmap(fondoMenu, 0, 0, null);
        //Dibujamos los rectángulos
        c.drawText("Battle Monster", anchoPantalla / 2, getPixels(75), pTitulo);
        c.drawRect(btnJugar, pBoton);
        c.drawRect(btnLogros, pBoton);
        c.drawRect(btnRecords, pBoton);
        c.drawRect(btnOpc, pBoton);
        c.drawRect(btnCreditos, pBoton);
        c.drawRect(btnAyuda, pBoton);
        c.drawRect(btnSalir, pBoton);
        //Ponemos el nombre de las opciones en los rectangulos

        p.setTextAlign(Paint.Align.CENTER);
        c.drawText("Jugar", anchoPantalla / 2, coory, p);
        coory += alto + separacion;
        c.drawText("EscenaLogros", anchoPantalla / 2, coory, p);
        coory += alto + separacion;
        c.drawText("EscenaRecords", anchoPantalla / 2, coory, p);
        coory += alto + separacion;
        c.drawText("Opciones", anchoPantalla / 2, coory, p);
        coory += alto + separacion;
        c.drawText("EscenaCreditos", anchoPantalla / 2, coory, p);
        coory += alto + separacion;
        c.drawText("EscenaAyuda", anchoPantalla / 2, coory, p);
        coory += alto + separacion;
        c.drawText("Salir", anchoPantalla / 2, coory, p);

        if (numEscena > 1) {
            c.drawRect(bAnt, pBoton);
        }
    }

    /*
     * Metodo actualizarFisica que actualiza todo el tiempo la fisica de la escena
     * Return: void
     * */
    public void actualizarFisica() {
        super.actualizarFisica();
    }

    /*
     * Metodo onTouchEvent que gestiona las pulsaciones del usuario en cada opcion del menu
     * Parametro: MotionEvent even, que nos sirve para localizar el evento de la pulsacion del usuario en la pantalla
     * Return: int
     * */
    public int onTouchEvent(MotionEvent event) {
        int accion = event.getAction();
        switch (accion) {

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                //Si estas dentro del rectangulo del boton anterior...
                if (bAnt.contains((int) event.getX(), (int) event.getY()) && numEscena > 1) {
                    return 1;
                }
                if (btnJugar.contains((int) event.getX(), (int) event.getY()) && numEscena <= 7) {
                    return 2;
                }
                if (btnLogros.contains((int) event.getX(), (int) event.getY()) && numEscena <= 7) {
                    return 3;
                }
                if (btnRecords.contains((int) event.getX(), (int) event.getY()) && numEscena <= 7) {
                    return 4;
                }
                if (btnOpc.contains((int) event.getX(), (int) event.getY()) && numEscena <= 7) {
                    return 5;
                }
                if (btnCreditos.contains((int) event.getX(), (int) event.getY()) && numEscena <= 7) {
                    return 6;
                }
                if (btnAyuda.contains((int) event.getX(), (int) event.getY()) && numEscena <= 7) {
                    return 7;
                }
                if (btnSalir.contains((int) event.getX(), (int) event.getY()) && numEscena <= 7) {
                    System.exit(0);
                }
                break;
        }
        return numEscena;
    }
}