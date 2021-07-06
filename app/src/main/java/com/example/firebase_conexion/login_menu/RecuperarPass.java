package com.example.firebase_conexion.login_menu;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.firebase_conexion.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class RecuperarPass extends AppCompatActivity {
    Button btnrecuperarpass, btnvolver;
    EditText emailText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_pass);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        btnvolver = findViewById(R.id.volverLogin);
        btnrecuperarpass = findViewById(R.id.recuperarpass);
        emailText = findViewById(R.id.emailtxt);
        btnrecuperarpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });
        btnvolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecuperarPass.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void validate() {
        String email = emailText.getText().toString().trim();
        if (email.isEmpty()||!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailText.setError("CORREO INVALIDO");
            return;
        }

        sendEmail(email);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(RecuperarPass.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
    public void sendEmail(String email) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        String emailAdress = email;
        auth.sendPasswordResetEmail(emailAdress).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(RecuperarPass.this, " Correo Enviado", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RecuperarPass.this, MainActivity.class);
                }else{
                    Toast.makeText(RecuperarPass.this, "El correo fue invalido | no existe correo", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
