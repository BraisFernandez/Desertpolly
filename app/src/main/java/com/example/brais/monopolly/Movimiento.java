package com.example.brais.monopolly;

import android.content.Context;

/**
 * Clase movimiento que hereda de escena y calcula el movimiento de los personajes
 */
public class Movimiento extends Escena{
    /**
     * Constructor de la clase Movimiento
     * @param numEscena
     * @param context
     * @param anchoPantalla
     * @param altoPantalla
     */
    public Movimiento(int numEscena, Context context, int anchoPantalla, int altoPantalla) {
        super(numEscena, context, anchoPantalla, altoPantalla);
    }

    /**
     * Método mover que calcula el movimiento de los personajes dependiendo del parámetro (el lanzamiento de dado)
     * @param dados
     * @return float
     */
    public float mover(int dados){
        return  dados * getPixels(27);
    }
}
