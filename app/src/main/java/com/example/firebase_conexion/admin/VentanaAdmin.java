package com.example.firebase_conexion.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.firebase_conexion.R;
import com.example.firebase_conexion.login_menu.MainActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class VentanaAdmin extends AppCompatActivity {

    private Button buttonEco,buttonGame,buttonTop, buttonCamara;
    FloatingActionButton floatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_admin);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        buttonEco=(findViewById(R.id.button1));
        buttonTop=(findViewById(R.id.button3));
        buttonGame=(findViewById(R.id.button2));
        buttonCamara=(findViewById(R.id.button4));
        floatingActionButton=(FloatingActionButton)findViewById(R.id.floatingActionButtonHome);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
        buttonEco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), Cuestionario_1_Admin.class);
                startActivity(intent);
            }
        });
        buttonTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), Cuestionario_2_Admin.class);
                startActivity(intent);
            }
        });
        buttonGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), Cuestionario_3_Admin.class);
                startActivity(intent);
            }
        });

        buttonCamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), Cuestionario_4_Admin.class);
                startActivity(intent);
            }
        });
    }
}