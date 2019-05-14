package co.edu.icesi.joancaliz.paycool_prototype.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import co.edu.icesi.joancaliz.paycool_prototype.EstablishmentOffer;
import co.edu.icesi.joancaliz.paycool_prototype.R;
import co.edu.icesi.joancaliz.paycool_prototype.adapters.EstablishmentAdapter;

public class BenefitsFragment extends Fragment {

    private View view;
    private ListView offerList;
    private EstablishmentAdapter establishmentAdapter;

    private DatabaseReference dbReference;
    private DatabaseReference dbBenefitsReference;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_benefits, container, false);

        offerList = view.findViewById(R.id.fragment_benefits_offers_list_view);
        establishmentAdapter = new EstablishmentAdapter(getActivity() );
        offerList.setAdapter(establishmentAdapter);

        // Firebase
        dbReference = FirebaseDatabase.getInstance().getReference();
        dbBenefitsReference = dbReference.child("Benefits");

        /*String id1 = dbBenefitsReference.push().getKey();
        EstablishmentOffer offer1 = new EstablishmentOffer();
        offer1.setId(id1);
        offer1.setOffer("10% Dto.");
        offer1.setDescription("Todas las Bebidas");
        offer1.setCompany("STARBUCKS COFFEE");
        dbBenefitsReference.child(id1).setValue(offer1);*/

        dbBenefitsReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                EstablishmentOffer establishmentOffer = dataSnapshot.getValue(EstablishmentOffer.class);
                establishmentAdapter.addEstablishment(establishmentOffer);
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

            }
        });

        return view;
    }
}
