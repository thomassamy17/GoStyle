package fr.epsi.gostyle.controller;

import android.content.Context;
import android.content.SharedPreferences;

import fr.epsi.gostyle.model.User;

public class SharedPrefManager {

    public static final String SHARED_PREF_NAME = "sharedPreferencesLoginAccountGoStyle";
    private static final String KEY_EMAIL = "keyemail";
    private static final String KEY_NAME = "keyname";
    private static final String KEY_FIRSTNAME = "keyfirstname";
    private static final String KEY_ID = "keyid";

    private static SharedPrefManager mInstance;
    private static Context mCtx;

    private SharedPrefManager(Context context) {
        mCtx = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }


    /**
     * Methode d'enregistrement de l'utilisateur dans l'appli por garder la connexion active
     * @param user
     */
    public void userLogin(User user) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_ID, user.getId());
        editor.putString(KEY_EMAIL, user.getEmail());
        editor.putString(KEY_NAME, user.getName());
        editor.putString(KEY_FIRSTNAME, user.getFirstname());
        editor.apply();
    }

    /**
     * Fonction pour savoir si il y a un utilisateur d'enregistrer dans l'appli
     * @return boolean
     */
    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_EMAIL, null) != null;
    }

    /**
     * Fonction pour récuperer l'object User enregistrer dans l'appi
     * @return User
     */
    public User getUser() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new User(
                sharedPreferences.getInt(KEY_ID, -1),
                sharedPreferences.getString(KEY_EMAIL, null),
                sharedPreferences.getString(KEY_FIRSTNAME, null),
                sharedPreferences.getString(KEY_NAME, null)
        );
    }

}
