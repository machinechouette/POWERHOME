package iut.dam.powerhome;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.koushikdutta.ion.Ion;

public abstract class WebRequest {
    private static final String URL_BASE = "10.0.2.2/POWERHOME/Powerhome_Server/";

    public static void logIn(Context context, String email, String password){
        Ion.with(context)
                .load("POST", URL_BASE + "User/logIn.php")
                .setBodyParameter("email", email)
                .setBodyParameter("password", password)
                .asString()
                .withResponse()
                .setCallback((e, response) -> {

                    if (e == null && response != null) {
                        Toast.makeText(context, "Connexion réussie", Toast.LENGTH_LONG).show();

                        if (context instanceof Activity) {
                            ((Activity) context).finish();
                        }

                    } else {
                        Toast.makeText(context, "Erreur lors de la connexion", Toast.LENGTH_LONG).show();
                    }
                });
    }

    public static void signUp(Context context, String email, String pseudo, String password){
        Ion.with(context)
                .load("POST", URL_BASE + "User/signUp.php")
                .setBodyParameter("email", email)
                .setBodyParameter("pseudo", pseudo)
                .setBodyParameter("password", password)
                .asString()
                .withResponse()
                .setCallback((e, response) -> {

                    if (e == null && response != null) {
                        Toast.makeText(context, "Inscription réussie", Toast.LENGTH_LONG).show();

                        if (context instanceof Activity) {
                            ((Activity) context).finish();
                        }

                    } else {
                        Toast.makeText(context, "Erreur lors de l'inscription", Toast.LENGTH_LONG).show();

                    }
                });
    }
}
