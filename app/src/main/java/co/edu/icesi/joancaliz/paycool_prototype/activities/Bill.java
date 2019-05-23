package co.edu.icesi.joancaliz.paycool_prototype.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;

import co.edu.icesi.joancaliz.paycool_prototype.Purchase;
import co.edu.icesi.joancaliz.paycool_prototype.R;

//Esta clase es usada para almacenar la información de una factura.
public class Bill extends AppCompatActivity {
    private FirebaseAuth auth;
    private DatabaseReference dbReference;
    private DatabaseReference dbCurrentUserReference;

    private TextView product1PriceTextView, product2PriceTextView, product3PriceTextView, subtotalTextView, discountTextView, totalTextView;
    private Button confirmPaymentButton;

    private Purchase myPurchase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);

        auth = FirebaseAuth.getInstance();
        dbReference = FirebaseDatabase.getInstance().getReference();
        dbCurrentUserReference = dbReference.child("Users").child(auth.getCurrentUser().getUid() );

        int price1 = getIntent().getExtras().getInt("price1");
        int price2 = getIntent().getExtras().getInt("price2");
        int price3 = getIntent().getExtras().getInt("price3");
        int discount = getIntent().getExtras().getInt("discount");
        String code = getIntent().getExtras().getString("code");
        myPurchase = new Purchase(price1, price2, price3, discount, code);

        product1PriceTextView = findViewById(R.id.bill_product_1_price_text_view);
        product1PriceTextView.setText(Integer.toString(myPurchase.getPrice1() ));
        product2PriceTextView = findViewById(R.id.bill_product_2_price_text_view);
        product2PriceTextView.setText(Integer.toString(myPurchase.getPrice2() ));
        product3PriceTextView = findViewById(R.id.bill_product_3_price_text_view);
        product3PriceTextView.setText(Integer.toString(myPurchase.getPrice3() ));
        subtotalTextView = findViewById(R.id.bill_subtotal_text_view);
        subtotalTextView.setText(Integer.toString(myPurchase.getSubtotal() ));
        discountTextView = findViewById(R.id.bill_discount_text_view);
        discountTextView.setText(Integer.toString(myPurchase.getDiscount() ));;
        totalTextView = findViewById(R.id.bill_total_text_view);
        totalTextView.setText(Integer.toString(myPurchase.getTotal() ));
        confirmPaymentButton = findViewById(R.id.challengue_bill_confirm_payment_button);

        confirmPaymentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                payBill();
            }
        });
    }

    public void payBill() {
        dbCurrentUserReference.child("money").runTransaction(new Transaction.Handler() {
            @NonNull
            @Override
            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                final Integer currentValue = mutableData.getValue(Integer.class);
                if(currentValue != null) {
                    if(currentValue >= myPurchase.getTotal() ) {
                        int remainingMoney = currentValue - myPurchase.getTotal();
                        mutableData.setValue(remainingMoney);
                        goToPurchaseCompleted();
                    }

                    else {
                        Bill.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(Bill.this, "No tienes saldo suficiente. :(", Toast.LENGTH_LONG).show();
                                Toast.makeText(Bill.this, "Tu saldo: " + currentValue + " - Valor de la compra: " + myPurchase.getTotal(), Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                }

                else {
                    Bill.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(Bill.this, "Whoops! Algo salió mal", Toast.LENGTH_LONG).show();
                        }
                    });
                }

                return Transaction.success(mutableData);
            }

            @Override
            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {
                Log.i("Firebase Transaction: ", "Transaction completed");
            }
        });
    }

    public void goToPurchaseCompleted(){
        Intent intent = new Intent (this, PurchaseCompleted.class);
        startActivity(intent);
    }
}
