package com.olarewajuoluwafemi34.rambo;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AboutGame extends AppCompatActivity {

    private Button LinkedIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_game);

        LinkedIn = findViewById(R.id.linkedInBttn);
        LinkedIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/oluwafemi-patrick"));
                startActivity(intent);
            }
        });
    }

}
