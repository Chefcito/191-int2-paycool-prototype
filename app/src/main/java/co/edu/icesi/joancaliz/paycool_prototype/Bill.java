package co.edu.icesi.joancaliz.paycool_prototype;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

//Esta clase es usada para almacenar la información de una factura.
public class Bill extends AppCompatActivity {
    private TextView product1PriceTextView, product2PriceTextView, product3PriceTextView, subtotalTextView, discountTextView, totalTextView;
    private Button confirmPaymentButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);

        product1PriceTextView = findViewById(R.id.bill_product_1_price_text_view);
        product1PriceTextView.setText(getIntent().getExtras().getString("price1") );
        Toast.makeText(this, getIntent().getExtras().getString("price1"), Toast.LENGTH_LONG).show();
        product2PriceTextView = findViewById(R.id.bill_product_2_price_text_view);
        product2PriceTextView.setText(getIntent().getExtras().getString("price2") );
        product3PriceTextView = findViewById(R.id.bill_product_3_price_text_view);
        product3PriceTextView.setText(getIntent().getExtras().getString("price3") );
        subtotalTextView = findViewById(R.id.bill_subtotal_text_view);
        subtotalTextView.setText(getIntent().getExtras().getString("subtotal") );
        discountTextView = findViewById(R.id.bill_discount_text_view);
        discountTextView.setText(getIntent().getExtras().getString("discount") );;
        totalTextView = findViewById(R.id.bill_total_text_view);
        totalTextView.setText(getIntent().getExtras().getString("total") );
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
