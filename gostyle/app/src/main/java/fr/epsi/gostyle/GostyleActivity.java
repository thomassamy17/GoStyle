package fr.epsi.gostyle;

import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class GostyleActivity extends AppCompatActivity {

    protected void showBackButton(){
        ImageView backButton = findViewById(R.id.imageViewClose);
        if (backButton != null){
            backButton.setVisibility(View.VISIBLE);
            backButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }

    protected void showAccountButton(){
        ImageView cameraButton = findViewById(R.id.imageViewAccount);
        if (cameraButton != null){
            cameraButton.setVisibility(View.VISIBLE);
            cameraButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        AccountActivity.display(GostyleActivity.this);
                }
            });
        }
    }


}
