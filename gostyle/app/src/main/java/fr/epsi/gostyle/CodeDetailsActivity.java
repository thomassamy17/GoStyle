package fr.epsi.gostyle;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import fr.epsi.gostyle.controller.RequestHandler;
import fr.epsi.gostyle.controller.SharedPrefManager;
import fr.epsi.gostyle.controller.URLs;
import fr.epsi.gostyle.model.Promo;
import fr.epsi.gostyle.model.User;

public class CodeDetailsActivity extends GostyleActivity {

    protected Promo promo;

    public static void display(Activity activity, Promo p){
        Intent intent=new Intent(activity,CodeDetailsActivity.class);
        intent.putExtra("promo",p);
        activity.startActivityForResult(intent,20);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_details);
        showAccountButton();
        showBackButton();
        promo = (Promo) getIntent().getExtras().get("promo");
        TextView textCode = findViewById(R.id.textCode);
        TextView textDateExp = findViewById(R.id.textDateExp);
        TextView textNbUtilisation = findViewById(R.id.textNbUtilisation);
        TextView textItems = findViewById(R.id.textItems);
        TextView textReduction = findViewById(R.id.textRate);
        Button button = findViewById(R.id.buttonUse);
        textCode.setText(promo.getCode());
        textDateExp.setText("Date d'expiration : "+promo.getDate_fin_validite());
        textNbUtilisation.setText("Utilisation du code :"+promo.getNb_utilisation()+"/"+promo.getUtilisation_max());
        textItems.setText("Produit(s) concerné(s) : "+promo.getItem_name());
        textReduction.setText("Réductions : -"+promo.getRate()+"%");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date date_exp = null;
                try {
                    date_exp = sdf.parse(promo.getDate_fin_validite());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Date today = new Date();
                if(today.compareTo(date_exp) < 0){
                    if (promo.getNb_utilisation()<promo.getUtilisation_max()){
                        ajoutUtilisation(promo);
                    }else{
                        Toast.makeText(CodeDetailsActivity.this,"Nombre utilisation maximum atteint",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(CodeDetailsActivity.this,"Date d'expiration atteinte",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void ajoutUtilisation(final Promo promo) {

        class AjoutUtilisation extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Toast.makeText(CodeDetailsActivity.this,"Code utilisé !",Toast.LENGTH_SHORT).show();
                Intent i = new Intent();
                setResult(Activity.RESULT_OK, i);
                finish();
            }

            @Override
            protected String doInBackground(Void... voids) {
                RequestHandler requestHandler = new RequestHandler();
                User user = SharedPrefManager.getInstance(CodeDetailsActivity.this).getUser();
                HashMap<String, String> params = new HashMap<>();
                params.put("user_id",Integer.toString(user.getId()));
                params.put("promo_id",Integer.toString(promo.getId()));
                return requestHandler.sendPostRequest(URLs.URL_AJOUT, params);
            }
        }

        AjoutUtilisation aj = new AjoutUtilisation();
        aj.execute();
    }
}
