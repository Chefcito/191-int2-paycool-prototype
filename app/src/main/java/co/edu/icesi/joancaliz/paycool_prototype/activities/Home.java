package co.edu.icesi.joancaliz.paycool_prototype.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import co.edu.icesi.joancaliz.paycool_prototype.R;
import co.edu.icesi.joancaliz.paycool_prototype.fragments.BenefitsFragment;
import co.edu.icesi.joancaliz.paycool_prototype.fragments.HomeFragment;
import co.edu.icesi.joancaliz.paycool_prototype.fragments.IFragmentInteraction;
import co.edu.icesi.joancaliz.paycool_prototype.fragments.TransferFragment;
import co.edu.icesi.joancaliz.paycool_prototype.fragments.WalletFragment;

//El home mijos. La clase de la actividad principal.
public class Home extends AppCompatActivity implements IFragmentInteraction {

    private FrameLayout fragmentContainer;
    private Fragment currentFragment = null;
    private BottomNavigationView bottomNav;
    private BottomNavigationView.OnNavigationItemSelectedListener bottomNavListener;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //La siguiente linea de código oculta el tittle bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);

        //Navegación principal del home.
        fragmentContainer = findViewById(R.id.home_fragment_container_frame_layout);
        bottomNav = findViewById(R.id.home_bottom_navigation_view);

        //Al iniciar la actividad, el fragmento seleccionado es un HomeFragment.
        bottomNav.setSelectedItemId(R.id.bottom_navigation_home_item);
        switch (bottomNav.getSelectedItemId() ) {
            case R.id.bottom_navigation_home_item:
                currentFragment = new HomeFragment();
                break;

            case R.id.bottom_navigation_benefits_item:
                currentFragment = new BenefitsFragment();
                break;

            case R.id.bottom_navigation_wallet_item:
                currentFragment = new WalletFragment();
                break;
        }

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(fragmentContainer.getId(), currentFragment).commit();

        bottomNavListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch( menuItem.getItemId() ) {
                    case R.id.bottom_navigation_home_item:
                        currentFragment = new HomeFragment();
                        break;

                    case R.id.bottom_navigation_benefits_item:
                        currentFragment = new BenefitsFragment();
                        break;

                    case R.id.bottom_navigation_wallet_item:
                        currentFragment = new WalletFragment();
                        break;
                }

                clearBackStack();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(fragmentContainer.getId(), currentFragment).commit();

                return true;
            }
        };
        bottomNav.setOnNavigationItemSelectedListener(bottomNavListener);

        // Firebase
        auth = FirebaseAuth.getInstance();

        if(auth.getCurrentUser() == null) {
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
        }
    }

    public void clearBackStack() {
        if(getSupportFragmentManager().getBackStackEntryCount() > 0) {
            for (int i = 0; i < getSupportFragmentManager().getBackStackEntryCount(); i++)
                getSupportFragmentManager().popBackStack();
        }
    }

    @Override
    public void onFragmentInteraction(String request) {
        
    }

    @Override
    public void replaceFragment(int containerId, Fragment fragment, boolean stackable) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        String backStackName = fragment.getTag();
        if(stackable) {
            fragmentTransaction.setPrimaryNavigationFragment(fragment);
            fragmentTransaction.addToBackStack(backStackName);
        }
        fragmentTransaction.replace(containerId, fragment, backStackName).commit();
    }
}

