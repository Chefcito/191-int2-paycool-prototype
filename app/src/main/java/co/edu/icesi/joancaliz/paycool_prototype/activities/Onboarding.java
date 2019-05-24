package co.edu.icesi.joancaliz.paycool_prototype.activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import co.edu.icesi.joancaliz.paycool_prototype.R;
import co.edu.icesi.joancaliz.paycool_prototype.adapters.OnboardingFragmentCollectionAdapter;
import co.edu.icesi.joancaliz.paycool_prototype.fragments.OnboardingFragment;

public class Onboarding extends AppCompatActivity {

    private FragmentManager fragmentManager;

    private TextView skipTextView;
    private ViewPager fragmentContainer;
    private OnboardingFragmentCollectionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_onboarding);

        fragmentManager = getSupportFragmentManager();

        skipTextView = findViewById(R.id.onboarding_skip_text_view);
        fragmentContainer = findViewById(R.id.onboarding_fragment_container_view_pager);
        adapter = new OnboardingFragmentCollectionAdapter(fragmentManager, this);
        fragmentContainer.setAdapter(adapter);

        skipTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToHome();
            }
        });
    }

    public void goToHome() {
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }
}
