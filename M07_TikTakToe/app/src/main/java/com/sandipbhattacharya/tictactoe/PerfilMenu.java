package com.sandipbhattacharya.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class PerfilMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_menu);

        ImageButton atrasMain=findViewById(R.id.atrasPerfil);
        atrasMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(PerfilMenu.this,MainMenuPage.class);
                startActivity(intent);
            }
        });
    }
}