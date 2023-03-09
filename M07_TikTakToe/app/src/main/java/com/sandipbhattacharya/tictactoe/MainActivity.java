package com.sandipbhattacharya.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int jugadorActual = 0;
    boolean partida = true;
    int[] estadoPartida = {-1,-1,-1,-1,-1,-1,-1,-1,-1};
    int[][] puntoDePartida = {
            {0,1,2}, {3,4,5}, {6,7,8},
            {0,3,6}, {1,4,7}, {2,5,8},
            {0,4,8}, {2,4,6}
    };
    String ganadorPartida = "X";
    private TextView tv;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Boton de atras
        ImageButton atrasMain=findViewById(R.id.logoinBack);
        atrasMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,MainMenuPage.class);
                startActivity(intent);
            }

        });


        //Temporizador
        TextView temporizador=findViewById(R.id.timerinGame);
        long tiempoRestante=30000;

        new CountDownTimer(tiempoRestante,1000){

            @Override
            public void onTick(long l) {
                int segundos=(int)(l/1000);
                temporizador.setText(String.format("00:%02d", segundos));
            }

            @Override
            public void onFinish() {
                empate();
            }
        }.start();
    }


    //ejecucion de la partida
    public void play(View view) {


        ImageView ivClicked = (ImageView) view;
        ivClicked.setAlpha(1.0f);
        ivClicked.setVisibility(View.VISIBLE);


        //Turnos
        int clickedImageView = Integer.parseInt(ivClicked.getTag().toString());
        if(estadoPartida[clickedImageView] == -1 && partida){
            estadoPartida[clickedImageView] = jugadorActual;

            //detecta de que jugador se trata para poner la ficha
            if(jugadorActual == 0){
                ivClicked.setImageResource(R.drawable.open);
                jugadorActual = 1;
            } else {
                ivClicked.setImageResource(R.drawable.close);
                jugadorActual = 0;
            }


            ivClicked.setVisibility(View.VISIBLE);

            //Aqui se comprueba si en este turno hay algun ganador o empate
            for (int[] winningPosition: puntoDePartida) {
                if(estadoPartida[winningPosition[0]] == estadoPartida[winningPosition[1]] &&
                estadoPartida[winningPosition[1]] == estadoPartida[winningPosition[2]] &&
                estadoPartida[winningPosition[0]] != -1){
                    partida = false;

                    //comprueba el ganador de la partida
                    if(estadoPartida[winningPosition[0]] == 0){
                        ganadorPartida = "0";
                    }

                    showConfirmationDialog();
                }else{
                    boolean gameOver = true;
                    for (int currentPlayer: estadoPartida
                         ) {
                        if(currentPlayer == -1){
                            gameOver = false;
                        }
                    }
                    if(gameOver){


                        showConfirmationDialog();

                    }
                }
            }
        }
    }




    //ventana de final
    public void showConfirmationDialog() {

        Dialog confirmationDialog = new Dialog(this);
        confirmationDialog.setContentView(R.layout.ventana_emerg);

        Button yesButton = confirmationDialog.findViewById(R.id.yes_button);
        Button noButton = confirmationDialog.findViewById(R.id.no_button);

        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);

                confirmationDialog.dismiss();
                startActivity(intent);
            }
        });

        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,MainMenuPage.class);
                confirmationDialog.dismiss();
                startActivity(intent);
            }
        });

        confirmationDialog.show();
    }

    //ventana empate por tiempo
    public void empate(){
        Dialog confirmationDialog=new Dialog(this);
        confirmationDialog.setContentView(R.layout.empate);

        Button yesButton = confirmationDialog.findViewById(R.id.yes_button);
        Button noButton = confirmationDialog.findViewById(R.id.no_button);

        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);

                confirmationDialog.dismiss();
                startActivity(intent);
            }
        });

        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,MainMenuPage.class);
                confirmationDialog.dismiss();
                startActivity(intent);
            }
        });

        confirmationDialog.show();


    }

}

