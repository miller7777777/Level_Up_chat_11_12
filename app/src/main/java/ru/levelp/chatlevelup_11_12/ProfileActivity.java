package ru.levelp.chatlevelup_11_12;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import ru.levelp.chatlevelup_11_12.storage.UserPreferences;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        UserPreferences preferences = new UserPreferences(this);
        preferences.setToken("tttcwtwwwwwwtyrqt");
        preferences.getToken("fhhh");
    }
}
