package co.edu.icesi.joancaliz.paycool_prototype.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import co.edu.icesi.joancaliz.paycool_prototype.R;

public class SignUpNameFragment extends Fragment implements IFragmentInteraction{

    private IFragmentInteraction listener;

    private Button nextButton;

    public SignUpNameFragment() {
        // Required empty public constructor
    }

    public static SignUpNameFragment newInstance() {
        SignUpNameFragment fragment = new SignUpNameFragment();
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
            throw new RuntimeException(context.toString() + " must implement IFragmentInteraction");
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
        View view = inflater.inflate(R.layout.fragment_sign_up_name, container, false);
        nextButton = view.findViewById(R.id.fragment_sign_up_name_next_button);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignUpIdentificationFragment signUpIdentificationFragment = SignUpIdentificationFragment.newInstance();
                listener.replaceFragment(signUpIdentificationFragment, true);
            }
        });
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
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
