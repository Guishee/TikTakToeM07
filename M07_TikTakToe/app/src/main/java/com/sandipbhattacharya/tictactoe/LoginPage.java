package com.sandipbhattacharya.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class LoginPage extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        ImageButton botonGoogle=findViewById(R.id.botonLogin);
        ImageButton botonFace=findViewById(R.id.botonLoginFace);

        botonGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginPage.this, MainMenuPage.class);
                startActivity(intent);
            }
        });
        botonFace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginPage.this, MainMenuPage.class);
                startActivity(intent);
            }
        });

    }

}