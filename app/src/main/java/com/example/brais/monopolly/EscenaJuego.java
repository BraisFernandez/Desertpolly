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


/**
* Clase EscenaJuego que hereda de Escena. Esta es la clase donde organizo las casillas del tablero y añado sus posiciones en una colección.
* */
public class EscenaJuego extends Escena {
    Paint pBoton, pTurno, pNum, pDinero, pCompra;
    Bitmap imgVolver, fondoJuego, tablero, dados, player1, player2, imgCeldaVerde, imgCeldaSalida, imgTrebol, imgGatito, imgCeldaAzulOs, imgCeldaAzulCl,
            imgCeldaMarron, imgCeldaNaranja, imgCeldaRoja, imgCeldaRosa, imgCasino, imgCeldaAmarilla, imgCeldaCarcel, imgCeldaPolicia;
    int numAle, posVertPlayer1 = getPixels(110), posHorizPlayer1 = getPixels(35), posVertPlayer2 = getPixels(110), posHorizPlayer2 = getPixels(35),
            casillaPlayer1 = 0, casillaPlayer2 = 0, llervarseDinero = 0, trebol_gato = 0;
    Rect rectDados, rectCompra, rectTurno;
    boolean tirarDados = false, player1turn = false,dVertical = true, dVerticalPlayer2 = true, subir = true, subirPlayer2 = true, conDados = true, alacarcel = false, entrando = false,
    ganandoDinero = false, perdiendoDinero = false;
    Jugador jugador1 = new Jugador(player1, 0, 1000, true);
    Jugador jugador2 = new Jugador(player2, 0, 1000, false);
    ArrayList<Casillas> linea = new ArrayList<>();
    Casillas esquina;
    Casillas c = new Casillas(100, 0,false, 100, imgCeldaVerde, 0);
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

        pDinero=new Paint();
        pDinero.setColor(Color.YELLOW);
        pDinero.setTextSize(40);

        pCompra=new Paint();
        pCompra.setColor(Color.GREEN);
        pCompra.setTextSize(40);

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
        rectCompra = new Rect(getPixels(40), getPixels(440), anchoPantalla - getPixels(220), altoPantalla - getPixels(120));
        rectTurno = new Rect(getPixels(220), getPixels(440), anchoPantalla - getPixels(40), altoPantalla - getPixels(120));

        numAle = numDados();

        player1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.player1);
        player1 = Bitmap.createBitmap(player1);
        player1 = Bitmap.createScaledBitmap(player1, getPixels(20), getPixels(20), true);

        player2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.player2);
        player2 = Bitmap.createBitmap(player2);
        player2 = Bitmap.createScaledBitmap(player2, getPixels(20), getPixels(20), true);


        imgCeldaSalida = BitmapFactory.decodeResource(context.getResources(), R.drawable.celdasalida);
        imgCeldaSalida = Bitmap.createBitmap(imgCeldaSalida);
        imgCeldaSalida = Bitmap.createScaledBitmap(imgCeldaSalida, getPixels(50), getPixels(50), true);

        Casillas salida = new Casillas(200, 0,true, 100, imgCeldaSalida, 0);
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
                 c = new Casillas(100, i,false, 100, imgCeldaVerde, 0);//25
                this.linea.add(c);
            }
            else if(i == 2 || i == 5 || i == 15|| i == 18 || i == 25 || i == 35 || i == 38){
                 c = new Casillas(0, i,true, 100, imgTrebol, 0);
                this.linea.add(c);

            }
            else if(i == 4 || i == 7 || i == 13 || i == 22 || i == 28 || i == 33 || i == 36){
                 c = new Casillas(0, i, true, 100, imgGatito, 0);
                this.linea.add(c);
            }
           else if(i == 6 || i == 8 || i == 9){
                 c = new Casillas(150, i, false, 100, imgCeldaRoja, 50);//50
                this.linea.add(c);
            }else if(i == 11 || i == 12 || i == 14){
                 c = new Casillas(200, i, false, 100, imgCeldaRosa, 75);//75
                this.linea.add(c);
            }else if(i == 16 || i == 17 || i == 19){
                 c = new Casillas(250, i, false, 100, imgCeldaMarron, 100);//100
                this.linea.add(c);
            }
            else if(i == 21 || i == 23 || i == 24){
                 c = new Casillas(300, i, false, 100, imgCeldaAzulCl, 125);//125
                this.linea.add(c);
            }
            else if(i == 26 || i == 27 || i == 29){
                 c = new Casillas(300, i, false, 100, imgCeldaNaranja, 150);//150
                this.linea.add(c);
            }
            else if(i == 31 || i == 32 || i == 34){
                 c = new Casillas(350, i, false, 100, imgCeldaAmarilla, 175);//175
                this.linea.add(c);
            }
            else if(i == 37 || i == 39){
                 c = new Casillas(400, i, false, 100, imgCeldaAzulOs, 200);//200
                this.linea.add(c);
            }
            else if(i == 10) {
                 c = new Casillas(100, i, true, 100, imgCeldaCarcel, 0);
                this.linea.add(c);
            }
            else if(i == 20){
                 c = new Casillas(100, i, true, 100, imgCasino, 0);
                this.linea.add(c);

            }else if(i == 30){
                 c = new Casillas(100, i, true, 100, imgCeldaPolicia, 0);
                this.linea.add(c);
            }
            else {
                 c = new Casillas(100, i,false, 100, imgCeldaVerde, 0);
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

        esquina = new Casillas(100, 8,false, 100, imgCeldaSalida, 0);

    }
    Movimiento mov = new Movimiento(1, context, anchoPantalla, altoPantalla);
    /**
     * Método dibujar en el que muestro por pantalla todos los elementos de la escena, realizo una creación dinámica del tablero y llamo a las funciones que organizan el movimiento
     * de los personajes y el funcionamiento del juego
     */
    public void dibujar(Canvas c) {
        super.dibujar(c);
        float vertical = getPixels(80);
        float horizontal = getPixels(10);
        c.drawBitmap(fondoJuego, 0, 0, null);
        c.drawBitmap(imgVolver, getPixels(0), altoPantalla - getPixels(50), null);
//        c.drawBitmap(tablero, getPixels(2), altoPantalla - getPixels(550), null);
        c.drawBitmap(dados, getPixels(140), altoPantalla - getPixels(425), null);
        c.drawRect(rectDados, pBoton);
        c.drawRect(rectCompra, pTurno);
        c.drawRect(rectTurno, pTurno);
        c.drawText("Comprar", getPixels(50), getPixels(495), pCompra);
        c.drawText("Terminar", getPixels(230), getPixels(490), pCompra);
        c.drawText("Turno", getPixels(245), getPixels(510), pCompra);
        c.drawText("Player 1: " + jugador1.dinero + " €", getPixels(20), getPixels(50), pDinero);
        c.drawText("Player 2: " + jugador2.dinero + " €", getPixels(180), getPixels(50), pDinero);

        if (tirarDados) {
            c.drawText(numAle + "", getPixels(160), getPixels(350), pNum);
        }
        c.drawBitmap(this.linea.get(0).imgCelda, horizontal, vertical, null);

        vertical += this.linea.get(0).imgCelda.getHeight();
        for (int i = 1; i < 10; i++) {

            c.drawBitmap(this.linea.get(i).imgCelda, horizontal, vertical, null);
            vertical += this.linea.get(i).imgCelda.getHeight();
        }
        c.drawBitmap(this.linea.get(10).imgCelda, horizontal, vertical, null);
        horizontal += this.linea.get(0).imgCelda.getHeight();
        for (int i = 11; i < 20; i++) {

            c.drawBitmap(this.linea.get(i).RotateBitmap((this.linea.get(i).imgCelda), 270), horizontal, vertical, null);
            horizontal += this.linea.get(i).imgCelda.getHeight();
        }
        c.drawBitmap(this.linea.get(20).imgCelda, horizontal, vertical, null);

        for (int i = 21; i < 30; i++) {

            vertical -= this.linea.get(i).imgCelda.getHeight();
            c.drawBitmap(this.linea.get(i).RotateBitmap((this.linea.get(i).imgCelda), 180), horizontal, vertical, null);
        }

        vertical -= this.linea.get(0).imgCelda.getHeight();
        c.drawBitmap(this.linea.get(30).imgCelda, horizontal, vertical, null);

        for (int i = 31; i < 40; i++) {
            horizontal -= this.linea.get(i).imgCelda.getHeight();
            c.drawBitmap(this.linea.get(i).RotateBitmap((this.linea.get(i).imgCelda), 90), horizontal, vertical, null);

        }
        //Log.i("NOES","POSICION: " + posVertPlayer1);
        turnoPlayer1();
        turnoPlayer2();
        if(alacarcel){
            c.drawText("A la carcel!! ", anchoPantalla/3, altoPantalla - getPixels(70), pDinero);
            //alacarcel = false;
        }
        if(entrando){
            c.drawText("Sumas 200€ !! ", anchoPantalla/3, altoPantalla - getPixels(70), pDinero);
            //entrando = false;
        }
        if(ganandoDinero){
            Log.i("BOOLEANA","BOOLEANA"+ ganandoDinero);
            c.drawText("Sumas 50€ :) ", anchoPantalla/3, altoPantalla - getPixels(70), pDinero);
            //ganandoDinero = false;
        }
        if(perdiendoDinero){
            c.drawText("Pierdes 50€ :( ", anchoPantalla/3, altoPantalla - getPixels(70), pDinero);
           // perdiendoDinero = false;
        }
        if(jugador1.getDinero() <= 0){
            c.drawText("¡¡¡ Ha ganado el Jugador 2 !!!", anchoPantalla/3, altoPantalla - getPixels(70), pDinero);
        }
        if(jugador2.getDinero() <= 0){
            c.drawText("¡¡¡ Ha ganado el Jugador 1 !!!", anchoPantalla/3, altoPantalla - getPixels(70), pDinero);
        }
        c.drawBitmap(player1, posHorizPlayer1, posVertPlayer1, null);
        c.drawBitmap(player2, posHorizPlayer2, posVertPlayer2, null);

    }
    /**
     * Método llemarMeta el cual suma 200€ al jugador correspondiente cuando llega a la casilla principal.
     */

    public void llegarMeta(){
        if(jugador1.isTengoTurno()) {
            if (casillaPlayer1 > (casillaPlayer1 + numAle) % 40) {
                jugador1.setDinero(jugador1.getDinero() + 200);
                entrando = true;
                //Log.i("DENTRO IF 1", "Casilla: " + casillaPlayer1 + " de: " + linea.size());
            }
        }
        else {
            if (casillaPlayer2 > (casillaPlayer2 + numAle) % 40) {
                jugador2.setDinero(jugador2.getDinero() + 200);
                entrando = true;
                //Log.i("DENTRO IF 2", "Casilla: " + casillaPlayer2 + " de: " + linea.size());
            }
        }
    }

    /**
     *Método turnoPLayer1 que organiza el movimiento del jugador 1
     */
    public void turnoPlayer1(){
        if (jugador1.isTengoTurno())
        {
            if (player1turn) {
                if (dVertical) {
                    if (subir) {
                        posVertPlayer1 -= mov.mover(numAle);
                    } else {
                        posVertPlayer1 += mov.mover(numAle);
                    }
                } else {
                    if (subir) {
                        posHorizPlayer1 -= mov.mover(numAle);
                    } else {
                        posHorizPlayer1 += mov.mover(numAle);
                    }
                }
                llegarMeta();

                casillaPlayer1 = (casillaPlayer1 + numAle) % 40;

                if (cambiarPosicion()) {
                    casillaPlayer1 = (10) % 40;
                    //casillaPlayer1 = (casillaPlayer1 + 3) % 40;
                    if (dVertical) {
                        if (subir) {
                            posVertPlayer1 -= mov.mover(20);
                        } else {
                            posVertPlayer1 += mov.mover(20);
                        }
                    } else {
                        if (subir) {
                            posHorizPlayer1 -= mov.mover(20);
                        } else {
                            posHorizPlayer1 += mov.mover(20);
                        }
                    }
                }
                player1turn = false;
            }
            if (posVertPlayer1 <= getPixels(110) && posHorizPlayer1 <= getPixels(35)) {
                dVertical = true;
                subir = false;
                posVertPlayer1 -= posHorizPlayer1 - getPixels(35);
                posHorizPlayer1 = getPixels(35);
            } else if (posVertPlayer1 >= getPixels(380) && posHorizPlayer1 <= getPixels(35)) {
                dVertical = false;
                subir = false;
                posHorizPlayer1 += posVertPlayer1 - getPixels(380);
                posVertPlayer1 = getPixels(380);
            } else if (posVertPlayer1 >= getPixels(380) && posHorizPlayer1 >= getPixels(305)) {
                dVertical = true;
                subir = true;
                posVertPlayer1 -= posHorizPlayer1 - getPixels(305);
                posHorizPlayer1 = getPixels(305);
            } else if (posVertPlayer1 <= getPixels(110) && posHorizPlayer1 >= getPixels(305)) {
                dVertical = false;
                subir = true;
                posHorizPlayer1 += posVertPlayer1 - getPixels(110);
                posVertPlayer1 = getPixels(110);
            }
            pagarCasillaJugador1(casillaPlayer1);
            casillasEspecialesPlayer1();
            Log.i("DUEÑO","TURNOJUGADOR1" + this.linea.get(casillaPlayer1).getDueño());
        }
    }
    /**
     * Método turnoPlayer2 que organiza el movimiento del jugador 2
     */
    public void turnoPlayer2(){
        if (jugador2.isTengoTurno())
        {
            if (player1turn) {
                if (dVerticalPlayer2) {
                    if (subirPlayer2) {
                        posVertPlayer2 -= mov.mover(numAle);
                    } else {
                        posVertPlayer2 += mov.mover(numAle);
                    }
                } else {
                    if (subirPlayer2) {
                        posHorizPlayer2 -= mov.mover(numAle);
                    } else {
                        posHorizPlayer2 += mov.mover(numAle);
                    }
                }
                llegarMeta();
                //casillasEspecialesPlayer2();
                casillaPlayer2 = (casillaPlayer2 + numAle) % 40;
                if (cambiarPosicionPlayer2()) {
                    casillaPlayer2 = (10) % 40;
                    if (dVerticalPlayer2) {
                        if (subirPlayer2) {
                            posVertPlayer2 -= mov.mover(20);
                        } else {
                            posVertPlayer2 += mov.mover(20);
                        }
                    } else {
                        if (subirPlayer2) {
                            posHorizPlayer2 -= mov.mover(20);
                        } else {
                            posHorizPlayer2 += mov.mover(20);
                        }
                    }
                }
                player1turn = false;
            }
            if (posVertPlayer2 <= getPixels(110) && posHorizPlayer2 <= getPixels(35)) {
                dVerticalPlayer2 = true;
                subirPlayer2 = false;
                posVertPlayer2 -= posHorizPlayer2 - getPixels(35);
                posHorizPlayer2 = getPixels(35);
            } else if (posVertPlayer2 >= getPixels(380) && posHorizPlayer2 <= getPixels(35)) {
                dVerticalPlayer2 = false;
                subirPlayer2 = false;
                posHorizPlayer2 += posVertPlayer2 - getPixels(380);
                posVertPlayer2 = getPixels(380);
            } else if (posVertPlayer2 >= getPixels(380) && posHorizPlayer2 >= getPixels(305)) {
                dVerticalPlayer2 = true;
                subirPlayer2 = true;
                posVertPlayer2 -= posHorizPlayer2 - getPixels(305);
                posHorizPlayer2 = getPixels(305);
            } else if (posVertPlayer2 <= getPixels(110) && posHorizPlayer2 >= getPixels(305)) {
                dVerticalPlayer2 = false;
                subirPlayer2 = true;
                posHorizPlayer2 += posVertPlayer2 - getPixels(110);
                posVertPlayer2 = getPixels(110);
            }
            pagarCasillaJugador2(casillaPlayer2);
            casillasEspecialesPlayer2();
            //Log.i("DUEÑO","TURNOJUGADOR2" + this.linea.get(casillaPlayer2).getDueño());
        }
    }
    /**
     * Método casillasEspecialesPlayer1 que determina las casillas especiales y realiza la correspondiente acción cuando el jugador 1 cae en una.
     * */
    public void casillasEspecialesPlayer1(){
        if(casillaPlayer1 == 2 || casillaPlayer1 == 5 || casillaPlayer1 == 15 || casillaPlayer1 == 18 || casillaPlayer1 == 25 || casillaPlayer1 == 35 || casillaPlayer1 == 38){
            if(trebol_gato == 0) {
                jugador1.setDinero(jugador1.getDinero() + 50);
                ganandoDinero = true;
                trebol_gato++;
            }
        }
        if(casillaPlayer1 == 4 || casillaPlayer1 == 7 || casillaPlayer1 == 13 || casillaPlayer1 == 22 || casillaPlayer1 == 28 || casillaPlayer1 == 33 || casillaPlayer1 == 36){
            if(trebol_gato == 0) {
                jugador1.setDinero(jugador1.getDinero() - 50);
                perdiendoDinero = true;
                trebol_gato ++;
            }
        }
    }
    /**
     * Método casillasEspecialesPlayer2 que determina las casillas especiales y realiza la correspondiente acción cuando el jugador 2 cae en una.
     * */
    public void casillasEspecialesPlayer2(){

        if(casillaPlayer2 == 2 || casillaPlayer2 == 5 || casillaPlayer2 == 15 || casillaPlayer2 == 18 || casillaPlayer2 == 25 || casillaPlayer2 == 35 || casillaPlayer2 == 38){
            if(trebol_gato == 0) {
                jugador2.setDinero(jugador2.getDinero() + 50);
                ganandoDinero = true;
                trebol_gato ++;
            }
        }
        if(casillaPlayer2 == 4 || casillaPlayer2 == 7 || casillaPlayer2 == 13 || casillaPlayer2 == 22 || casillaPlayer2 == 28 || casillaPlayer2== 33 || casillaPlayer2 == 36){
            if(trebol_gato == 0) {
                jugador2.setDinero(jugador2.getDinero() - 50);
                perdiendoDinero = true;
                trebol_gato ++;
            }
        }
    }
    /**
     * Método cambiar posición que indica si el jugador 1 ha caído en la carcel
     * Return: boolean
     * */
    public boolean cambiarPosicion(){
        if(casillaPlayer1 == 30){
            alacarcel = true;
            return true;
        }
        return false;
    }
    /**
     * Método cambiar posición que indica si el jugador 2 ha caído en la carcel
     * @return : boolean
     * */
    public boolean cambiarPosicionPlayer2(){
        if(casillaPlayer2 == 30){
            alacarcel = true;
            return true;
        }
        return false;
    }
    /**
     * Método numDados que devuelve un número aleatorio entre 1 y 12, en el que se basará el movimiento de los jugadores en cuanto al lanzamiento de dado.
     * @return : int.
     * */
    public int numDados(){
        if(conDados) {
            //conDados = false;
            alacarcel = false;
            entrando = false;
            ganandoDinero = false;
            perdiendoDinero = false;
            return (int) (Math.random() * (12 + 1) - 1) + 1;
        }
        else {
            return 0;
        }
    }

    /**
     * Nétodo acualizaFisica que actualiza constantemente la física del juego
     */
    public void actualizarFisica() {
        super.actualizarFisica();
    }

    /**
     * Método que gestiona las pulsaciones del usuario en la pantalla.
     *
     * @param event
     * @return int
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
                if (rectDados.contains((int) event.getX(), (int) event.getY())) {
                    tirarDados = true;
                    numAle = numDados();
                    conDados = false;
                    player1turn = true;
                    llervarseDinero = 0;
                    trebol_gato = 0;
                }
                if (rectTurno.contains((int) event.getX(), (int) event.getY())) {
                    numAle = 0;
                    conDados = true;
                    if(jugador1.isTengoTurno()){
                        jugador1.setTengoTurno(false);
                        jugador2.setTengoTurno(true);
                    }
                    else{
                        jugador2.setTengoTurno(false);
                        jugador1.setTengoTurno(true);
                    }
                }
                if (rectCompra.contains((int) event.getX(), (int) event.getY())) {
                    if(jugador1.isTengoTurno()){
                        comprarCasilla(jugador1, casillaPlayer1);
                    }else {
                        comprarCasilla(jugador2, casillaPlayer2);
                    }
                }
                break;
        }

        if(nuevaEscena!=numEscena){
            return nuevaEscena;
        }
        return numEscena;
    }

    /**
     * Método comprarCasilla que permite a los jugadores comprar una casilla, siempre que esta no tenga dueño
     * @param j
     * @param posicion
     */
    public void comprarCasilla(Jugador j, int posicion){
        if(this.linea.get(posicion).getDueño() == null){
            this.linea.get(posicion).setDueño(j);
            j.setDinero(j.getDinero()-this.linea.get(posicion).getPrecio());
        }
    }

    /**
     * Método pagarCasillaJugador1 que maneja las acciones cuando el jugador 1 cae en una casilla en la que el dueño es el jugador 2
     * @param posicion
     */
    public void pagarCasillaJugador1(int posicion){
        if(llervarseDinero == 0) {
            if (this.linea.get(posicion).getDueño() == jugador2) {
                jugador1.setDinero(jugador1.getDinero() - this.linea.get(posicion).getCobrar());
                jugador2.setDinero(jugador2.getDinero() + this.linea.get(posicion).getCobrar());
            }
            llervarseDinero ++;
        }
    }

    /**
     * Método pagarCasillaJugador1 que maneja las acciones cuando el jugador 1 cae en una casilla en la que el dueño es el jugador 2
     * @param posicion
     */
    public void pagarCasillaJugador2(int posicion){
        if(llervarseDinero == 0) {
            if (this.linea.get(posicion).getDueño() == jugador1) {
                jugador2.setDinero(jugador2.getDinero() - this.linea.get(posicion).getCobrar());
                jugador1.setDinero(jugador1.getDinero() + this.linea.get(posicion).getCobrar());
            }
            llervarseDinero ++;
        }
    }
}