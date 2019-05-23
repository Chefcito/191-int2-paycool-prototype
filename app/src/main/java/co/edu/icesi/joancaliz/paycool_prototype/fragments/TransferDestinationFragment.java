package co.edu.icesi.joancaliz.paycool_prototype.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import co.edu.icesi.joancaliz.paycool_prototype.R;

public class TransferDestinationFragment extends Fragment implements IFragmentInteraction {

    private IFragmentInteraction listener;

    private EditText phoneNumberEditText;
    private Button continueButton;

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
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TransferInfoFragment transferInfoFragment = TransferInfoFragment.newInstance();
                FragmentTransaction fragmentTransaction = getParentFragment().getChildFragmentManager().beginTransaction();
                fragmentTransaction.addToBackStack(transferInfoFragment.getTag() );
                fragmentTransaction.replace(R.id.fragment_transfer_fragment_container_frame_layout, transferInfoFragment).commit();
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
    public void replaceFragment(int containerId, Fragment fragment, boolean stackable) {
        if(listener != null) {
            listener.replaceFragment(containerId, fragment, stackable);
        }
    }
}
