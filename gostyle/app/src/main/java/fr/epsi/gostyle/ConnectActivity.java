package fr.epsi.gostyle;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import fr.epsi.gostyle.controller.RequestHandler;
import fr.epsi.gostyle.controller.SharedPrefManager;
import fr.epsi.gostyle.controller.URLs;
import fr.epsi.gostyle.model.User;

/**
 * Class ConnectActivity
 * Vue Connexion dans l'application
 */
public class ConnectActivity extends GostyleActivity{

    EditText editTextEmail, editTextPassword;

    /**
     * Methode pour démarrer cette activté
     * @param activity
     */
    public static void display(Activity activity){
        Intent intent=new Intent(activity, ConnectActivity.class);
        activity.startActivity(intent);
        activity.finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect);
        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            CodesActivity.display(ConnectActivity.this);
        }
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        Button connect = findViewById(R.id.button_connect);
        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ConnectActivity.this,"Connexion...",Toast.LENGTH_SHORT).show();
                userLogin();
            }
        });

    }

    /**
     * Methode appelée pour se connecter
     */
    private void userLogin() {

        final String email = editTextEmail.getText().toString();
        final String password = editTextPassword.getText().toString();

        if (TextUtils.isEmpty(email)) {
            editTextEmail.setError("Please enter your username");
            editTextEmail.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            editTextPassword.setError("Please enter your password");
            editTextPassword.requestFocus();
            return;
        }

        /**
         * Class DiscountScan
         * Class pour effectuer la requête vers l'API en asynchrone qui récupère l'objet User si il existe
         */
        class UserLogin extends AsyncTask<Void, Void, String> {


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);


                try {
                    JSONObject obj = new JSONObject(s);

                    if (!obj.getBoolean("error")) {
                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                        JSONObject userJson = obj.getJSONObject("user");

                        User user = new User(
                                userJson.getInt("id"),
                                userJson.getString("email"),
                                userJson.getString("firstname"),
                                userJson.getString("name")
                        );

                        SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);

                        finish();
                        CodesActivity.display(ConnectActivity.this);
                    } else {
                        Toast.makeText(getApplicationContext(), "Invalid email or password", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected String doInBackground(Void... voids) {
                RequestHandler requestHandler = new RequestHandler();

                HashMap<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("password", password);

                return requestHandler.sendPostRequest(URLs.URL_LOGIN, params);
            }
        }

        UserLogin ul = new UserLogin();
        ul.execute();
    }
}
