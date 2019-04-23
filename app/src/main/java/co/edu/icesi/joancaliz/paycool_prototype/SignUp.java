package co.edu.icesi.joancaliz.paycool_prototype;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {
    private EditText nameEditText, surnameEditText, dniEditText, phoneNumberEditText, emailEditText, passwordEditText, confirmPasswordEditText;
    private Button createAccountButton, goToLoginButton;

    // Estas son las clases que usaremos de Firebase (creo que son todas las que vamos a usar).
    //FirebaseAuth se encarga de lo que tenga que ver con autenticación.
    private FirebaseAuth auth;

    // FirebaseDatabase es la base de datos como tal de Firebase.
    private FirebaseDatabase database;

    /* Estas dos son referencias a la base de datos. Las referencias son como "direcciones" que
    * señalan una parte de la base de datos como tal. Las usamos para acceder a cierta parte de nuestro
    * interés de la base de datos.
    */
    private DatabaseReference dbReference;
    private DatabaseReference dbUsersReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        nameEditText = findViewById(R.id.sign_up_name_edit_text);
        surnameEditText = findViewById(R.id.sign_up_surname_edit_text);
        dniEditText = findViewById(R.id.sign_up_dni_edit_text);
        phoneNumberEditText = findViewById(R.id.sign_up_phone_number_edit_text);
        emailEditText = findViewById(R.id.sign_up_email_edit_text);
        passwordEditText = findViewById(R.id.sign_up_password_edit_text);
        confirmPasswordEditText = findViewById(R.id.sign_up_confirm_password_edit_text);
        createAccountButton = findViewById(R.id.sign_up_create_account_button);
        goToLoginButton = findViewById(R.id.sign_up_go_to_login_button);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        dbReference = database.getReference();
        dbUsersReference = dbReference.child("Users");

        /* Si ustedes se loguean en sus dispositivos (o el emulador de Android Studio) van a quedarse logueados hasta que
        * cierren la sesión. Si cierran la app y la vuelven a abrir, seguirán logueados. Por eso lo que hago en el siguiente
        * if, es que si hay un usuario logueado, que cierre la sesión.
        */
        if(auth.getCurrentUser() != null) {
            auth.signOut();
        }

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp();
            }
        });

        goToLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToLogin();
            }
        });
    }

    public void signUp() {
        // Un montón de verificaciones antes de crear la cuenta.
        if(nameEditText.getText().toString().isEmpty() ) {
            Toast.makeText(this, "Ingresar tu nombre", Toast.LENGTH_LONG).show();
            return;
        }

        if(surnameEditText.getText().toString().isEmpty() ) {
            Toast.makeText(this, "Ingresar tu apellido", Toast.LENGTH_LONG).show();
            return;
        }

        if(dniEditText.getText().toString().isEmpty() ) {
            Toast.makeText(this, "Ingresar tu número de identidad", Toast.LENGTH_LONG).show();
            return;
        }

        if(phoneNumberEditText.getText().toString().isEmpty() ) {
            Toast.makeText(this, "Ingresar un número de teléfono", Toast.LENGTH_LONG).show();
            return;
        }

        if(emailEditText.getText().toString().isEmpty() ) {
            Toast.makeText(this, "Ingresa un correo electrónico", Toast.LENGTH_LONG).show();
            return;
        }

        if(passwordEditText.getText().toString().isEmpty() ) {
            Toast.makeText(this, "Crea una contraseña", Toast.LENGTH_LONG).show();
            return;
        }

        // Esta de aquí es muy importante. Firebase no permite al usuario crear contraseñas que tengan menos de 6 caracteres.
        if(passwordEditText.getText().toString().length() < 6 ) {
            Toast.makeText(this, "Tu contraseña debe tener mínimo 6 caracteres", Toast.LENGTH_LONG).show();
            return;
        }

        if(confirmPasswordEditText.getText().toString().isEmpty() ) {
            Toast.makeText(this, "Repite tu contraseña", Toast.LENGTH_LONG).show();
            return;
        }

        if(!passwordEditText.getText().toString().equals(confirmPasswordEditText.getText().toString() )) {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_LONG).show();
            return;
        }

        // Este método es el que permite crear una cuenta de usuario en Firebase.
        auth.createUserWithEmailAndPassword(emailEditText.getText().toString(), passwordEditText.getText().toString() )
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                // Si la creación de la cuenta fue exitosa, entonces...
                if(task.isSuccessful() ) {
                    String userID = auth.getCurrentUser().getUid();
                    String name = nameEditText.getText().toString();
                    String surname = surnameEditText.getText().toString();
                    String dni = dniEditText.getText().toString();
                    String phoneNumber = phoneNumberEditText.getText().toString();
                    String email = emailEditText.getText().toString();
                    String password = passwordEditText.getText().toString();

                    // Se añade el usuario a la base de datos.
                    User user = new User(userID, name, surname, dni, phoneNumber, email, password);
                    dbUsersReference.child(userID).setValue(user);

                    goToHome();
                }

                // Si no lo fué, entonces...
                else {
                    Toast.makeText(SignUp.this, "El registro no pudo completarse", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void goToHome() {
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }

    public void goToLogin() {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
}
