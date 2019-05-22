package co.edu.icesi.joancaliz.paycool_prototype.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import co.edu.icesi.joancaliz.paycool_prototype.Challengue;
import co.edu.icesi.joancaliz.paycool_prototype.R;
import co.edu.icesi.joancaliz.paycool_prototype.User;
import co.edu.icesi.joancaliz.paycool_prototype.adapters.ChallengueAdapterRecyclerView;

public class HomeFragment extends Fragment {

    private View view;
    private List<Challengue> challengues;

    private TextView money;


    private FirebaseAuth auth;
    private DatabaseReference dbReference;
    private DatabaseReference dbUsersReference;
    private DatabaseReference dChallenges;

    //Recycler view elements
    private RecyclerView rv;
    private ChallengueAdapterRecyclerView adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home, container, false);
        money = view.findViewById(R.id.fragment_home_money_text_view);
        FragmentTransaction CambioFragment = getActivity().getSupportFragmentManager().beginTransaction();
        //Inicialización de Recycler view
        rv= (RecyclerView) view.findViewById(R.id.fragment_home_challengues_recycler_view);




        //Firebase
        auth = FirebaseAuth.getInstance();
        dbReference = FirebaseDatabase.getInstance().getReference();
        dbUsersReference = dbReference.child("Users");





        //Recycler View
        dChallenges =dbReference.child("Challenges").child("LittleChallenge");
        challengues = new ArrayList<>();
       // challengues.add(new Challengue("TOSTAO","Paga cualquier producto en Tostao con la app.",5));
       // challengues.add(new Challengue("BILLETERA","Envía un monto mayor de $2000 pesos a un amigo.",15));

       // for (int i = 0; i < challengues.size() ; i++) {
       //     dChallenges.push().setValue(challengues.get(i));
       // }




        adapter = new ChallengueAdapterRecyclerView(challengues);
        rv.setLayoutManager(new LinearLayoutManager(view.getContext()));
        rv.setAdapter(adapter);

        dChallenges.addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                challengues.removeAll(challengues);
                for (DataSnapshot snapshot:dataSnapshot.getChildren()) {
                    Challengue  challengueTemp = snapshot.getValue(Challengue.class);
                    challengues.add(challengueTemp);
                    
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });






        dbUsersReference.child(auth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user  =  dataSnapshot.getValue(User.class);
                money.setText("$" + Integer.toString(user.getMoney()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("Error: " + databaseError);
            }
        });



        return view;
    }
}
