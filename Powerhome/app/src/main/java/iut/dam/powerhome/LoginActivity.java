package iut.dam.powerhome;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail;
    private EditText etPassword;
    private Button   btnLogin;
    private TextView tvForgotPassword;
    private TextView tvSignup;

    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        session = new SessionManager(this);

        setContentView(R.layout.activity_login);
        initViews();
        setListeners();
    }

    private void initViews() {
        etEmail          = findViewById(R.id.et_email);
        etPassword       = findViewById(R.id.et_password);
        btnLogin         = findViewById(R.id.btn_login);
        tvForgotPassword = findViewById(R.id.tv_forgot_password);
        tvSignup         = findViewById(R.id.tv_signup);

        tvSignup.setPaintFlags(
                tvSignup.getPaintFlags() | android.graphics.Paint.UNDERLINE_TEXT_FLAG);
    }

    private void setListeners() {
        btnLogin.setOnClickListener(v -> {
            if (validateForm()) {
                performLogin();
            }
        });

        tvForgotPassword.setOnClickListener(v ->
                startActivity(new Intent(this, ForgotPassword.class)));

        tvSignup.setOnClickListener(v ->
                startActivity(new Intent(this, SignupActivity.class)));
    }

    private boolean validateForm() {
        String email    = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString();

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
        return true;
    }

    private void performLogin() {
        String email    = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString();

        WebRequest.logIn(this, email, password, (pseudo) -> {
            session.createSession(email, pseudo);
            goToProfile();
        });
    }

    private void goToProfile() {
        Intent intent = new Intent(this, EditProfile.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
