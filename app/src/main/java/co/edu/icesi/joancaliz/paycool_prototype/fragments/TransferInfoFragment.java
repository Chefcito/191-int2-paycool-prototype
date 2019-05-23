package co.edu.icesi.joancaliz.paycool_prototype.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

import co.edu.icesi.joancaliz.paycool_prototype.R;

public class TransferInfoFragment extends Fragment implements IFragmentInteraction{
    private static final String TRANSFER_USER_ID = "transferUserId";

    private String transferUserId;

    private IFragmentInteraction listener;

    private EditText moneyAmountEditText, messageEditText;
    private Button transferButton;

    private FirebaseAuth auth;
    private DatabaseReference dbReference;
    private DatabaseReference dbUsersReference;

    public TransferInfoFragment() {
        // Required empty public constructor
    }

    public static TransferInfoFragment newInstance(String transferUserId) {
        TransferInfoFragment fragment = new TransferInfoFragment();
        Bundle args = new Bundle();
        args.putString(TRANSFER_USER_ID, transferUserId);
        fragment.setArguments(args);
        return fragment;
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            transferUserId = getArguments().getString(TRANSFER_USER_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.from(container.getContext() ).inflate(R.layout.fragment_transfer_info, container, false);
        moneyAmountEditText = view.findViewById(R.id.fragment_transfer_info_money_amount_edit_text);
        messageEditText = view.findViewById(R.id.fragment_transfer_info_message_edit_text);
        transferButton = view.findViewById(R.id.fragment_transfer_info_transfer_button);

        auth = FirebaseAuth.getInstance();
        dbReference = FirebaseDatabase.getInstance().getReference();
        dbUsersReference = dbReference.child("Users");
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        transferButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setupTransfer();
            }
        });
    }

    public void setupTransfer() {
        String moneyAmountStr = moneyAmountEditText.getText().toString();
        final int moneyAmount;
        if(moneyAmountStr.isEmpty() ) {
            Toast.makeText(getContext(), "Ingresa el valor a transferir", Toast.LENGTH_LONG).show();
            return;
        }
        moneyAmount = Integer.parseInt(moneyAmountEditText.getText().toString() );

        dbUsersReference.child(auth.getCurrentUser().getUid() ).child("money").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int currentUserMoney = dataSnapshot.getValue(Integer.class);
                transfer(currentUserMoney, moneyAmount);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("Firebase Error: ", databaseError.getMessage() );
            }
        });
    }

    public void transfer(int currentUserMoney, final int moneyAmount) {
        if(currentUserMoney < moneyAmount) {
            Toast.makeText(getActivity(), "No tienes saldo suficiente. :(", Toast.LENGTH_LONG).show();
            return;
        }

        dbUsersReference.child(auth.getCurrentUser().getUid() ).child("money").runTransaction(new Transaction.Handler() {
            @NonNull
            @Override
            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                int remainingMoney = mutableData.getValue(Integer.class) - moneyAmount;
                mutableData.setValue(remainingMoney);
                return Transaction.success(mutableData);
            }

            @Override
            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {
                Log.i("Firebase Transaction: ", "Transaction completed");
            }
        });

        dbUsersReference.child(transferUserId).child("money").runTransaction(new Transaction.Handler() {
            @NonNull
            @Override
            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                int transferMoney = mutableData.getValue(Integer.class) + moneyAmount;
                mutableData.setValue(transferMoney);
                return Transaction.success(mutableData);
            }

            @Override
            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {
                Log.i("Firebase Transaction: ", "Transaction completed");
            }
        });
        onFragmentInteraction("CLEAR_BACK_STACK_WALLET");
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    @Override
    public void onFragmentInteraction(String request) {
        if(listener != null) {
            listener.onFragmentInteraction(request);
        }
    }

    @Override
    public void replaceFragment(Fragment fragment, boolean stackable) {

    }
}
