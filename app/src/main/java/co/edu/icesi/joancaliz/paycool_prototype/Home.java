package co.edu.icesi.joancaliz.paycool_prototype;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

//El home mijos. La clase de la actividad principal.
public class Home extends AppCompatActivity {

    //Lista de retos del usuario.
    private ListView challenguesListView;
    //Adaptador para la lista de retos.
    private ChallengueAdapter challengueAdapter;
    //Textviews de información
    private TextView money;
    //Comunicación con Firebase
    private DatabaseReference dbReference;
     private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //La siguiente linea de código oculta el tittle bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_home);

        challenguesListView = findViewById(R.id.home_challengues_list_view);
        money = findViewById(R.id.money_edit_text);

        /* BaseAdapter requiere que le pasemos por parámetro una instancia de Activity, la cual tiene
        * que ser la actividad en donde se desplegará la lista. Es decir, esta misma actividad (this). */
        challengueAdapter = new ChallengueAdapter(this);

        // Una vez inicializado el adaptador, se le debe añadir al ListView.
        challenguesListView.setAdapter(challengueAdapter);

        // Esta es la manera en cómo hacer que se pueda ingresar a cada item de la lista.
        challenguesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                challengueAdapter.goToAccomplishChallengueActivity(i);
            }
        });

        // Añadí tres retos de prueba para ver si funcionaba correctamente.
        challengueAdapter.addChallengue(new Challengue() );
        challengueAdapter.addChallengue(new Challengue() );
        challengueAdapter.addChallengue(new Challengue() );


        auth = FirebaseAuth.getInstance();
        dbReference= FirebaseDatabase.getInstance().getReference();


        dbReference.child("Users").child(auth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
             //User myInformation  =  dataSnapshot.getValue(User.class);
            // money.setText(Integer.toString(myInformation.getDinero()));



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });







    }
}

