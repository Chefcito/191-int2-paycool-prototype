package co.edu.icesi.joancaliz.paycool_prototype.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import co.edu.icesi.joancaliz.paycool_prototype.R;
import co.edu.icesi.joancaliz.paycool_prototype.User;

public class TransferDestinationFragment extends Fragment implements IFragmentInteraction {

    private IFragmentInteraction listener;

    private EditText phoneNumberEditText;
    private Button continueButton;

    private DatabaseReference dbReference;
    private DatabaseReference dbUsersReference;

    public TransferDestinationFragment() {
        // Required empty public constructor
    }

    public static TransferDestinationFragment newInstance() {
        TransferDestinationFragment fragment = new TransferDestinationFragment();
        Bundle args = new Bundle();
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
            throw new RuntimeException(context.toString()
                    + " must implement IFragmentInteraction");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.from(container.getContext() ).inflate(R.layout.fragment_transfer_destination, container, false);
        phoneNumberEditText = view.findViewById(R.id.fragment_transfer_destination_phone_number_edit_text);
        continueButton = view.findViewById(R.id.fragment_transfer_destination_continue_button);

        dbReference = FirebaseDatabase.getInstance().getReference();
        dbUsersReference = dbReference.child("Users");
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNumber = phoneNumberEditText.getText().toString();
                if(phoneNumber.isEmpty() ) {
                    Toast.makeText(getContext(), "Debes ingresar un número telefónico", Toast.LENGTH_LONG).show();
                    return;
                }
                else {
                    findUserByPhone(phoneNumber);
                }
            }
        });
    }

    public void findUserByPhone(String phoneNumber) {
        dbUsersReference.orderByChild("phoneNumber").equalTo(phoneNumber).limitToFirst(1)
                .addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        if(dataSnapshot.hasChildren() ) {
                            User foundUser = dataSnapshot.getValue(User.class);
                            TransferInfoFragment transferInfoFragment = TransferInfoFragment.newInstance(foundUser.getUserID() );
                            FragmentTransaction fragmentTransaction = getParentFragment().getChildFragmentManager().beginTransaction();
                            fragmentTransaction.addToBackStack(transferInfoFragment.getTag() );
                            fragmentTransaction.replace(R.id.fragment_transfer_fragment_container_frame_layout, transferInfoFragment).commit();
                        }
                        else {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Log.i("Firebase Database", "Sin resultados");
                                    Toast.makeText(getActivity(), "No existe el usuario", Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.e("Firebase Error: ", databaseError.getMessage() );
                    }
                });
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onFragmentInteraction(String request) {

    }

    @Override
    public void replaceFragment(Fragment fragment, boolean stackable) {
        if(listener != null) {
            listener.replaceFragment(fragment, stackable);
        }
    }
}
