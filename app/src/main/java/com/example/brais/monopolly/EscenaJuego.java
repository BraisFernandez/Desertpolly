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

import java.util.ArrayList;

//Hereda de Escena bc es una escena hija
public class EscenaJuego extends Escena {

    Paint pBoton, pTurno, pNum;
    Bitmap imgVolver, fondoJuego, tablero, dados, player1, player2, imgCeldaVerde, imgCeldaSalida, imgTrebol, imgGatito, imgCeldaAzulOs, imgCeldaAzulCl,
            imgCeldaMarron, imgCeldaNaranja, imgCeldaRoja, imgCeldaRosa, imgCasino, imgCeldaAmarilla, imgCeldaCarcel, imgCeldaPolicia;
    int numAle, posVertPlayer1 = getPixels(110), posHorizPlayer1 = getPixels(35);
    Rect rectDados, rectCompra, rectTurno;
    boolean tirarDados = false, player1turn = false, movHorizPlayer1 = false, movVertiPlayer1 = true, bajando = true, subiendo = false, derecha = true;
    Jugador jugador1 = new Jugador(player1, 0, 1000);
    ArrayList<Casillas> linea = new ArrayList<>();
    Casillas esquina;
    public EscenaJuego(int numEscena, Context contexto, int anchoPantalla, int altoPantalla) {
        super(numEscena, contexto, anchoPantalla, altoPantalla);


        pBoton=new Paint();
        pBoton.setColor(Color.RED);
        pBoton.setAlpha(50);
        pBoton.setTextSize(70);

        pTurno=new Paint();
        pTurno.setColor(Color.BLUE);
        pTurno.setAlpha(50);
        pTurno.setTextSize(70);

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
        rectCompra = new Rect(getPixels(40), getPixels(520), anchoPantalla - getPixels(220), altoPantalla - getPixels(180));
        rectTurno = new Rect(getPixels(220), getPixels(520), anchoPantalla - getPixels(40), altoPantalla - getPixels(180));

        numAle = numDados();

        Jugador jugador2 = new Jugador(player2, 0, 1000);

        player1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.player1);
        player1 = Bitmap.createBitmap(player1);
        player1 = Bitmap.createScaledBitmap(player1, getPixels(20), getPixels(20), true);

        player2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.player2);
        player2 = Bitmap.createBitmap(player2);
        player2 = Bitmap.createScaledBitmap(player2, getPixels(20), getPixels(20), true);


        imgCeldaSalida = BitmapFactory.decodeResource(context.getResources(), R.drawable.celdasalida);
        imgCeldaSalida = Bitmap.createBitmap(imgCeldaSalida);
        imgCeldaSalida = Bitmap.createScaledBitmap(imgCeldaSalida, getPixels(50), getPixels(50), true);

        Casillas salida = new Casillas(null, 200, 0,true, 100, imgCeldaSalida );
        this.linea.add(salida);

        imgTrebol = BitmapFactory.decodeResource(context.getResources(), R.drawable.casillatrebol);
        imgTrebol = Bitmap.createBitmap(imgTrebol);
        imgTrebol = Bitmap.createScaledBitmap(imgTrebol, getPixels(50), getPixels(27), true);

        imgCeldaVerde = BitmapFactory.decodeResource(context.getResources(), R.drawable.celdapeque);
        imgCeldaVerde = Bitmap.createBitmap(imgCeldaVerde);
        imgCeldaVerde = Bitmap.createScaledBitmap(imgCeldaVerde, getPixels(50), getPixels(27), true);

        imgGatito = BitmapFactory.decodeResource(context.getResources(), R.drawable.celdagatito);
        imgGatito = Bitmap.createBitmap(imgGatito);
        imgGatito = Bitmap.createScaledBitmap(imgGatito, getPixels(50), getPixels(27), true);

        imgCeldaRoja = BitmapFactory.decodeResource(context.getResources(), R.drawable.celdaroja);
        imgCeldaRoja = Bitmap.createBitmap(imgCeldaRoja);
        imgCeldaRoja = Bitmap.createScaledBitmap(imgCeldaRoja, getPixels(50), getPixels(27), true);

        imgCeldaRosa = BitmapFactory.decodeResource(context.getResources(), R.drawable.celdarosa);
        imgCeldaRosa = Bitmap.createBitmap(imgCeldaRosa);
        imgCeldaRosa = Bitmap.createScaledBitmap(imgCeldaRosa, getPixels(50), getPixels(27), true);

        imgCeldaMarron = BitmapFactory.decodeResource(context.getResources(), R.drawable.celdamarron);
        imgCeldaMarron = Bitmap.createBitmap(imgCeldaMarron);
        imgCeldaMarron = Bitmap.createScaledBitmap(imgCeldaMarron, getPixels(50), getPixels(27), true);

        imgCeldaAzulCl = BitmapFactory.decodeResource(context.getResources(), R.drawable.celdaazulclaro);
        imgCeldaAzulCl = Bitmap.createBitmap(imgCeldaAzulCl);
        imgCeldaAzulCl = Bitmap.createScaledBitmap(imgCeldaAzulCl, getPixels(50), getPixels(27), true);

        imgCeldaNaranja = BitmapFactory.decodeResource(context.getResources(), R.drawable.celdanaranja);
        imgCeldaNaranja = Bitmap.createBitmap(imgCeldaNaranja);
        imgCeldaNaranja = Bitmap.createScaledBitmap(imgCeldaNaranja, getPixels(50), getPixels(27), true);

        imgCeldaAmarilla = BitmapFactory.decodeResource(context.getResources(), R.drawable.celdaamarilla);
        imgCeldaAmarilla = Bitmap.createBitmap(imgCeldaAmarilla);
        imgCeldaAmarilla = Bitmap.createScaledBitmap(imgCeldaAmarilla, getPixels(50), getPixels(27), true);

        imgCeldaAzulOs = BitmapFactory.decodeResource(context.getResources(), R.drawable.celdaazuloscuro);
        imgCeldaAzulOs = Bitmap.createBitmap(imgCeldaAzulOs);
        imgCeldaAzulOs = Bitmap.createScaledBitmap(imgCeldaAzulOs, getPixels(50), getPixels(27), true);

        imgCasino = BitmapFactory.decodeResource(context.getResources(), R.drawable.celdacasino);
        imgCasino = Bitmap.createBitmap(imgCasino);
        imgCasino = Bitmap.createScaledBitmap(imgCasino, getPixels(50), getPixels(50), true);

        imgCeldaCarcel = BitmapFactory.decodeResource(context.getResources(), R.drawable.celdacarcel);
        imgCeldaCarcel = Bitmap.createBitmap(imgCeldaCarcel);
        imgCeldaCarcel = Bitmap.createScaledBitmap(imgCeldaCarcel, getPixels(50), getPixels(50), true);

        imgCeldaPolicia = BitmapFactory.decodeResource(context.getResources(), R.drawable.celdagrande);
        imgCeldaPolicia = Bitmap.createBitmap(imgCeldaPolicia);
        imgCeldaPolicia = Bitmap.createScaledBitmap(imgCeldaPolicia, getPixels(50), getPixels(50), true);


        for (int i = 1; i < 40; i++) {
            //Log.i("EOOOOOOOOOOOO", "POSICION: " + i);
            if(i == 1|| i == 3){
                Casillas c = new Casillas(null, 100, i,false, 100, imgCeldaVerde);
                this.linea.add(c);
            }
            else if(i == 2 || i == 5 || i == 15|| i == 18 || i == 25 || i == 35 || i == 38){
                Casillas c = new Casillas(null, 100, i,true, 100, imgTrebol);
                this.linea.add(c);

            }
            else if(i == 4 || i == 7 || i == 13 || i == 22 || i == 28 || i == 33 || i == 36){
                Casillas c = new Casillas(null, 100, i, true, 100, imgGatito);
                this.linea.add(c);
            }
           else if(i == 6 || i == 8 || i == 9){
                Casillas c = new Casillas(null, 100, i, false, 100, imgCeldaRoja);
                this.linea.add(c);
            }else if(i == 11 || i == 12 || i == 14){
                Casillas c = new Casillas(null, 100, i, false, 100, imgCeldaRosa);
                this.linea.add(c);
            }else if(i == 16 || i == 17 || i == 19){
                Casillas c = new Casillas(null, 100, i, false, 100, imgCeldaMarron);
                this.linea.add(c);
            }
            else if(i == 21 || i == 23 || i == 24){
                Casillas c = new Casillas(null, 100, i, false, 100, imgCeldaAzulCl);
                this.linea.add(c);
            }
            else if(i == 26 || i == 27 || i == 29){
                Casillas c = new Casillas(null, 100, i, false, 100, imgCeldaNaranja);
                this.linea.add(c);
            }
            else if(i == 31 || i == 32 || i == 34){
                Casillas c = new Casillas(null, 100, i, false, 100, imgCeldaAmarilla);
                this.linea.add(c);
            }
            else if(i == 37 || i == 39){
                Casillas c = new Casillas(null, 100, i, false, 100, imgCeldaAzulOs);
                this.linea.add(c);
            }
            else if(i == 10) {
                Casillas c = new Casillas(null, 100, i, true, 100, imgCeldaCarcel);
                this.linea.add(c);
            }
            else if(i == 20){
                Casillas c = new Casillas(null, 100, i, true, 100, imgCasino);
                this.linea.add(c);

            }else if(i == 30){
                Casillas c = new Casillas(null, 100, i, true, 100, imgCeldaPolicia);
                this.linea.add(c);
            }
            else {
                Casillas c = new Casillas(null, 100, i,false, 100, imgCeldaVerde);
                this.linea.add(c);
            }
        }

//        for (int i = 11; i < 20; i++) {
//            imgCeldaVerde = BitmapFactory.decodeResource(context.getResources(), R.drawable.celdapeque);
//            imgCeldaVerde = Bitmap.createBitmap(imgCeldaVerde);
//            imgCeldaVerde = Bitmap.createScaledBitmap(imgCeldaVerde, getPixels(50), getPixels(27), true);
//
//            //Casillas rotar = new Casillas(null, 100, 0, false, 100, imgCeldaVerde);
//
//            Casillas c2 = new Casillas(null, 100, i,false, 100, imgCeldaVerde);
//            this.linea.add(c2);
//        }
//        for (int i = 21; i < 30; i++) {
//            imgCeldaVerde = BitmapFactory.decodeResource(context.getResources(), R.drawable.celdapeque);
//            imgCeldaVerde = Bitmap.createBitmap(imgCeldaVerde);
//            imgCeldaVerde = Bitmap.createScaledBitmap(imgCeldaVerde, getPixels(50), getPixels(27), true);
//
//            //Casillas rotar = new Casillas(null, 100, 0, false, 100, imgCeldaVerde);
//
//            Casillas c2 = new Casillas(null, 100, i,false, 100, imgCeldaVerde);
//            this.linea.add(c2);
//        }
//        for (int i = 31; i < 40; i++) {
//            imgCeldaVerde = BitmapFactory.decodeResource(context.getResources(), R.drawable.celdapeque);
//            imgCeldaVerde = Bitmap.createBitmap(imgCeldaVerde);
//            imgCeldaVerde = Bitmap.createScaledBitmap(imgCeldaVerde, getPixels(50), getPixels(27), true);
//
//            //Casillas rotar = new Casillas(null, 100, 0, false, 100, imgCeldaVerde);
//
//            Casillas c2 = new Casillas(null, 100, i,false, 100, imgCeldaVerde);
//            this.linea.add(c2);
//        }

        esquina = new Casillas(null, 100, 8,false, 100, imgCeldaSalida);

    }

    //Escena inicial (se le pasa el lienzo (el canvas))
    public void dibujar(Canvas c) {
        super.dibujar(c);

        Movimiento mov = new Movimiento(1, context, anchoPantalla, altoPantalla);

        float vertical = getPixels(80);
        float horizontal = getPixels(10);
        c.drawBitmap(fondoJuego, 0, 0, null);
        c.drawBitmap(imgVolver, getPixels(0), altoPantalla - getPixels(50), null);
//        c.drawBitmap(tablero, getPixels(2), altoPantalla - getPixels(550), null);
        c.drawBitmap(dados, getPixels(140), altoPantalla - getPixels(425), null);
        c.drawRect(rectDados, pBoton);
        c.drawRect(rectCompra, pTurno);
        c.drawRect(rectTurno, pTurno);
        if(tirarDados) {
            c.drawText(numAle + "", getPixels(160), getPixels(350), pNum);
        }
        c.drawBitmap(this.linea.get(0).imgCelda, horizontal, vertical, null);

        vertical += this.linea.get(0).imgCelda.getHeight();
        for (int i =1; i <10; i++ ){

            c.drawBitmap(this.linea.get(i).imgCelda, horizontal, vertical, null);
            vertical += this.linea.get(i).imgCelda.getHeight();
        }
        c.drawBitmap(this.linea.get(10).imgCelda, horizontal, vertical, null);
        horizontal += this.linea.get(0).imgCelda.getHeight();
        for (int i =11; i < 20; i++ ) {

            c.drawBitmap(this.linea.get(i).RotateBitmap((this.linea.get(i).imgCelda), 270), horizontal, vertical, null);
            horizontal += this.linea.get(i).imgCelda.getHeight();
        }
        c.drawBitmap(this.linea.get(20).imgCelda, horizontal, vertical, null);

        for (int i =21; i < 30; i++ ) {

            vertical -= this.linea.get(i).imgCelda.getHeight();
            c.drawBitmap(this.linea.get(i).RotateBitmap((this.linea.get(i).imgCelda), 180), horizontal, vertical, null);
        }

        vertical -= this.linea.get(0).imgCelda.getHeight();
        c.drawBitmap(this.linea.get(30).imgCelda, horizontal, vertical, null);

        for (int i =31; i < 40; i++ ) {
            horizontal -= this.linea.get(i).imgCelda.getHeight();
            c.drawBitmap(this.linea.get(i).RotateBitmap((this.linea.get(i).imgCelda), 90), horizontal, vertical, null);

        }
        //Log.i("NOES","POSICION: " + posVertPlayer1);
        if(player1turn){
            if(!movHorizPlayer1){
                if(bajando) {
                    posVertPlayer1 += mov.mover(numAle);
                }else {
                        Log.i("EEEEEEEEEEEEEEEES", "AQUIIIIIIIIIIIIIIIIIII: " + posVertPlayer1);
                        posVertPlayer1 -= mov.mover(numAle);
                }
                //jugador1.setPosicion(posVertPlayer1);
            }else {

                if(derecha) {
                    posHorizPlayer1 += mov.mover(numAle);
                }else {
                    posHorizPlayer1 -= mov.mover(numAle);
                }
            }
            player1turn = false;

            if (posVertPlayer1 >= 760) {
                posHorizPlayer1 += posVertPlayer1 - 760;
                posVertPlayer1 = 760;
                derecha = true;
            }
            if(posVertPlayer1 <= 220){
                Log.i("EOOOOOOOOOOOO","POSICIONVERTICAL: " + posVertPlayer1);
                posHorizPlayer1 -= posVertPlayer1 - 220;
                posVertPlayer1 = 220;
                derecha = false;
            }
            if(posHorizPlayer1 >= 610){
                posVertPlayer1 -= posHorizPlayer1 - 610;
                posHorizPlayer1 = 610;
                bajando = false;
                subiendo = true;
            }else{
                bajando = true;
                subiendo = false;
            }
            if(posVertPlayer1 < 760 ){
                movHorizPlayer1 = false;
                movVertiPlayer1 = true;
            }else if(posVertPlayer1 >= 760){
                movHorizPlayer1 = true;
                movVertiPlayer1 = false;
            }else if(posVertPlayer1 > 220){
                movHorizPlayer1 = false;
                movVertiPlayer1 = true;
            }else if(posVertPlayer1 <= 220){
                movHorizPlayer1 = true;
                movVertiPlayer1 = false;
            }
        }
        //Log.i("EOOOOOOOOOOOO","POSICION2: " + posVertPlayer1);
        c.drawBitmap(player1, posHorizPlayer1, posVertPlayer1, null);
        c.drawBitmap(player2, getPixels(10), getPixels(110), null);

    }

    public int numDados(){
        return (int)(Math.random() * (12 + 1)-1) + 1;
    }

    //Metodo encargado de actualizar elementos comunes
    public void actualizarFisica() {
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
                    player1turn = true;
                }
                break;
        }

        if(nuevaEscena!=numEscena){
            return nuevaEscena;
        }
        return numEscena;
    }
}