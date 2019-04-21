package co.edu.icesi.joancaliz.paycool_prototype;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

//El home mijos. La clase de la actividad principal.
public class Home extends AppCompatActivity {

    //Lista de retos del usuario.
    private ListView challenguesListView;
    //Adaptador para la lista de retos.
    private ChallengueAdapter challengueAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        challenguesListView = findViewById(R.id.home_challengues_list_view);

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
    }
}

