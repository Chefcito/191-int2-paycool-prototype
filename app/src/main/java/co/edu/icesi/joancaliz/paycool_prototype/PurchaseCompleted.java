package co.edu.icesi.joancaliz.paycool_prototype;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

//Esta clase se usará para dar feedback al usuario al hacer una compra.
public class PurchaseCompleted extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_completed);
    }
}
