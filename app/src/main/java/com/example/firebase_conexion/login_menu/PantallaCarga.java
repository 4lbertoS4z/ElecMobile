package com.example.firebase_conexion.login_menu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.firebase_conexion.R;

public class PantallaCarga extends AppCompatActivity {
    private ProgressBar progressBar;
    private TextView textView;
    int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_carga);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        adjustFontScale(getResources().getConfiguration());

        progressBar=findViewById(R.id.progressBar);
        textView=findViewById(R.id.textView);



        final Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(i<=100){
                    textView.setText(""+i);
                    progressBar.setProgress(i);
                    i++;
                    handler.postDelayed(this,5);
                   /* Intent intent=new Intent(PantallaCarga.this,MainActivity.class);
                    startActivity(intent);
                    PantallaCarga.this.finish();*/
                }else{
                    handler.removeCallbacks(this);
                    Intent intent=new Intent(PantallaCarga.this,MainActivity.class);
                    startActivity(intent);
                    PantallaCarga.this.finish();
                }


            }
        },5);
    }

    private void adjustFontScale(Configuration configuration) {
        configuration.fontScale = (float) 1.0;
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(metrics);
        metrics.scaledDensity = configuration.fontScale * metrics.density;
        getBaseContext().getResources().updateConfiguration(configuration, metrics);
    }
}