package co.edu.icesi.joancaliz.paycool_prototype.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import co.edu.icesi.joancaliz.paycool_prototype.R;

public class AccountCreated extends AppCompatActivity {

    private TextView usernameTextView;
    private Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_created);

        usernameTextView = findViewById(R.id.account_created_username_text_view);
        String username = getIntent().getStringExtra("NAME");
        usernameTextView.setText(username);
        startButton = findViewById(R.id.account_created_start_button);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToOnboarding();
                finish();
            }
        });
    }

    public void goToOnboarding() {
        Intent intent = new Intent(this, Onboarding.class);
        startActivity(intent);
    }
}
