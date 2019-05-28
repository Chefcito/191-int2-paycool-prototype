package co.edu.icesi.joancaliz.paycool_prototype.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
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

public class SignUpPasswordFragment extends Fragment implements IFragmentInteraction{

    private SignUpViewModel signUpViewModel;
    private IFragmentInteraction listener;

    private EditText passwordEditText;
    private Button nextButton;

    public SignUpPasswordFragment() {
        // Required empty public constructor
    }

    public static SignUpPasswordFragment newInstance() {
        SignUpPasswordFragment fragment = new SignUpPasswordFragment();
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
        View view = inflater.inflate(R.layout.fragment_sign_up_password, container, false);
        passwordEditText = view.findViewById(R.id.fragment_sign_up_password_password_edit_text);
        nextButton = view.findViewById(R.id.fragment_sign_up_password_next_button);
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
                goToHome();
            }
        });
    }

    public void goToHome() {
        String password = passwordEditText.getText().toString();
        if(password.isEmpty() ) {
            Toast.makeText(getActivity(), "Debes crear una contraseña", Toast.LENGTH_LONG).show();
            return;
        }

        signUpViewModel.setStringData(password);
        listener.onFragmentInteraction("SIGN_UP");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
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
