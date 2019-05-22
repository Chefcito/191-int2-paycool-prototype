package co.edu.icesi.joancaliz.paycool_prototype.activities;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.view.Window;
import android.view.WindowManager;

import co.edu.icesi.joancaliz.paycool_prototype.R;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //Hacer Full Screen la pantalla
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Esta linea permite las transiciones animadas con esta actividad
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        //Transición de salida
        getWindow().setExitTransition(new Explode());

        setContentView(R.layout.activity_splash_screen);

        //Crear el delay del splash screen a través de Handler
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, Login.class);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(SplashScreen.this).toBundle());
            }
        },3000);
    }
}
