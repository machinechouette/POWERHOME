package iut.dam.powerhome;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {

    private static final String PREF_NAME   = "PowerhomeSession";
    private static final String KEY_LOGGED  = "isLoggedIn";
    private static final String KEY_EMAIL   = "email";
    private static final String KEY_PSEUDO  = "pseudo";

    private final SharedPreferences prefs;
    private final SharedPreferences.Editor editor;

    public SessionManager(Context context) {
        prefs  = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    public void createSession(String email, String pseudo) {
        editor.putBoolean(KEY_LOGGED, true);
        editor.putString(KEY_EMAIL,  email);
        editor.putString(KEY_PSEUDO, pseudo);
        editor.apply();
    }

    public void updatePseudo(String pseudo) {
        editor.putString(KEY_PSEUDO, pseudo);
        editor.apply();
    }

    public void updateEmail(String email) {
        editor.putString(KEY_EMAIL, email);
        editor.apply();
    }

    public boolean isLoggedIn() {
        return prefs.getBoolean(KEY_LOGGED, false);
    }

    public String getEmail() {
        return prefs.getString(KEY_EMAIL, "");
    }

    public String getPseudo() {
        return prefs.getString(KEY_PSEUDO, "");
    }

    public void logout() {
        editor.clear();
        editor.apply();
    }
}
