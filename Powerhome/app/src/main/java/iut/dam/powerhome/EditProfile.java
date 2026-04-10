package iut.dam.powerhome;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EditProfile extends AppCompatActivity {

    private ImageButton btnBack;
    private TextView    tvCurrentPseudo;
    private EditText    etPseudo;
    private EditText    etEmail;
    private EditText    etPassword;
    private EditText    etConfirmPassword;
    private Button      btnSave;

    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        session = new SessionManager(this);

        initViews();
        prefillFields();
        setListeners();
    }

    private void initViews() {
        btnBack           = findViewById(R.id.btn_back);
        tvCurrentPseudo   = findViewById(R.id.tv_current_pseudo);
        etPseudo          = findViewById(R.id.et_pseudo);
        etEmail           = findViewById(R.id.et_email);
        etPassword        = findViewById(R.id.et_password);
        etConfirmPassword = findViewById(R.id.et_confirm_password);
        btnSave           = findViewById(R.id.btn_save);
    }

    private void prefillFields() {
        String pseudo = session.getPseudo();
        String email  = session.getEmail();

        tvCurrentPseudo.setText(pseudo.isEmpty() ? "Résident" : pseudo);
        etPseudo.setText(pseudo);
        etEmail.setText(email);
    }

    private void setListeners() {
        btnBack.setOnClickListener(v -> finish());

        btnSave.setOnClickListener(v -> {
            if (validateForm()) {
                performUpdate();
            }
        });
    }

    private boolean validateForm() {
        String pseudo  = etPseudo.getText().toString().trim();
        String email   = etEmail.getText().toString().trim();
        String pass    = etPassword.getText().toString();
        String confirm = etConfirmPassword.getText().toString();

        if (TextUtils.isEmpty(pseudo)) {
            etPseudo.setError("Le pseudo est requis");
            etPseudo.requestFocus();
            return false;
        }
        if (pseudo.length() < 3) {
            etPseudo.setError("Minimum 3 caractères");
            etPseudo.requestFocus();
            return false;
        }

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

        // Le mot de passe est optionnel : on ne change que si renseigné
        if (!TextUtils.isEmpty(pass)) {
            if (pass.length() < 8) {
                etPassword.setError("Minimum 8 caractères");
                etPassword.requestFocus();
                return false;
            }
            if (!pass.equals(confirm)) {
                etConfirmPassword.setError("Les mots de passe ne correspondent pas");
                etConfirmPassword.requestFocus();
                return false;
            }
        }

        return true;
    }

    private void performUpdate() {
        String pseudo  = etPseudo.getText().toString().trim();
        String email   = etEmail.getText().toString().trim();
        String pass    = etPassword.getText().toString();

        String currentEmail = session.getEmail();

        WebRequest.updateProfile(this, currentEmail, pseudo, email,
                TextUtils.isEmpty(pass) ? null : pass,
                (newEmail, newPseudo) -> {
                    session.updateEmail(newEmail);
                    session.updatePseudo(newPseudo);
                    finish();
                });
    }
}
