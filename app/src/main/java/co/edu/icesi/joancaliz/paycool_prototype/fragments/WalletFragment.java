package co.edu.icesi.joancaliz.paycool_prototype.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import co.edu.icesi.joancaliz.paycool_prototype.R;
import co.edu.icesi.joancaliz.paycool_prototype.User;

public class WalletFragment extends Fragment implements IFragmentInteraction {

    private IFragmentInteraction listener;
    private View view;

    private TextView money;
    private Button withdrawButton;

    private FirebaseAuth auth;
    private DatabaseReference dbReference;
    private DatabaseReference dbUsersReference;

    public WalletFragment () {

    }

    public static WalletFragment newInstance() {
        WalletFragment walletFragment = new WalletFragment();
        Bundle args = new Bundle();
        walletFragment.setArguments(args);
        return walletFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof IFragmentInteraction) {
            listener = (IFragmentInteraction) context;
        }
        else {
            throw new RuntimeException(context.toString() + " must implement IFragmentInteraction");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null) {

        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_wallet, container, false);

        money = view.findViewById(R.id.fragment_wallet_money_text_view);
        withdrawButton = view.findViewById(R.id.fragment_wallet_withdraw_button);

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
                System.out.println("Firebase database error: " + databaseError);
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        withdrawButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TransferFragment transferFragment = TransferFragment.newInstance();
                replaceFragment(R.id.home_fragment_container_frame_layout, transferFragment, true);
            }
        });
    }

    //Método implementado de la interfáz IFragmentInteraction
    @Override
    public void onFragmentInteraction(String request) {
        if(listener != null) {
            listener.onFragmentInteraction(request);
        }
    }

    @Override
    public void replaceFragment(int containerId, Fragment fragment, boolean stackable) {
        if(listener != null) {
            listener.replaceFragment(containerId, fragment, stackable);
        }
    }
}
