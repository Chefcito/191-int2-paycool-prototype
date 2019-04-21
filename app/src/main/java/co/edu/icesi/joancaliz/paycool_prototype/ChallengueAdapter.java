package co.edu.icesi.joancaliz.paycool_prototype;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

//Este adaptador permite crear listas de retos (Challengue).

/*Al extender de BaseAdapter (un adaptador que nos ayudará a crear listas) se añadirán 4 metodos que
* nos permitirán administrar la lista. */
public class ChallengueAdapter extends BaseAdapter {

    /* Ya que esta clase no pertenece a una actividad, se necesita un objeto activity que
    * permita usar funciones de las actividades (como startActivity, para pasar de una pantalla a otra)
    * además de ser necesario para usar BaseAdapter. */
    private Activity activity;

    //Este ArrayList contendrá cada uno de los retos (Challengue) que se muestran en el listado.
    private ArrayList<Challengue> challengues;

    private TextView titleTextView, descriptionTextView, pointsTextView;

    public ChallengueAdapter(Activity activity) {
        this.activity = activity;
        challengues = new ArrayList<>();
    }

    //Estos son los métodos añadidos debido a que la clase extiende de BaseAdapter.

    /* Por defecto, cada uno de los métodos no retornará nada. Si van a usar BaseAdapter para crear
    * listas, deben sobreescribir lo que retorna cada método para que el adaptador funcione correctamente.
    * Yo lo suelo hacer de la siguiente forma, pero creo que no es obligatiorio hacerlo tal cual (con tal
    * de que lo sobreescriban y ningún método les retorne un null, no pasa nada, creo): */

    //getCount() debe retornar el tamaño del ArrayList usado para guardar cada elemento de la lista.
    @Override
    public int getCount() {
        return challengues.size();
    }

    //getItem() debe retornar un objeto del ArrayList en la posición i.
    @Override
    public Object getItem(int i) {
        return challengues.get(i);
    }

    //En getItemID() retorna la posición de un item dentro del ArrayList.
    @Override
    public long getItemId(int i) {
        return i;
    }

    //Este es el método más importante, hay cosas que no entiendo como funcionan exactamente.
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        // No se que hace exactamente LayoutInflater, pero se necesita para inicializar el View que retornará este método.
        /* ¿Recuerdan lo que dije en el comentario de arriba acerca de que se necesitaba una instancia (objeto) de Activity?
        * pues por ejemplo, aquí lo necesitaremos para usar el método getLayoutInflater() */
        LayoutInflater li = activity.getLayoutInflater();

        //Este objeto View es lo que debe retornar el método. Es la "vista" del item dentro de la lista.
        /* La instancia de View recibe tres parámetros:
        * 1. Un archivo XML, en el cual ustedes diagraman cómo se debería ver cada item de la lista
        * en este caso es challengue.xml).
        * 2. Una instancia de ViewGroup, que no sé para que sirve, pero siempre deben usar el que se declara en
        * los parámetros del método getView (llamado viewGroup).
        * 3. Un booleano, que no sé que hace. Ponganle false. */
        View v = li.inflate(R.layout.challengue, viewGroup, false);

        /* Aqui se inicializan las variables de los widgets del XML. Deben usar la variable v para poder usar el método
        *  findViewById() de la clase View. */
        titleTextView = v.findViewById(R.id.challengue_title_text_view);
        titleTextView.setText("TOSTAO'");

        descriptionTextView = v.findViewById(R.id.challengue_description_text_view);
        descriptionTextView.setText("Paga cualquier producto en Tostao' con Paycool");

        pointsTextView = v.findViewById(R.id.challengue_points_text_view);
        pointsTextView.setText("20 puntos");

        /* Llamar a este método es SUPER importante. Este se encarga de avisar a la lista cuando han habido
        * cambios en el adaptador. Deben llamar a este método cada vez que se modifiquen los datos de
        * este adaptador. */
        notifyDataSetChanged();

        //Se retorna la vista del item.
        return v;
    }

    //Este método permite añadir un nuevo item al ArrayList desde otras clases.
    public void addChallengue(Challengue challengue) {
        challengues.add(challengue);
        //Recuerden, como se está modificando los datos del adaptador, se llama al método notifyDataSetChanged().
        notifyDataSetChanged();
    }

    //Este es el método que se ejecuta cuando el usuario hace tab sobre uno de los items de la lista.
    public void goToAccomplishChallengueActivity(int index) {
        Intent intent = new Intent(activity, AccomplishChallengue.class);
        activity.startActivity(intent);
    }
}
