package fr.epsi.gostyle;

import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Class GostyleActivity
 * Les autres activités extends cette class pour récuperer les méthodes associées
 */
public class GostyleActivity extends AppCompatActivity {

    /**
     * Methode pour afficher le bouton retour
     */
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

    /**
     * Methode pour afficher le bouton Compte
     */
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
