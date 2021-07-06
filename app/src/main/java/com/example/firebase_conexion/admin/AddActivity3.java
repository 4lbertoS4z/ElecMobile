package com.example.firebase_conexion.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.firebase_conexion.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AddActivity3 extends AppCompatActivity {
    private EditText modelo,foto,compra,descripcion;
    private Button btnAdd,btnVolver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        modelo=findViewById(R.id.txtModelo);
        foto=findViewById(R.id.txtFoto);
        compra=findViewById(R.id.txtCompra);
        descripcion=findViewById(R.id.txtDescripcion);

        btnAdd=findViewById(R.id.btnAdd);
        btnVolver=findViewById(R.id.btnVolver);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
                limpiarCampos();
            }
        });

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void insertData(){
        Map<String,Object> map=new HashMap<>();
        map.put("modelo",modelo.getText().toString());
        map.put("foto",foto.getText().toString());
        map.put("compra",compra.getText().toString());
        map.put("descripcion",descripcion.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("movil_gamer").push().setValue(map).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(AddActivity3.this, "Datos Creados correctamente", Toast.LENGTH_SHORT).show();
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        Toast.makeText(AddActivity3.this, "Ocurrio un error al insertar los datos", Toast.LENGTH_SHORT).show();
                    }
                });

    }
    private void limpiarCampos(){
        modelo.setText("");
        foto.setText("");
        compra.setText("");
        descripcion.setText("");
    }
}
