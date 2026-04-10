package iut.dam.powerhome;

import android.content.Context;
import android.widget.Toast;

import com.koushikdutta.ion.Ion;

import org.json.JSONObject;

public abstract class WebRequest {

    private static final String URL_BASE =
            "http://10.0.2.2/POWERHOME/Powerhome_Server/";

    // ─── Interfaces callbacks ────────────────────────────────────────────────

    public interface LoginCallback {
        void onSuccess(String pseudo);
    }

    public interface ProfileCallback {
        void onSuccess(String newEmail, String newPseudo);
    }

    // ─── logIn ───────────────────────────────────────────────────────────────

    public static void logIn(Context context, String email, String password,
                             LoginCallback callback) {
        Ion.with(context)
                .load("POST", URL_BASE + "User/logIn.php")
                .setBodyParameter("email", email)
                .setBodyParameter("password", password)
                .asString()
                .withResponse()
                .setCallback((e, response) -> {
                    if (e == null && response != null) {
                        try {
                            JSONObject json = new JSONObject(response.getResult());
                            if (json.has("pseudo")) {
                                String pseudo = json.getString("pseudo");
                                Toast.makeText(context,
                                        "Bienvenue " + pseudo + " !", Toast.LENGTH_SHORT).show();
                                if (callback != null) callback.onSuccess(pseudo);
                            } else {
                                String msg = json.optString("error", "Identifiants invalides");
                                Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception ex) {
                            Toast.makeText(context,
                                    "Réponse inattendue du serveur", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(context,
                                "Erreur réseau : " + (e != null ? e.getMessage() : ""),
                                Toast.LENGTH_LONG).show();
                    }
                });
    }

    // ─── signUp ──────────────────────────────────────────────────────────────

    public static void signUp(Context context, String email,
                              String pseudo, String password) {
        Ion.with(context)
                .load("POST", URL_BASE + "User/signUp.php")
                .setBodyParameter("email", email)
                .setBodyParameter("pseudo", pseudo)
                .setBodyParameter("password", password)
                .asString()
                .withResponse()
                .setCallback((e, response) -> {
                    if (e == null && response != null) {
                        try {
                            JSONObject json = new JSONObject(response.getResult());
                            if (json.has("success")) {
                                Toast.makeText(context,
                                        "Compte créé ! Vous pouvez vous connecter.",
                                        Toast.LENGTH_LONG).show();
                            } else {
                                String msg = json.optString("error", "Inscription échouée");
                                Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception ex) {
                            Toast.makeText(context,
                                    "Réponse inattendue du serveur", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(context,
                                "Erreur réseau : " + (e != null ? e.getMessage() : ""),
                                Toast.LENGTH_LONG).show();
                    }
                });
    }

    // ─── updatePassword ──────────────────────────────────────────────────────

    public static void updatePassword(Context context, String email, String password) {
        Ion.with(context)
                .load("POST", URL_BASE + "User/changePassword.php")
                .setBodyParameter("email", email)
                .setBodyParameter("newPassword", password)
                .asString()
                .withResponse()
                .setCallback((e, response) -> {
                    if (e == null && response != null) {
                        try {
                            JSONObject json = new JSONObject(response.getResult());
                            if (json.has("success")) {
                                Toast.makeText(context,
                                        "Mot de passe mis à jour.", Toast.LENGTH_SHORT).show();
                            } else {
                                String msg = json.optString("error", "Échec de la mise à jour");
                                Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception ex) {
                            Toast.makeText(context,
                                    "Réponse inattendue du serveur", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(context,
                                "Erreur réseau : " + (e != null ? e.getMessage() : ""),
                                Toast.LENGTH_LONG).show();
                    }
                });
    }

    // ─── updateProfile ───────────────────────────────────────────────────────

    public static void updateProfile(Context context, String currentEmail,
                                     String newPseudo, String newEmail,
                                     String newPassword,
                                     ProfileCallback callback) {
        Ion.with(context)
                .load("POST", URL_BASE + "User/updateProfile.php")
                .setBodyParameter("current_email", currentEmail)
                .setBodyParameter("new_pseudo", newPseudo)
                .setBodyParameter("new_email", newEmail)
                .setBodyParameter("new_password",
                        newPassword != null ? newPassword : "")
                .asString()
                .withResponse()
                .setCallback((e, response) -> {
                    if (e == null && response != null) {
                        try {
                            JSONObject json = new JSONObject(response.getResult());
                            if (json.has("success")) {
                                Toast.makeText(context,
                                        "Profil mis à jour.", Toast.LENGTH_SHORT).show();
                                if (callback != null)
                                    callback.onSuccess(newEmail, newPseudo);
                            } else {
                                String msg = json.optString("error", "Mise à jour échouée");
                                Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception ex) {
                            Toast.makeText(context,
                                    "Réponse inattendue du serveur", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(context,
                                "Erreur réseau : " + (e != null ? e.getMessage() : ""),
                                Toast.LENGTH_LONG).show();
                    }
                });
    }
}
