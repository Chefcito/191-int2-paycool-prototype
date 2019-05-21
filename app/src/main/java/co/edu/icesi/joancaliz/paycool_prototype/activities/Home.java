package co.edu.icesi.joancaliz.paycool_prototype.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.google.firebase.auth.FirebaseAuth;

import co.edu.icesi.joancaliz.paycool_prototype.R;
import co.edu.icesi.joancaliz.paycool_prototype.fragments.BenefitsFragment;
import co.edu.icesi.joancaliz.paycool_prototype.fragments.HomeFragment;
import co.edu.icesi.joancaliz.paycool_prototype.fragments.WalletFragment;

//El home mijos. La clase de la actividad principal.
public class Home extends AppCompatActivity {

    private FrameLayout fragmentContainer;
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



        // Firebase
        // voy a pantalla de login si no estyo logeado
        auth = FirebaseAuth.getInstance();

        if(auth.getCurrentUser() == null) {
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
        } else {


            //Navegación principal del home.
            fragmentContainer = findViewById(R.id.home_fragment_container_frame_layout);
            bottomNav = findViewById(R.id.home_bottom_navigation_view);

            getSupportFragmentManager().beginTransaction()
                    .replace(fragmentContainer.getId(), new HomeFragment() ).commit();

            bottomNavListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment currentFragment = null;

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

                    getSupportFragmentManager().beginTransaction()
                            .replace(fragmentContainer.getId(), currentFragment).commit();

                    return true;
                }
            };
            bottomNav.setOnNavigationItemSelectedListener(bottomNavListener);





        }
    }
}

