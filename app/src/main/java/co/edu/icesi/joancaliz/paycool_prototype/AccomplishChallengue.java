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
        setContentView(R.layout.accomplish_challengue);

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
