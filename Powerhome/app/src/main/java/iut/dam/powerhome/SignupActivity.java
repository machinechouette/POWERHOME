package iut.dam.powerhome;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignupActivity extends AppCompatActivity {

    private EditText etEmail;
    private EditText etUsername;
    private EditText etPassword;
    private EditText etConfirmPassword;
    private Button btnSignup;
    private TextView tvSignin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        initViews();
        setListeners();
    }

    private void initViews() {
        etEmail           = findViewById(R.id.et_email);
        etUsername        = findViewById(R.id.et_username);
        etPassword        = findViewById(R.id.et_password);
        etConfirmPassword = findViewById(R.id.et_confirm_password);
        btnSignup         = findViewById(R.id.btn_signup);
        tvSignin          = findViewById(R.id.tv_signin);
        tvSignin.setPaintFlags(tvSignin.getPaintFlags() | android.graphics.Paint.UNDERLINE_TEXT_FLAG);
    }


    private void setListeners() {

        btnSignup.setOnClickListener(v -> {
            if (validateForm()) {
                performSignup();
            }
        });

        tvSignin.setOnClickListener(v -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }


    private boolean validateForm() {
        String email           = etEmail.getText().toString().trim();
        String username        = etUsername.getText().toString().trim();
        String password        = etPassword.getText().toString();
        String confirmPassword = etConfirmPassword.getText().toString();

        if (TextUtils.isEmpty(email)) {
            etEmail.setError("L'e-mail est requis");
            etEmail.requestFocus();
            return false;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("E-mail invalide");
            etEmail.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(username)) {
            etUsername.setError("Le nom d'utilisateur est requis");
            etUsername.requestFocus();
            return false;
        }
        if (username.length() < 3) {
            etUsername.setError("Minimum 3 caractères");
            etUsername.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(password)) {
            etPassword.setError("Le mot de passe est requis");
            etPassword.requestFocus();
            return false;
        }
        if (password.length() < 8) {
            etPassword.setError("Minimum 8 caractères");
            etPassword.requestFocus();
            return false;
        }

        if (!password.equals(confirmPassword)) {
            etConfirmPassword.setError("Les mots de passe ne correspondent pas");
            etConfirmPassword.requestFocus();
            return false;
        }

        return true;
    }


    private void performSignup() {
        String email    = etEmail.getText().toString().trim();
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString();

        Toast.makeText(this, "Inscription réussie !", Toast.LENGTH_SHORT).show();

    }
}