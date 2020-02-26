package fr.epsi.gostyle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import fr.epsi.gostyle.controller.RequestHandler;
import fr.epsi.gostyle.controller.SharedPrefManager;
import fr.epsi.gostyle.controller.URLs;
import fr.epsi.gostyle.model.Promo;
import fr.epsi.gostyle.model.User;

/**
 * Class CodesActivity
 * Vue liste des codes dans l'application
 */
public class CodesActivity extends GostyleActivity {

    protected ArrayList<Promo> promos;
    private PromosAdapter adapter;

    /**
     * Methode pour démarrer cette activté
     * @param activity
     */
    public static void display(Activity activity){
        Intent intent=new Intent(activity, CodesActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_codes);
        if (!SharedPrefManager.getInstance(this).isLoggedIn()) {
            ConnectActivity.display(CodesActivity.this);
        }
        showAccountButton();
        promos=new ArrayList<>();
        ListView listView=findViewById(R.id.listViewCodes);
        adapter=new PromosAdapter(this,R.layout.c_promo,promos);
        listView.setAdapter(adapter);
        getPromos();
        ImageView image = findViewById(R.id.imageViewCamera);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(CodesActivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {
                    ActivityCompat.requestPermissions(CodesActivity.this,new String[] { Manifest.permission.CAMERA },100);
                }else{
                    Intent intent = new Intent(CodesActivity.this,QRCodeActivity.class);
                    startActivityForResult(intent,23);
                }

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CodeDetailsActivity.display(CodesActivity.this, promos.get(position));
            }
        });
    }

    /**
     * Methode
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 23) {
            if(resultCode == RESULT_OK) {
                discountScan(data.getStringExtra("code"));
            }
        }else if (requestCode == 20){
            if(resultCode == RESULT_OK) {
                getPromos();
            }
        }
    }

    /**
     * Methode pour recupérer l'objet Promo après le scan QrCode
     * @param code
     */
    private void discountScan(final String code) {

        /**
         * Class DiscountScan
         * Class pour effectuer la requête vers l'API en asynchrone qui récupère la promo
         */
        class DiscountScan extends AsyncTask<Void, Void, String> {


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

                        JSONObject promoJson = obj.getJSONObject("promo");

                        Promo promo = new Promo(
                                promoJson.getInt("id"),
                                promoJson.getInt("rate"),
                                promoJson.getString("item_name"),
                                promoJson.getString("code"),
                                promoJson.getInt("utilisation_max"),
                                promoJson.getString("date_fin_validite"),
                                promoJson.getInt("nb_utilisation")
                        );
                        promos.add(promo);
                        adapter.notifyDataSetChanged();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected String doInBackground(Void... voids) {
                RequestHandler requestHandler = new RequestHandler();
                User user = SharedPrefManager.getInstance(CodesActivity.this).getUser();
                HashMap<String, String> params = new HashMap<>();
                params.put("code", code);
                params.put("user_id",Integer.toString(user.getId()));

                return requestHandler.sendPostRequest(URLs.URL_PROMO, params);
            }
        }

        DiscountScan ds = new DiscountScan();
        ds.execute();
    }

    /**
     * Methode pour recupérer les promos liées à l'utilisateur pour les afficher dans la liste
     */
    private void getPromos() {

        /**
         * Class GetPromos
         * Class pour effectuer la requête vers l'API en asynchrone qui récupère les promos
         */
        class GetPromos extends AsyncTask<Void, Void, String> {


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                promos.clear();
                adapter.notifyDataSetChanged();
                super.onPostExecute(s);
                try {
                    JSONObject obj = new JSONObject(s);
                    JSONArray jsonArray=obj.getJSONArray("promos");
                    for(int i=0;i<jsonArray.length();i++){
                        Promo p=new Promo(jsonArray.getJSONObject(i));
                        promos.add(p);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                adapter.notifyDataSetChanged();

            }

            @Override
            protected String doInBackground(Void... voids) {
                RequestHandler requestHandler = new RequestHandler();
                User user = SharedPrefManager.getInstance(CodesActivity.this).getUser();
                HashMap<String, String> params = new HashMap<>();
                params.put("user_id",Integer.toString(user.getId()));

                return requestHandler.sendPostRequest(URLs.URL_GETPROMO, params);
            }
        }

        GetPromos gp = new GetPromos();
        gp.execute();
    }

    /**
     * Methode pour demander la permissions de la CAMERA pour le scan
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    public void onRequestPermissionsResult(int requestCode,@NonNull String[] permissions,@NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);
        if (requestCode == 100) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(CodesActivity.this,QRCodeActivity.class);
                startActivityForResult(intent,23);
            }
            else{
                Toast.makeText(CodesActivity.this,"Permission annulée",Toast.LENGTH_SHORT).show();
            }
        }
    }

}
