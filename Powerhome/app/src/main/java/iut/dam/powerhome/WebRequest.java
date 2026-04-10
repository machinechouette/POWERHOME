package iut.dam.powerhome;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.koushikdutta.ion.Ion;

public abstract class WebRequest {
    private static final String URL_BASE = "http://10.0.2.2/POWERHOME/Powerhome_Server/";

    public static void logIn(Context context, String email, String password){
        Ion.with(context)
                .load("POST", URL_BASE + "User/logIn.php")
                .setBodyParameter("email", email)
                .setBodyParameter("password", password)
                .asString()
                .withResponse()
                .setCallback((e, response) -> {

                    if (e == null && response != null) {
                        String result = response.getResult();
                        Toast.makeText(context, result, Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(context, "Erreur: " + (e != null ? e.getMessage() : ""), Toast.LENGTH_LONG).show();
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
                        String result = response.getResult();
                        Toast.makeText(context, result, Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(context, "Erreur: " + (e != null ? e.getMessage() : ""), Toast.LENGTH_LONG).show();
                    }
                });
    }

    public static void updatePassword(Context context, String email, String password){
        Ion.with(context)
                .load("POST", URL_BASE + "User/changePassword.php")
                .setBodyParameter("email", email)
                .setBodyParameter("newPassword", password)
                .asString()
                .withResponse()
                .setCallback((e,response)->{

                    if(e == null && response != null){
                        String result = response.getResult();
                        Toast.makeText(context, result, Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(context, "Erreur: " + (e != null ? e.getMessage() : ""), Toast.LENGTH_LONG).show();
                    }
                });
    }
}
