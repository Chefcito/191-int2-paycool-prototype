package co.edu.icesi.joancaliz.paycool_prototype.activities;
import android.content.Intent;
import android.support.annotation.NonNull;
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

public class SignUp extends AppCompatActivity implements IFragmentInteraction {

    private Fragment currentFragment;
    private FragmentManager fragmentManager;

    private FrameLayout fragmentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        currentFragment = null;
        fragmentManager = getSupportFragmentManager();

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
        Intent intent = new Intent(this, Onboarding.class);
        startActivity(intent);
    }

    public void goToLogin() {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

    @Override
    public void onFragmentInteraction(String request) {
        switch (request) {
            case "SIGN_UP":
                Toast.makeText(this, "Registro finalizado", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, Home.class);
                startActivity(intent);
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
