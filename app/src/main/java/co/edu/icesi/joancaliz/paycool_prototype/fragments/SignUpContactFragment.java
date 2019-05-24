package co.edu.icesi.joancaliz.paycool_prototype.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import co.edu.icesi.joancaliz.paycool_prototype.R;
import co.edu.icesi.joancaliz.paycool_prototype.User;
import co.edu.icesi.joancaliz.paycool_prototype.view_models.SignUpViewModel;

public class SignUpContactFragment extends Fragment implements IFragmentInteraction{

    private SignUpViewModel signUpViewModel;
    private IFragmentInteraction listener;

    private EditText phoneNumberEditText, emailEditText;
    private Button nextButton;

    public SignUpContactFragment() {
        // Required empty public constructor
    }

    public static SignUpContactFragment newInstance() {
        SignUpContactFragment fragment = new SignUpContactFragment();
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
        View view = inflater.inflate(R.layout.fragment_sign_up_contact, container, false);
        phoneNumberEditText = view.findViewById(R.id.fragment_sign_up_contact_phone_number_edit_text);
        emailEditText  = view.findViewById(R.id.fragment_sign_up_contact_email_edit_text);
        nextButton = view.findViewById(R.id.fragment_sign_up_contact_next_button);
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
                goToPasswordFragment();
            }
        });
    }

    public void goToPasswordFragment() {
        String phoneNumber = phoneNumberEditText.getText().toString();
        String email = emailEditText.getText().toString();
        if(phoneNumber.isEmpty() ) {
            Toast.makeText(getActivity(), "Debes ingresar tu número de teléfono", Toast.LENGTH_LONG).show();
            return;
        }
        else if(email.isEmpty() ) {
            Toast.makeText(getActivity(), "Debes ingresar tu email", Toast.LENGTH_LONG).show();
            return;
        }
        /*User user = signUpViewModel.getUser().getValue();
        signUpViewModel.setUser(user);*/
        SignUpPasswordFragment signUpPasswordFragment = SignUpPasswordFragment.newInstance();
        listener.replaceFragment(signUpPasswordFragment, true);
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
