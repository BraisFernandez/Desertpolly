package com.example.brais.monopolly;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

/**
 * Clase EscenaCreditos que hereda de escena y muestra los créditos del juego.
 */
public class EscenaCreditos extends Escena {

    Bitmap fondoescena;//la imagen de fondo
    Paint creditos;//paint para el texto creditos
    int y;//coordenada y
    int tickTexto;//tickTexto para establecer la velocidad de los creditos
    Long tacutal;//tacutal oara guardar los milisegundos
    String[] cads;//vector cads para guardar el texto de agradecimientos
    int separacion = getPixels(40);//int separacion para establecer la separacion entre texto

    /**
     * Constructor de la clase EscenaCreditos
     * @param numEscena
     * @param context
     * @param anchoPantalla
     * @param altoPantalla
     */
    public EscenaCreditos(int numEscena, Context context, int anchoPantalla, int altoPantalla) {
        super(numEscena, context, anchoPantalla, altoPantalla);

        fondoescena = BitmapFactory.decodeResource(context.getResources(), R.drawable.fondoescena);
        fondoescena = Bitmap.createScaledBitmap(fondoescena, anchoPantalla, altoPantalla, true);
        String cred = "Agradecimientos##Por las imágenes##A Freepik##La flecha de retorno#" +
                "Los dados#El icono del juego#La casilla de salida##A FreeImages##" +
                "El fondo de las escenas##A Vecteezy##La celda policía#La celda cárcel#La celda trébol#" +
                "#A Flaticon##La casilla gato#Los personajes##A GabiGa##El nombre del juego#";
        creditos = new Paint();
        creditos.setColor(Color.YELLOW);
        creditos.setTextSize(getPixels(30));
        creditos.setTextAlign(Paint.Align.CENTER);
        cads = cred.split("#");
        y = altoPantalla + 100;
        tacutal = System.currentTimeMillis();
        tickTexto = 1;
    }

    /**
     * Método dibujar, que pinta por pantalla constantemente los créditos del juego
     * @param c
     */
    public void dibujar(Canvas c) {
        super.dibujar(c);
        c.drawBitmap(fondoescena, 0, 0, null);
        c.drawBitmap(imgVolver, getPixels(0), altoPantalla - getPixels(50), null);

        for (int i = 0; i < cads.length; i++) {
            c.drawText(cads[i], anchoPantalla / 2, y + i * separacion, creditos);
            if (y <= -cads.length * 80) {
                y = altoPantalla + 100;
            }
        }
    }

    /**
     * Nétodo acualizaFisica que actualiza constantemente la física del juego
     */
    public void actualizarFisica() {
        if (System.currentTimeMillis() - tacutal > tickTexto) {
            y -= getPixels(1);
            tacutal = System.currentTimeMillis();
        }
        super.actualizarFisica();
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
