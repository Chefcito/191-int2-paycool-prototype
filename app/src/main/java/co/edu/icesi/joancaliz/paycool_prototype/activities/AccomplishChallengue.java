package co.edu.icesi.joancaliz.paycool_prototype.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import co.edu.icesi.joancaliz.paycool_prototype.Purchase;
import co.edu.icesi.joancaliz.paycool_prototype.R;

public class AccomplishChallengue extends AppCompatActivity {
    private FirebaseDatabase database;
    private DatabaseReference dbReference;
    private DatabaseReference dbPurchasesReference;

    private EditText billCodeEditText;
    private Button acceptButton;

    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accomplish_challengue);

        /*
        //Temporizador que ayuda a simular que el usuario completó el reto.
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    goToChallengueBill();
                }
            }, 5000);
        */

        database = FirebaseDatabase.getInstance();
        dbReference = database.getReference();
        dbPurchasesReference = dbReference.child("Purchases");

        billCodeEditText = findViewById(R.id.accomplish_challengue_bill_code_edit_text);
        acceptButton= findViewById(R.id.accomplish_challengue_accept_button);

        activity = this;

        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code = billCodeEditText.getText().toString();
                if(code.isEmpty() ) {
                    return;
                }

                dbPurchasesReference.orderByChild("code").equalTo(code).limitToFirst(1).addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        Purchase purchase = dataSnapshot.getValue(Purchase.class);
                        if(dataSnapshot.hasChildren() ) {
                            goToChallengueBill(purchase);
                        }

                        else {
                            Toast.makeText(activity, "No existe la factura: " + purchase.getTotal(), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }

    public void goToChallengueBill(Purchase purchase) {
        Intent intent = new Intent(this, Bill.class);
        intent.putExtra("price1", purchase.getPrice1() );
        intent.putExtra("price2", purchase.getPrice2() );
        intent.putExtra("price3", purchase.getPrice3() );
        intent.putExtra("discount", purchase.getDiscount() );
        intent.putExtra("code", purchase.getCode() );
        startActivity(intent);
    }
}
