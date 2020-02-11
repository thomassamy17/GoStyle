package fr.epsi.gostyle;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class CodesActivity extends GostyleActivity {

    public static void display(Activity activity){
        Intent intent=new Intent(activity, CodesActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_codes);
        showBackButton();
        showAccountButton();
        ImageView image = findViewById(R.id.imageViewCamera);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CodesActivity.this,QRCodeActivity.class);
                startActivity(intent);
            }
        });
    }

}
