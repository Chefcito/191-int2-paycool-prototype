package co.edu.icesi.joancaliz.paycool_prototype.adapters;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import co.edu.icesi.joancaliz.paycool_prototype.Challengue;
import co.edu.icesi.joancaliz.paycool_prototype.R;
import co.edu.icesi.joancaliz.paycool_prototype.activities.AccomplishChallengue;
import co.edu.icesi.joancaliz.paycool_prototype.activities.SplashScreen;
import co.edu.icesi.joancaliz.paycool_prototype.fragments.WalletFragment;

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
        challengueViewHolder.textViewType.setText(challengue.getType());

        if(challengue.getComplete()){
            Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/paycool-prototype.appspot.com/o/ChallengeState%2Fdone.png?alt=media&token=2b0b736b-f5c2-4284-acf8-6cf80dc55f1f").into(challengueViewHolder.ImageViewChallenge);
        } else{

            Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/paycool-prototype.appspot.com/o/ChallengeState%2Fundone.png?alt=media&token=2be48ec4-ec2e-4c94-985c-c335d3ba5117").into(challengueViewHolder.ImageViewChallenge);
        }


    }

    //nos devuelve el tamaño de la lista
    @Override
    public int getItemCount() {
        return challengues.size();
    }






    //Se crea clase holder que contiene los elementos linkeados del layout


    public static class ChallengueViewHolder extends RecyclerView.ViewHolder {


        //Elementos que van dentro de Recycler view declarados
        TextView textViewTittle, textViewDescription, textViewPoints, textViewType;
        ImageView ImageViewChallenge;




        public ChallengueViewHolder(@NonNull final View itemView) {
            super(itemView);

            textViewTittle = itemView.findViewById(R.id.challengue_title_text_view);
            textViewDescription = itemView.findViewById(R.id.challengue_description_text_view);
            textViewPoints = itemView.findViewById(R.id.challengue_points_text_view);
            textViewType = itemView.findViewById(R.id.challengue_type_text_view);
            ImageViewChallenge = itemView.findViewById(R.id.challengue_img_img);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String a = textViewTittle.getText().toString();

                    if(textViewType.getText().toString().equals("wallet")){

                        Toast toast = Toast.makeText(itemView.getContext(), "Go To Retos",Toast.LENGTH_SHORT );
                        toast.show();
                        Intent cambio = new Intent(itemView.getContext(), SplashScreen.class);

                        AppCompatActivity activity = (AppCompatActivity) itemView.getContext();
                        WalletFragment walletFragment =  new WalletFragment();
                        activity.getSupportFragmentManager().beginTransaction().replace(R.id.home_fragment_container_frame_layout, walletFragment).commit();



                    }

                    if(textViewType.getText().toString().equals("buy")){

                        Toast toast = Toast.makeText(itemView.getContext(), "bebecita",Toast.LENGTH_SHORT );
                        toast.show();
                        Intent cambio = new Intent(itemView.getContext(), AccomplishChallengue.class);

                        //AppCompatActivity activity = (AppCompatActivity) itemView.getContext();
                        //WalletFragment walletFragment =  new WalletFragment();
                        //activity.getSupportFragmentManager().beginTransaction().replace(R.id.home_fragment_container_frame_layout, walletFragment).commit();
                        itemView.getContext().startActivity(cambio);


                    }





                }
            });


        }
    }


}
