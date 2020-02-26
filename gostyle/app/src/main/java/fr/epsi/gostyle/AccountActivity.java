package fr.epsi.gostyle;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import fr.epsi.gostyle.controller.SharedPrefManager;
import fr.epsi.gostyle.model.User;

/**
 * Class AccountActivity
 * Vue Compte dans l'application
 */
public class AccountActivity extends GostyleActivity {

    TextView emailView, firstnameView, nameView;

    /**
     * Methode pour démarrer cette activté
     * @param activity
     */
    public static void display(Activity activity){
        Intent intent=new Intent(activity, AccountActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        showBackButton();

        User user = SharedPrefManager.getInstance(this).getUser();
        emailView = findViewById(R.id.accountMail);
        emailView.setText("Email : "+user.getEmail());
        nameView = findViewById(R.id.accountNom);
        nameView.setText("Nom : "+user.getName());
        firstnameView = findViewById(R.id.accountPrenom);
        firstnameView.setText("Prenom : "+user.getFirstname());


        findViewById(R.id.buttonDeco).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences(SharedPrefManager.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();
                ConnectActivity.display(AccountActivity.this);
            }
        });
    }
}
