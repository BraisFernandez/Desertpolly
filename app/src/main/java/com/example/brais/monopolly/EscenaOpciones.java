package com.example.brais.monopolly;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class EscenaOpciones extends Escena {

    Bitmap fondoescena;//la imagen de fondo

    public EscenaOpciones(int numEscena, Context context, int anchoPantalla, int altoPantalla) {
        super(numEscena, context, anchoPantalla, altoPantalla);

        fondoescena = BitmapFactory.decodeResource(context.getResources(), R.drawable.fondoescena);
        fondoescena = Bitmap.createScaledBitmap(fondoescena, anchoPantalla, altoPantalla, true);
    }
    public void dibuja(Canvas c) {
        super.dibujar(c);
        c.drawBitmap(fondoescena, 0, 0, null);
        c.drawBitmap(imgVolver, getPixels(0), altoPantalla - getPixels(50), null);
    }
}
