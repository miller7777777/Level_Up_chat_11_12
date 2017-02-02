package ru.levelp.chatlevelup_11_12;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class UserProfileActivity extends AppCompatActivity {

    private FloatingActionButton logOutButton;
    private TextView userName;
    private TextView userDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
//        requestWindowFeature(Window.FEATURE_NO_TITLE);

        logOutButton = (FloatingActionButton) findViewById(R.id.user_profile_log_out_btn);
        userName = (TextView) findViewById(R.id.user_profile_tv_user_name);
        userDescription = (TextView) findViewById(R.id.user_profile_tv_user_description);

        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(UserProfileActivity.this, "Log out!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
