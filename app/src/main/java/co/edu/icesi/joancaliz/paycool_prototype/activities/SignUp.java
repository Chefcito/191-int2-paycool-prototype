package co.edu.icesi.joancaliz.paycool_prototype.activities;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import co.edu.icesi.joancaliz.paycool_prototype.R;
import co.edu.icesi.joancaliz.paycool_prototype.User;
import co.edu.icesi.joancaliz.paycool_prototype.fragments.IFragmentInteraction;
import co.edu.icesi.joancaliz.paycool_prototype.fragments.SignUpNameFragment;
import co.edu.icesi.joancaliz.paycool_prototype.view_models.SignUpViewModel;

public class SignUp extends AppCompatActivity implements IFragmentInteraction {

    private FirebaseAuth auth;
    private DatabaseReference dbReference;
    private DatabaseReference dbUsersReference;

    private Fragment currentFragment;
    private FragmentManager fragmentManager;
    private SignUpViewModel signUpViewModel;

    private FrameLayout fragmentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        auth = FirebaseAuth.getInstance();
        dbReference = FirebaseDatabase.getInstance().getReference();
        dbUsersReference = dbReference.child("Users");

        currentFragment = null;
        fragmentManager = getSupportFragmentManager();
        signUpViewModel = ViewModelProviders.of(this).get(SignUpViewModel.class);
        ((SignUpViewModel) signUpViewModel).getUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {

            }
        });

        fragmentContainer = findViewById(R.id.sign_up_fragment_container_frame_layout);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        SignUpNameFragment signUpNameFragment = SignUpNameFragment.newInstance();
        setCurrentFragment(signUpNameFragment);
        fragmentTransaction.replace(fragmentContainer.getId(), currentFragment).commit();
    }

    public void setCurrentFragment(Fragment fragment) {
        currentFragment = fragment;
    }

    public void signUp() {
        final String name = signUpViewModel.getStringData(0);
        final String surname = signUpViewModel.getStringData(1);
        final String dni = signUpViewModel.getStringData(2);
        final String documentExpeditionDate = signUpViewModel.getStringData(3);
        final String phoneNumber = signUpViewModel.getStringData(4);
        final String email = signUpViewModel.getStringData(5);
        final String password = signUpViewModel.getStringData(6);

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful() ) {
                    String userID = auth.getCurrentUser().getUid();
                    User user = new User(userID, name, surname, dni, documentExpeditionDate, phoneNumber, email, password);
                    dbUsersReference.child(userID).setValue(user);
                    goToOnboarding();
                }

                else {
                    Toast.makeText(SignUp.this, task.getResult().toString(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void goToOnboarding() {
        Intent intent = new Intent(this, Onboarding.class);
        startActivity(intent);
    }

    @Override
    public void onFragmentInteraction(String request) {
        switch (request) {
            case "SIGN_UP":
                signUp();
                break;

            default:
                Log.i("onFragmentInteraction", "La petición no existe");
                break;
        }
    }

    @Override
    public void replaceFragment(Fragment fragment, boolean stackable) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        String backStackName = fragment.getTag();
        if(stackable) {
            fragmentTransaction.setPrimaryNavigationFragment(fragment);
            fragmentTransaction.addToBackStack(backStackName);
        }
        setCurrentFragment(fragment);
        fragmentTransaction.replace(fragmentContainer.getId(), fragment, backStackName).commit();
    }
}
