package co.edu.icesi.joancaliz.paycool_prototype;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class AccomplishChallengue extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accomplish_challengue);

        //Temporizador que ayuda a simular que el usuario completó el reto.
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                goToChallengueBill();
            }
        }, 5000);
    }

    public void goToChallengueBill() {
        Intent intent = new Intent(this, ChallengueBill.class);
        startActivity(intent);
    }
}
