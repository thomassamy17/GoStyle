package fr.epsi.gostyle;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ConnectActivity extends GostyleActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect);
        Button connect = findViewById(R.id.button_connect);
        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CodesActivity.display(ConnectActivity.this);

            }
        });

    }

}
