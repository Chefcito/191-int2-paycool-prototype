package co.edu.icesi.joancaliz.paycool_prototype;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

//Esta clase se usará para dar feedback al usuario al hacer una compra.
public class PurchaseCompletedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_completed);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(PurchaseCompletedActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        }, 3000);
    }
}
