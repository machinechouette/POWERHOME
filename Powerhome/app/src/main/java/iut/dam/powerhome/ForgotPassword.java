package iut.dam.powerhome;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ForgotPassword extends AppCompatActivity {

    private ImageButton btnBack;
    private EditText etEmail;
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
        btnReset = findViewById(R.id.btn_reset);
        tvSignin = findViewById(R.id.tv_signin);

        tvSignin.setPaintFlags(tvSignin.getPaintFlags() | android.graphics.Paint.UNDERLINE_TEXT_FLAG);
    }


    private void setListeners() {

        btnBack.setOnClickListener(v -> finish());

        btnReset.setOnClickListener(v -> {
            if (validateForm()) {
                sendResetLink();
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

        return true;
    }


    private void sendResetLink() {
        String email = etEmail.getText().toString().trim();

        showSuccessMessage();
    }


    private void showSuccessMessage() {
        String email = etEmail.getText().toString().trim();
        Toast.makeText(this, "Lien envoyé à " + email, Toast.LENGTH_LONG).show();

        btnReset.setEnabled(false);
        btnReset.setText("Link sent ✓");
        btnReset.setAlpha(0.7f);
    }
}