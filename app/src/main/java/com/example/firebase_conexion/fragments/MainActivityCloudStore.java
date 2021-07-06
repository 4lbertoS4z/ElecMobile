package com.example.firebase_conexion.fragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.firebase_conexion.R;

public class MainActivityCloudStore extends AppCompatActivity {

   private Button btnfr1, btnfr2,btnfr3,btnfr4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_cloud_store);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        btnfr1 =findViewById(R.id.btnFrag1);
        btnfr2 =findViewById(R.id.btnFrag2);
        btnfr3= findViewById(R.id.btnFrag3);
        btnfr4= findViewById(R.id.btnFrag4);

        //carga fragment dinamico
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.contenedor, new fr1())
                .disallowAddToBackStack()
                .commit();

    //boton A
        btnfr1.setOnClickListener(
                new View.OnClickListener() {
        public void onClick(View view) {
            Fragment fragmentoA = new fr1();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.contenedor, fragmentoA)
                    .disallowAddToBackStack()
                    .commit();
        }
    });
    //boton B
        btnfr2.setOnClickListener(
                new View.OnClickListener() {
        public void onClick(View view) {
            Fragment fragmentoB = new fr2();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.contenedor, fragmentoB)
                    .disallowAddToBackStack()
                    .commit();
        }
    });
    //boton C
        btnfr3.setOnClickListener(
                new View.OnClickListener() {
        public void onClick(View view) {
            Fragment fragmentoC = new fr3();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.contenedor, fragmentoC)
                    .disallowAddToBackStack()
                    .commit();
        }
    });
        //boton C
        btnfr4.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        Fragment fragmentoD = new fr4();
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.contenedor, fragmentoD)
                                .disallowAddToBackStack()
                                .commit();
                    }
                });
}
}
