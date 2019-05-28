package co.edu.icesi.joancaliz.paycool_prototype.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import co.edu.icesi.joancaliz.paycool_prototype.R;
import co.edu.icesi.joancaliz.paycool_prototype.User;
import co.edu.icesi.joancaliz.paycool_prototype.view_models.SignUpViewModel;

public class SignUpNameFragment extends Fragment implements IFragmentInteraction{

    private SignUpViewModel signUpViewModel;
    private IFragmentInteraction listener;

    private EditText nameEditText, surnameEditText;
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
        nameEditText = view.findViewById(R.id.fragment_sign_up_name_name_edit_text);
        surnameEditText  = view.findViewById(R.id.fragment_sign_up_name_surname_edit_text);
        nextButton = view.findViewById(R.id.fragment_sign_up_name_next_button);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        signUpViewModel = ViewModelProviders.of(getActivity() ).get(SignUpViewModel.class);
        signUpViewModel.getUser().observe(getViewLifecycleOwner(), new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {

            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToIdentificationFragment();
            }
        });
    }

    public void goToIdentificationFragment() {
        String name = nameEditText.getText().toString();
        String surname = surnameEditText.getText().toString();
        if(name.isEmpty() ) {
            Toast.makeText(getActivity(), "Debes ingresar tu nombre", Toast.LENGTH_LONG).show();
            return;
        }
        else if(surname.isEmpty() ) {
            Toast.makeText(getActivity(), "Debes ingresar tus apellidos", Toast.LENGTH_LONG).show();
            return;
        }
        /*User user = new User();
        user.setName(name);
        user.setSurname(surname);
        signUpViewModel.setUser(user);*/

        signUpViewModel.setStringData(name);
        signUpViewModel.setStringData(surname);
        SignUpIdentificationFragment signUpIdentificationFragment = SignUpIdentificationFragment.newInstance();
        listener.replaceFragment(signUpIdentificationFragment, true);
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
