package co.edu.icesi.joancaliz.paycool_prototype.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import co.edu.icesi.joancaliz.paycool_prototype.BigChallengue;
import co.edu.icesi.joancaliz.paycool_prototype.Challengue;
import co.edu.icesi.joancaliz.paycool_prototype.R;
import co.edu.icesi.joancaliz.paycool_prototype.User;
import co.edu.icesi.joancaliz.paycool_prototype.adapters.ChallengueAdapterRecyclerView;

public class HomeFragment extends Fragment {

    private View view;
    private List<Challengue> challengues;
    private List<Challengue> challengueUsuario = new ArrayList<>();
    private List<Challengue> challenguesAsignados;
    private BigChallengue big;

    private TextView money;
    private int retosCumplidos = 0;
    private User user;


    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private DatabaseReference dbReference = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference dbUsersReference = dbReference.child("Users");
    private DatabaseReference dChallenges;
    private DatabaseReference dBigChallenges;
    private boolean asignarRetoB = true;
    private boolean asignarRetoC = true;
    private int a;
    private int b;
    private int c;
    private int progress;
    private TextView bigChallengeDescription;
    private TextView bigChallengeSize;
    private ImageView bigChallengeImage;
    private TextView point;
    private ProgressBar bigChalleProgressBar;
    private SharedPreferences myPreferences;


    //Recycler view elements
    private RecyclerView rv;
    private ChallengueAdapterRecyclerView adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home, container, false);

        //Algo parecido al local Storage
        myPreferences = PreferenceManager.getDefaultSharedPreferences(view.getContext());
        a = myPreferences.getInt("a", 0);
        b = myPreferences.getInt("b", 0);
        c = myPreferences.getInt("c", 0);
        retosCumplidos = myPreferences.getInt("retos", 0);
        progress = myPreferences.getInt("progress", 0);


        //elements from layout find by id
        money = view.findViewById(R.id.fragment_home_money_text_view);
        bigChallengeDescription = view.findViewById(R.id.fragment_home_challengues_textview_bigchallenge);
        bigChallengeSize = view.findViewById(R.id.fragment_home_challengues_textview_bigchallenge_number);
        bigChallengeImage = view.findViewById(R.id.fragment_home_challengues_img_bigchallenge);
        bigChalleProgressBar = view.findViewById(R.id.fragment_home_challengues_progressbar_bigchallenge);
        point = view.findViewById(R.id.fragment_home_points_text_view);
        FragmentTransaction CambioFragment = getActivity().getSupportFragmentManager().beginTransaction();


        //Inicialización de Recycler view
        rv = (RecyclerView) view.findViewById(R.id.fragment_home_challengues_recycler_view);


        //Firebase


        //Recycler View
        dChallenges = dbReference.child("Challenges").child("LittleChallenge");
        dBigChallenges = dbReference.child("Challenges").child("BigChallenge");
        challengues = new ArrayList<>();
        challenguesAsignados = new ArrayList<>();
        //big = new BigChallengue("MC DONALDS","Cumple 2 tareas para llevarte una Black Angus completamente gratis.",5, "award");
        //dBigChallenges.push().setValue(big);


        //challengues.add(new Challengue("BILLETERA", "Envía un monto mayor de $2000 pesos a un amigo.", 15, "buy", 0));
        //challengues.add(new Challengue("TOSTAO", "Paga cualquier producto en Tostao con la app.", 5, "wallet", 1));
        //challengues.add(new Challengue("BILLETERA", "Envía un monto mayor de $2000 pesos a un amigo.", 15, "buy", 2));
        //challengues.add(new Challengue("TOSTAO", "Paga cualquier producto en Tostao con la app.", 5, "wallet", 3));
        //challengues.add(new Challengue("BILLETERA", "Envía un monto mayor de $2000 pesos a un amigo.", 15, "buy", 4));
        //challengues.add(new Challengue("TOSTAO", "Paga cualquier producto en Tostao con la app.", 5, "wallet", 5));

        //for (int i = 0; i < challengues.size(); i++) {
        //  dChallenges.push().setValue(challengues.get(i));
        //}


        dbUsersReference.child(auth.getCurrentUser().getUid()).child("challengues").addValueEventListener(new ValueEventListener() {


            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                challengueUsuario.removeAll(challengueUsuario);

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Challengue challengueTemp = snapshot.getValue(Challengue.class);
                    challengueUsuario.add(challengueTemp);
                }

                a = challengueUsuario.get(0).getIndex();
                b = challengueUsuario.get(1).getIndex();
                c = challengueUsuario.get(2).getIndex();

                ///progres bar y retos en tiempo reaaaaaal
                progress=0;
                retosCumplidos=0;

                if (challengueUsuario.get(0).getComplete() == true) {
                    challenguesAsignados.get(0).setComplete(true);
                    progress += 33;
                    retosCumplidos++;

                }

                if (challengueUsuario.get(0).getComplete() == false) {
                    challenguesAsignados.get(0).setComplete(false);

                }


                if (challengueUsuario.get(1).getComplete() == true) {

                    challenguesAsignados.get(1).setComplete(true);
                    progress += 33;
                    retosCumplidos++;

                }

                if (challengueUsuario.get(1).getComplete() == false) {
                    challenguesAsignados.get(1).setComplete(false);

                }

                if (challengueUsuario.get(2).getComplete() == true) {

                    challenguesAsignados.get(2).setComplete(true);
                    progress += 33;
                    retosCumplidos++;

                }

                if (challengueUsuario.get(2).getComplete() == false) {

                    challenguesAsignados.get(2).setComplete(false);

                }


                if (challengueUsuario.get(2).getComplete() == false && challengueUsuario.get(1).getComplete() == false && challengueUsuario.get(0).getComplete() == false) {
                    progress = 0;
                }

                bigChallengeSize.setText(retosCumplidos + "/" + challenguesAsignados.size());
                adapter.notifyDataSetChanged();
                bigChalleProgressBar.setProgress(progress);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        adapter = new ChallengueAdapterRecyclerView(challenguesAsignados);
        rv.setLayoutManager(new LinearLayoutManager(view.getContext()));
        rv.setAdapter(adapter);

        dbUsersReference.child(auth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                user = dataSnapshot.getValue(User.class);
                point.setText("Tus puntos: " + user.getPaycoolPoints());
                money.setText("$" + Integer.toString(user.getMoney()));

                if (user.isRetos() == false) {
                    user.setA((int) Math.floor((Math.random() * challengues.size())));

                    asignarRetoB = true;

                    while (asignarRetoB) {

                        user.setB((int) Math.floor((Math.random() * challengues.size())));
                        if (user.getA() != user.getB()) {
                            asignarRetoB = false;
                        }

                    }

                    asignarRetoC = true;

                    while (asignarRetoC) {
                        user.setC((int) Math.floor((Math.random() * challengues.size())));
                        if (user.getA() != user.getB() && user.getA() != user.getC() && user.getC() != user.getB()) {
                            asignarRetoC = false;

                        }

                    }


                    dbUsersReference.child(auth.getCurrentUser().getUid()).child("challengues").child("0").child("index").setValue(user.getA());
                    dbUsersReference.child(auth.getCurrentUser().getUid()).child("challengues").child("1").child("index").setValue(user.getB());
                    dbUsersReference.child(auth.getCurrentUser().getUid()).child("challengues").child("2").child("index").setValue(user.getC());

                    adapter.notifyDataSetChanged();
                    dbUsersReference.child(auth.getCurrentUser().getUid()).child("retos").setValue(true);

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("Error: " + databaseError);
            }
        });


        dBigChallenges.child("-LfXCRV5UblCf9W6fsTl").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                big = dataSnapshot.getValue(BigChallengue.class);
                bigChallengeDescription.setText(big.getDescription());
                bigChallengeSize.setText(retosCumplidos + "/" + challenguesAsignados.size());
                Picasso.get().load(big.getImg()).into(bigChallengeImage);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


            }
        });

        refrescarListasFirebase();
        return view;
    }


    public void refrescarListasFirebase() {


        dChallenges.addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                challenguesAsignados.removeAll(challenguesAsignados);
                challengues.removeAll(challengues);


                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Challengue challengueTemp = snapshot.getValue(Challengue.class);
                    challengues.add(challengueTemp);


                }


                for (int i = 0; i < challengues.size(); i++) {

                    if (a == challengues.get(i).getIndex()) {
                        challenguesAsignados.add(challengues.get(i));
                        Log.d("p3n3", "onCreateView: ------------------------------------" + a);
                    }

                    if (b == challengues.get(i).getIndex()) {
                        challenguesAsignados.add(challengues.get(i));
                        Log.d("p3n3", "onCreateView: ------------------------------------" + b);


                    }

                    if (c == challengues.get(i).getIndex()) {
                        challenguesAsignados.add(challengues.get(i));
                        Log.d("p3n3", "onCreateView: ------------------------------------" + c);
                    }

                }


                adapter.notifyDataSetChanged();
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    @Override
    public void onStop() {
        super.onStop();

        SharedPreferences.Editor myEditor = myPreferences.edit();
        myEditor.putInt("a", a);
        myEditor.putInt("b", b);
        myEditor.putInt("c", c);
        myEditor.putInt("progress", progress);
        myEditor.putInt("retos", retosCumplidos);
        myEditor.commit();


    }
}
