package com.example.firebase_conexion.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.firebase_conexion.login_menu.MainActivity;
import com.example.firebase_conexion.R;

public class LoginAdmin extends AppCompatActivity {
    private Button logear;
    private Button volver;
    private EditText email, contrasenia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_admin);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        volver=(findViewById(R.id.button2));
        logear =(findViewById(R.id.button));
        email=(findViewById(R.id.txtEmail));
        contrasenia=(findViewById(R.id.txtPassword));
        logear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(email.getText().toString().equals("admin")&&contrasenia.getText().toString().equals("admin")){
                    Toast.makeText(LoginAdmin.this, "Conectado con exito", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginAdmin.this,VentanaAdmin.class));
                    limpiarCampos();
                }else{
                    Toast.makeText(LoginAdmin.this, "No eres el admin. ¡largo de aquí!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginAdmin.this, MainActivity.class));
                }

            }
        });
        volver.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Toast.makeText(LoginAdmin.this, "Volviendo...", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginAdmin.this,MainActivity.class));

        }
    });
}
    private void limpiarCampos() {
        email.setText("");
        contrasenia.setText("");

    }
}