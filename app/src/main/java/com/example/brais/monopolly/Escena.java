package com.example.brais.monopolly;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Vibrator;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.WindowManager;

//Creamos la clase Escena
public class Escena {

    SoundPool sonidos;//SoundPool sonidos que establece un sonido
    final private int maxSonidosSimultaneos=10;//int maxSonidosSimultaneos que indica cuantos sonidos se pueden escuchar a la vez
    int numEscena;//int numeEscena que indica el numero de las escena
    Context context;//cotext de la clase
   // int colorFondo;//int colorFondo que establece un color de fondo
    Paint pTexto,pBoton,pVolver;//Paints pTexto, para el texto, pBoton, para los botones y pVOlver para el boton volver
    int anchoPantalla, altoPantalla;//int anchoPantalla que establece el ancho de pantalla y altoPantalla que establece el alto de pantalla
    Rect bAnt;//Rect bAnt que sirve para volver a la escenaMenu
    //Bitmap bVolver;//Bitmap bVolver que se situa en bAnt
    AudioManager audioManager;//audioManager que sirve para gestionar la musica
    boolean sonido = true, vibracion = true, primeraVez = true;//booleanos sonido, bibracion y primeraVez
    Bitmap imgVolver;

    /*
     * Constructor de Escena que da valor a los paints, situa botones...
     * parametros: int numEscena, para saber la escena
     * Context context, el contexto de escena
     * int colorFondo, el fondo de la escena
     * int anchoPantalla, el ancho de la pantalla
     * int altoPantalla, el alto de la pantalla
     * */
    public Escena(int numEscena, Context context, int anchoPantalla, int altoPantalla) {

        this.numEscena=numEscena;
        this.context=context;
        this.anchoPantalla=anchoPantalla;
        this.altoPantalla=altoPantalla;
       // colorFondo = Color.GREEN;
        pTexto=new Paint();
        //pTexto.setColor(Color.RED);
        pTexto.setTextSize(getPixels(50));
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

        bAnt=new Rect(0,altoPantalla-getPixels(50),getPixels(50),altoPantalla);


    }
    /*
     * Metodo dibujar que dibuja el boton volver
     * Paramentro: Canvas c, lienzo del que nos valemos para dibujar por pantalla
     * */
    public void dibujar(Canvas c){
        //c.drawColor(colorFondo);
        //c.drawText("Escena "+numEscena,getPixels(10),getPixels(60),pTexto);
        if (numEscena>1){
            c.drawRect(bAnt,pVolver);
            c.drawBitmap(imgVolver, getPixels(0), altoPantalla - getPixels(50), null);
        }

    }
    /*
     * Getter numEscena
     * Return: numEscena*/
    public int getNumEscena() {
        return numEscena;
    }
    /*
     * Metodo actualizaFisica que sirve para actualizar constantemente la fisica del juego
     * Return: void*/
    public void actualizarFisica(){

    }
    /*
     * Metodo onTouchEvent que gestiona las pulsaciones de botones
     * Parametro: MotionEvent even, que nos sirve para localizar el evento de la pulsacion del usuario en la pantalla
     * */
    public int onTouchEvent(MotionEvent event) {
        int accion=event.getAction();
        switch (accion) {

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                if (bAnt.contains((int) event.getX(), (int) event.getY()) && numEscena > 1)
                    return (numEscena = 1);
        }
        return numEscena;
    }
    /*
     * Metodo escalaAnchura que escla el ancho de una imagen
     * Parametros: int res, lo utilizamos para coger el recurso
     * int nuevoAncho, para coger al ancho a escalar la imagen
     * Return: Bitmap*/
    public Bitmap escalaAnchura(int res, int nuevoAncho) {
        Bitmap bitmapAux= BitmapFactory.decodeResource(context.getResources(), res);
        if (nuevoAncho==bitmapAux.getWidth()) return bitmapAux;
        return bitmapAux.createScaledBitmap(bitmapAux, nuevoAncho, (bitmapAux.getHeight() * nuevoAncho) / bitmapAux.getWidth(),true);
    }
    /*
     * Metodo escalaAltura que escla el alto de una imagen
     * Parametros: int res, lo utilizamos para coger el recurso
     * int nuevoAncho, para coger al alto a escalar la imagen
     * Return: Bitmap*/
    public Bitmap escalaAltura(int res, int nuevoAlto ) {
        Bitmap bitmapAux=BitmapFactory.decodeResource(context.getResources(), res);
        if (nuevoAlto==bitmapAux.getHeight()) return bitmapAux;
        return bitmapAux.createScaledBitmap(bitmapAux, (bitmapAux.getWidth() * nuevoAlto) / bitmapAux.getHeight(), nuevoAlto, true);
    }
    /*
     * Metodo getPixels que establece unos pixeles dp como genericos para distintas resoluciones de pantalla
     * Parametro: flat dp, para saber los pixeles, el tamaño de nuestro elemento
     * Return: int */
    int getPixels(float dp) {
        DisplayMetrics metrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(metrics);
        return (int) (dp * metrics.density);
    }
}
