package com.example.firebase_conexion.login_menu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.firebase_conexion.admin.AddActivity;
import com.example.firebase_conexion.juego.Juego;
import com.example.firebase_conexion.R;
import com.example.firebase_conexion.cuestionario.Cuestionario;
import com.example.firebase_conexion.fragments.MainActivityCloudStore;
import com.example.firebase_conexion.noticias.MainActivity3;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity2 extends AppCompatActivity {


private ImageView buttonJuego,buttonRss,buttonTop, buttonViewModel;
    private long backPressedTime;
    FloatingActionButton floatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        buttonRss=(findViewById(R.id.buttonRss));
        buttonTop=(findViewById(R.id.buttonTop));
        buttonViewModel=(findViewById(R.id.buttonCuestionario));
        buttonJuego=(findViewById(R.id.buttonJuego));
        floatingActionButton=(FloatingActionButton)findViewById(R.id.floatingActionButtonHome);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
        buttonTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), MainActivityCloudStore.class);
                startActivity(intent);
            }
        });
        buttonRss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), MainActivity3.class);
                startActivity(intent);
            }
        });
        buttonViewModel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), Cuestionario.class);
                startActivity(intent);
            }
        });

        buttonJuego.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), Juego.class);
                startActivity(intent);
            }
        });




    }
    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();

            return;
        } else {
            Toast.makeText(this, "Pulsa 2 veces atras para salir.", Toast.LENGTH_SHORT).show();

        }
        backPressedTime=System.currentTimeMillis();
    }
}