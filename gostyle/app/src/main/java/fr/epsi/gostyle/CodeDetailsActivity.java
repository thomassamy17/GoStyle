package fr.epsi.gostyle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.TextView;

import fr.epsi.gostyle.model.Promo;

public class CodeDetailsActivity extends GostyleActivity {

    private Promo promo;

    public static void display(Activity activity, Promo p){
        Intent intent=new Intent(activity, CodeDetailsActivity.class);
        intent.putExtra("promo", (Parcelable) p);
        activity.startActivity(intent);
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


    }
}
