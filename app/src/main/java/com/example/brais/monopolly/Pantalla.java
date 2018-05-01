package com.example.brais.monopolly;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Vibrator;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PointF;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

import java.util.Timer;
import java.util.TimerTask;

import static android.content.Context.VIBRATOR_SERVICE;

/**
 * Created by Brais Fernández on 24/01/2018.
 */
/*
 * Clase EscenaMenu
 * En esta escena que hereda de Escena, gestionamos el control de escenas, es decir, todas las escenas se lanzan en esta.
 * */
//Paso 1: creamos la clase pantalla, que extiende e implementa, sobreescribimos los tres metodos y metemos el construnctor
public class Pantalla extends SurfaceView implements SurfaceHolder.Callback {
    //Instanciamos las propiedades
    private SurfaceHolder surfaceHolder;//variable surfaceHolder que se utiliza para el Holder
    Context context;//contexto de la imagen
    boolean funcionando = false;//boolean que indica un funcionamiento
    Hilo hilo;//hilo de la clase
    int altoPantalla = 0, anchoPantalla = 0;//enteros que indican alto y ancho de pantalla
    Escena escenaActual;//Escena actual


    /*
     * Constructor de la clase Pantalla en la que se inicialican diversons componentes citado anteriormente
     * Parametros: Context context, para trabajar con el contexto actual
     * */
    public Pantalla(Context context) {
        super(context);
        this.context = context;
        this.surfaceHolder = getHolder();       // Se obtiene el holder
        this.surfaceHolder.addCallback(this);   // Se indica donde van las funciones callback
        hilo = new Hilo();
        //detectorGestos = new GestureDetector(context, new MisGestos());

    }

    /*Override del metodo SurfaceCreated*/
    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {


    }

    /*En el onTouchEvent gestionamos la creación de nuevas escenas valiendónos del constructor de la clase genérica Escena
    y d del constructor de cada clase que hereda de escena, pasando como parámetro el contexto, un color de fondo, el ancho de pantalla
    y el alto de pantalla
    * Parametro: MotionEvent even, que nos sirve para localizar el evento de la pulsacion del usuario en la pantalla
    */
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int nuevaEscena = escenaActual.onTouchEvent(event);

        if (nuevaEscena != escenaActual.getNumEscena()) {
            switch (nuevaEscena) {
                case 1:
                    escenaActual = new EscenaMenu(1, context, anchoPantalla, altoPantalla);
                    break;
                case 2:
                    escenaActual = new EscenaJuego(2, context, anchoPantalla, altoPantalla);
                    break;
                case 3:
                    escenaActual = new EscenaLogros(3, context, anchoPantalla, altoPantalla);
                    break;
                case 4:
                    escenaActual = new EscenaRecords(4, context, anchoPantalla, altoPantalla);
                    break;
                case 5:
                    escenaActual = new EscenaOpciones(5, context, anchoPantalla, altoPantalla);
                    break;
                case 6:
                    escenaActual = new EscenaCreditos(6, context, anchoPantalla, altoPantalla);
                    break;
                case 7:
                    escenaActual = new EscenaAyuda(7, context, anchoPantalla, altoPantalla);
                    break;
            }
        }
        return true;
    }

    /*Sobreescribimos el surfaceChanged para poder trabajar con el hilo
     * Parametros: SurfaceHolder surfaceHolder, para sincronizar el actualiza fisica y el canvas
     * int format, para el formateo
     * int width, para saber el ancho de la pantalla
     * int height para saber el alto de la pantalla
     * */
    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int format, int width, int height) {
        anchoPantalla = width;
        altoPantalla = height;
        escenaActual = new EscenaMenu(1, context, anchoPantalla, altoPantalla);
        if (!funcionando) {
            funcionando = true;
            if (hilo.getState() == Thread.State.NEW) {
                hilo.start(); // si el hilo no ha sido creado se crea;
            }

            if (hilo.getState() == Thread.State.TERMINATED) {      // si el hilo ha sido finalizado se crea de nuevo;
                hilo = new Hilo();
                hilo.start(); // se arranca el hilo
            }
        }
    }

    /*Sobreescribimos el SurfaceDestroyed para poder poner el hilo en espera a que finalice
     * */
    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        funcionando = false;
        try {
            hilo.join();   // Se espera a que finalize
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /*Creamos una clase Hilo que hereda de Thread y maneja los metodos dibujar y actualizar física además del Canvas*/
    class Hilo extends Thread {
        @Override
        public void run() {
            while (funcionando) {
                Canvas c = null;
                try {
                    c = surfaceHolder.lockCanvas();
                    if (c != null) {
                        synchronized (surfaceHolder) {
                            escenaActual.actualizarFisica();
                            escenaActual.dibujar(c);
                        }
                    }
                } finally {
                    if (c != null) surfaceHolder.unlockCanvasAndPost(c);
                }
            }
        }
    }

}