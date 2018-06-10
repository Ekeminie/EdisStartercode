package com.example.android.edisstartercode;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        TextView prenursery = findViewById(R.id.prenursery);
        prenursery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LauncherActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        TextView nurseryone = findViewById(R.id.nurseryone);
        nurseryone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LauncherActivity.this, NurseyOneActivity.class);
                startActivity(intent);
            }
        });

        TextView nurserytwo = findViewById(R.id.nurserytwo);

        TextView nurserythree = findViewById(R.id.nurserythree);
    }
}
