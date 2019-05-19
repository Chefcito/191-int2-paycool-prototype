package co.edu.icesi.joancaliz.paycool_prototype.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import co.edu.icesi.joancaliz.paycool_prototype.Challengue;
import co.edu.icesi.joancaliz.paycool_prototype.R;
import co.edu.icesi.joancaliz.paycool_prototype.User;
import co.edu.icesi.joancaliz.paycool_prototype.adapters.ChallengueAdapter;

public class HomeFragment extends Fragment {

    private View view;

    private TextView money;
    private ListView challenguesListView;
    private ChallengueAdapter challengueAdapter;

    private FirebaseAuth auth;
    private DatabaseReference dbReference;
    private DatabaseReference dbUsersReference;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home, container, false);

        money = view.findViewById(R.id.fragment_home_money_text_view);
        challenguesListView = view.findViewById(R.id.fragment_home_challengues_list_view);
        challengueAdapter = new ChallengueAdapter(getActivity() );
        challenguesListView.setAdapter(challengueAdapter);

        challenguesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                challengueAdapter.goToAccomplishChallengueActivity(i);
            }
        });

        challengueAdapter.addChallengue(new Challengue() );
        challengueAdapter.addChallengue(new Challengue() );
        challengueAdapter.addChallengue(new Challengue() );

        //Firebase
        auth = FirebaseAuth.getInstance();
        dbReference = FirebaseDatabase.getInstance().getReference();
        dbUsersReference = dbReference.child("Users");

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
