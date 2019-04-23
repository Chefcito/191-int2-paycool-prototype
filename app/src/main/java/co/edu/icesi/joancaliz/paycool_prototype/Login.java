package co.edu.icesi.joancaliz.paycool_prototype;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    private EditText emailEditText, passwordEditText;
    private Button loginButton, goToSignUpButton;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //La siguiente linea de código oculta el tittle bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);



        setContentView(R.layout.activity_login);
        emailEditText = findViewById(R.id.login_email_edit_text);
        passwordEditText = findViewById(R.id.login_password_edit_text);
        loginButton = findViewById(R.id.login_sign_in_button);
        goToSignUpButton = findViewById(R.id.login_go_to_sign_up_button);

        auth = FirebaseAuth.getInstance();

        if(auth.getCurrentUser() != null) {
            auth.signOut();
        }

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        goToSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToSignUp();
            }
        });
    }

    public void login() {
        // Se verifíca que los campos no esten vacíos.
        if(emailEditText.getText().toString().isEmpty() ) {
            Toast.makeText(this, "Ingresa tu correo electrónico", Toast.LENGTH_LONG).show();
            return;
        }

        if(passwordEditText.getText().toString().isEmpty() ) {
            Toast.makeText(this, "Ingresa tu contraseña", Toast.LENGTH_LONG).show();
            return;
        }

        // Esta es la manera en la que se inicia sesión con un usuario previamente creado en la base de datos de Firebase.
        auth.signInWithEmailAndPassword(emailEditText.getText().toString(), passwordEditText.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                // Si el inicio de sesión fue exitoso, entonces...
                if(task.isSuccessful() ) {
                    goToHome();
                }

                // Si no fue exitoso, entonces esto otro...
                else {
                    Toast.makeText(Login.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void goToHome() {
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }

    public void goToSignUp() {
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
    }
}
