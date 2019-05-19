package co.edu.icesi.joancaliz.paycool_prototype.fragments;

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

public class WalletFragment extends Fragment {

    private View view;

    private TextView money;

    private FirebaseAuth auth;
    private DatabaseReference dbReference;
    private DatabaseReference dbUsersReference;

    private static final int TRANSFER_REQUEST = 1;

    private Button withdrawButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_wallet, container, false);

        money = view.findViewById(R.id.fragment_wallet_money_text_view);

        withdrawButton = view.findViewById(R.id.fragment_wallet_withdraw_button);
        withdrawButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToTransferActivity();
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
                System.out.println("Error: " + databaseError);
            }
        });

        return view;
    }

    public void goToTransferActivity() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == TRANSFER_REQUEST) {
            if(resultCode == RESULT_OK) {

            }

            else if(resultCode == RESULT_CANCELED) {

            }
        }
    }
}
