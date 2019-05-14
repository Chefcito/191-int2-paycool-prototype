package co.edu.icesi.joancaliz.paycool_prototype.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import co.edu.icesi.joancaliz.paycool_prototype.R;

//Esta clase se usará para dar feedback al usuario al hacer una compra.
public class PurchaseCompleted extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_completed);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(PurchaseCompleted.this, Home.class);
                startActivity(intent);
            }
        }, 3000);
    }
}
