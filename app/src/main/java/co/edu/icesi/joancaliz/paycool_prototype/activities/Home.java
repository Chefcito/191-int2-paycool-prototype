package co.edu.icesi.joancaliz.paycool_prototype.activities;

import android.content.Intent;
import android.graphics.drawable.TransitionDrawable;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
    private FragmentManager fragmentManager;
    private FragmentManager.OnBackStackChangedListener backStackChangedListener;
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
        fragmentManager = getSupportFragmentManager();
        /*backStackChangedListener = new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {

            }
        };
        fragmentManager.addOnBackStackChangedListener(backStackChangedListener);*/
        bottomNav = findViewById(R.id.home_bottom_navigation_view);

        //Al iniciar la actividad, el fragmento seleccionado es un HomeFragment.
        bottomNav.setSelectedItemId(R.id.bottom_navigation_home_item);
        setMenuCurrentFragment(bottomNav.getSelectedItemId() );

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(fragmentContainer.getId(), currentFragment).commit();

        bottomNavListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                setMenuCurrentFragment(menuItem.getItemId() );
                clearBackStack();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.addToBackStack(currentFragment.getTag() );
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

    public void setCurrentSelectedMenuItem() {
        if(currentFragment instanceof HomeFragment) {
            bottomNav.setSelectedItemId(R.id.bottom_navigation_home_item);
        }
        else if(currentFragment instanceof BenefitsFragment) {
            bottomNav.setSelectedItemId(R.id.bottom_navigation_benefits_item);
        }
        else if(currentFragment instanceof WalletFragment) {
            bottomNav.setSelectedItemId(R.id.bottom_navigation_wallet_item);
        }
    }

    public void setMenuCurrentFragment(int selectedItem) {
        switch( selectedItem ) {
            case R.id.bottom_navigation_home_item:
                currentFragment = HomeFragment.newInstance();
                Log.i("Current fragment", "Home");
                break;

            case R.id.bottom_navigation_benefits_item:
                currentFragment = BenefitsFragment.newInstance();
                Log.i("Current fragment", "Benefits");
                break;

            case R.id.bottom_navigation_wallet_item:
                currentFragment = WalletFragment.newInstance();
                Log.i("Current fragment", "Wallet");
                break;
        }
    }

    public void setCurrentFragment(Fragment fragment) {
        currentFragment = fragment;
    }

    public void clearBackStack() {
        if(fragmentManager.getBackStackEntryCount() > 0) {
            for (int i = 0; i < fragmentManager.getBackStackEntryCount(); i++)
                fragmentManager.popBackStack();
        }
    }

    /*@Override
    public void onBackPressed() {
        super.onBackPressed();
        getSupportFragmentManager().popBackStack();
        setCurrentSelectedMenuItem();
        Log.i("onBackPressed", "Back");
    }*/

    @Override
    public void onFragmentInteraction(String request) {
        switch (request) {
            case "CLEAR_BACK_STACK_WALLET":
                clearBackStack();
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

