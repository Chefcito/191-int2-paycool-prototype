package co.edu.icesi.joancaliz.paycool_prototype.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import co.edu.icesi.joancaliz.paycool_prototype.Challengue;
import co.edu.icesi.joancaliz.paycool_prototype.R;

public class ChallengueAdapterRecyclerView extends RecyclerView.Adapter<ChallengueAdapterRecyclerView.ChallengueViewHolder> {

    //Aqui va nuestra lista de elementos
    private List<Challengue> challengues;

    public ChallengueAdapterRecyclerView(List<Challengue> challengues){
        this.challengues=challengues;

    }


    //En este método se define el layout a través de un view, el cual se le pasa al holder
    public ChallengueViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        // creo la vista que luego se pasara al holder, le pongo contexto y llamo el layput que creé para cada uno de los retos
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.challengue, viewGroup, false);

        ChallengueViewHolder holder = new ChallengueViewHolder(v);
        //retorno el sostenedor
        return holder;
    }

    // aqui cambiamos los parametros de nuestros de nuestro holder, por ejemplo el titulo, la descripción
    @Override
    public void onBindViewHolder(@NonNull ChallengueViewHolder challengueViewHolder, int i) {
        Challengue challengue = challengues.get(i);
        challengueViewHolder.textViewTittle.setText(challengue.getTitle());
        challengueViewHolder.textViewDescription.setText(challengue.getDescription());
        challengueViewHolder.textViewPoints.setText(Integer.toString(challengue.getPoints()));


    }

    //nos devuelve el tamaño de la lista
    @Override
    public int getItemCount() {
        return challengues.size();
    }


    //Se crea clase holder que contiene los elementos linkeados del layout


    public static class ChallengueViewHolder extends RecyclerView.ViewHolder {


        //Elementos que van dentro de Recycler view declarados
        TextView textViewTittle, textViewDescription, textViewPoints;


        public ChallengueViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTittle = itemView.findViewById(R.id.challengue_title_text_view);
            textViewDescription = itemView.findViewById(R.id.challengue_description_text_view);
            textViewPoints = itemView.findViewById(R.id.challengue_points_text_view);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String a = textViewTittle.getText().toString();
                    Log.i("hola",a);

                }
            });

        }
    }


}
