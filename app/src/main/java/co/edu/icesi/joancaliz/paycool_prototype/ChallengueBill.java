package co.edu.icesi.joancaliz.paycool_prototype;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

//Esta clase es usada para almacenar la información de una factura.
public class ChallengueBill extends AppCompatActivity {

    Button confirmPaymentButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challengue_bill);

        confirmPaymentButton = findViewById(R.id.challengue_bill_confirm_payment_button);
        confirmPaymentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToPurchaseCompleted();
            }
        });
    }

    public void goToPurchaseCompleted(){
        Intent intent = new Intent (this, PurchaseCompleted.class);
        startActivity(intent);
    }
}
