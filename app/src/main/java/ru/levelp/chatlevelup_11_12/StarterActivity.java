package ru.levelp.chatlevelup_11_12;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StarterActivity extends AppCompatActivity {

    private Button authActivityButton;
    private Button registerActivityButton;
    private Button lifeCycleActivityButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starter);

        authActivityButton = (Button) findViewById(R.id.start_auth_button);
        registerActivityButton = (Button) findViewById(R.id.start_register_button);
        lifeCycleActivityButton = (Button) findViewById(R.id.start_life_cycle_activity_button);

        authActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(StarterActivity.this, AuthActivity.class);
                startActivity(i);
            }
        });

        registerActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(StarterActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });

        lifeCycleActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(StarterActivity.this, LifeCycleActivity.class);
                startActivity(i);
            }
        });
    }
}
