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

public class SignUpIdentificationFragment extends Fragment implements IFragmentInteraction{

    private SignUpViewModel signUpViewModel;
    private IFragmentInteraction listener;

    private EditText dniEditText, documentExpeditionDateEditText;
    private Button nextButton;

    public SignUpIdentificationFragment() {
        // Required empty public constructor
    }

    public static SignUpIdentificationFragment newInstance() {
        SignUpIdentificationFragment fragment = new SignUpIdentificationFragment();
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
        View view = inflater.inflate(R.layout.fragment_sign_up_identification, container, false);
        dniEditText = view.findViewById(R.id.fragment_sign_up_identification_dni_edit_text);
        documentExpeditionDateEditText  = view.findViewById(R.id.fragment_sign_up_identification_exp_date_edit_text);
        nextButton = view.findViewById(R.id.fragment_sign_up_identification_next_button);
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
                goToContactFragment();
            }
        });
    }

    public void goToContactFragment() {
        String dni = dniEditText.getText().toString();
        String documentExpeditionDate = documentExpeditionDateEditText.getText().toString();
        if(dni.isEmpty() ) {
            Toast.makeText(getActivity(), "Debes ingresar tu cédula", Toast.LENGTH_LONG).show();
            return;
        }
        else if(documentExpeditionDate.isEmpty() ) {
            Toast.makeText(getActivity(), "Debes ingresar la fecha de expedición de tu cédula", Toast.LENGTH_LONG).show();
            return;
        }
        /*User user = signUpViewModel.getUser().getValue();
        user.setDni(dni);
        user.setSurname(documentExpeditionDate);
        signUpViewModel.setUser(user);*/

        signUpViewModel.setStringData(dni);
        signUpViewModel.setStringData(documentExpeditionDate);
        SignUpContactFragment signUpContactFragment = SignUpContactFragment.newInstance();
        listener.replaceFragment(signUpContactFragment, true);
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
