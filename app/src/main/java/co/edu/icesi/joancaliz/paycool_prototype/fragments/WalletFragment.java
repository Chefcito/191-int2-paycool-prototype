package co.edu.icesi.joancaliz.paycool_prototype.fragments;

import android.content.Context;
import android.content.Intent;
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

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

public class WalletFragment extends Fragment implements IFragmentListener{

    private IFragmentListener listener;
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
        if(context instanceof IFragmentListener) {
            listener = (IFragmentListener) context;
        }
        else {
            throw new RuntimeException(context.toString() + " must implement IFragmentListener");
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
        withdrawButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onFragmentInteraction("TRANSFER_REQUEST");
            }
        });

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

    //Método implementado de la interfáz IFragmentListener
    @Override
    public void onFragmentInteraction(String request) {
        if(listener != null) {
            listener.onFragmentInteraction(request);
        }
    }
}
