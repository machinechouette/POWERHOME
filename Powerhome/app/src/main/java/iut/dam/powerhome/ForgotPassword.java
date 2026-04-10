package iut.dam.powerhome;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ForgotPassword extends AppCompatActivity {

    private ImageButton btnBack;
    private EditText etEmail;
    private EditText etPassword;
    private Button btnReset;
    private TextView tvSignin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        initViews();
        setListeners();
    }

    private void initViews() {
        btnBack  = findViewById(R.id.btn_back);
        etEmail  = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        btnReset = findViewById(R.id.btn_reset);
        tvSignin = findViewById(R.id.tv_signin);

        tvSignin.setPaintFlags(tvSignin.getPaintFlags() | android.graphics.Paint.UNDERLINE_TEXT_FLAG);
    }


    private void setListeners() {

        btnBack.setOnClickListener(v -> finish());

        btnReset.setOnClickListener(v -> {
            if (validateForm()) {
                resetPassword();
            }
        });

        tvSignin.setOnClickListener(v -> {
            Intent intent = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            finish();
        });
    }

    private boolean validateForm() {
        String email = etEmail.getText().toString().trim();

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

        if (TextUtils.isEmpty(etPassword.getText())) {
            etPassword.setError("Le mot de passe est requis");
            etPassword.requestFocus();
            return false;
        }
        if (etPassword.length() < 8) {
            etPassword.setError("Minimum 8 caractères");
            etPassword.requestFocus();
            return false;
        }

        return true;
    }


    private void resetPassword() {
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString();
        WebRequest.updatePassword(this, email, password);
    }
}