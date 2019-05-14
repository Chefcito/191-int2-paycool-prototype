package co.edu.icesi.joancaliz.paycool_prototype.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import co.edu.icesi.joancaliz.paycool_prototype.Challengue;
import co.edu.icesi.joancaliz.paycool_prototype.adapters.ChallengueAdapter;
import co.edu.icesi.joancaliz.paycool_prototype.R;
import co.edu.icesi.joancaliz.paycool_prototype.User;
import co.edu.icesi.joancaliz.paycool_prototype.fragments.BenefitsFragment;
import co.edu.icesi.joancaliz.paycool_prototype.fragments.HomeFragment;
import co.edu.icesi.joancaliz.paycool_prototype.fragments.WalletFragment;

//El home mijos. La clase de la actividad principal.
public class Home extends AppCompatActivity {

    private TextView money;
    private BottomNavigationView bottomNav;
    private BottomNavigationView.OnNavigationItemSelectedListener bottomNavListener;

    private DatabaseReference dbReference;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //La siguiente linea de código oculta el tittle bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);

        money = findViewById(R.id.home_money_text_view);

        //Navegación principal del home.
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.home_main_fragment_container_layout, new HomeFragment() ).commit();
        bottomNav = findViewById(R.id.home_bottom_navigation_view);
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
                        .replace(R.id.home_main_fragment_container_layout, currentFragment).commit();

                return true;
            }
        };
        bottomNav.setOnNavigationItemSelectedListener(bottomNavListener);

        // Firebase
        auth = FirebaseAuth.getInstance();
        dbReference= FirebaseDatabase.getInstance().getReference();

        if(auth.getCurrentUser() == null) {
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
        }

        dbReference.child("Users").child(auth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user  =  dataSnapshot.getValue(User.class);
                money.setText("$"+Integer.toString(user.getMoney()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}

