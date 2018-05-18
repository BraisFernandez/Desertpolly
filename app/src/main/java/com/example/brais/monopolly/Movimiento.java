package com.example.brais.monopolly;

import android.content.Context;

public class Movimiento extends Escena{
    public Movimiento(int numEscena, Context context, int anchoPantalla, int altoPantalla) {
        super(numEscena, context, anchoPantalla, altoPantalla);
    }

    public float mover(int dados){
        return  dados * getPixels(27);
    }
}
