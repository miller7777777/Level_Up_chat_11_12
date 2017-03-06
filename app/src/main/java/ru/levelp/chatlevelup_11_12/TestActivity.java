package ru.levelp.chatlevelup_11_12;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class TestActivity extends AppCompatActivity{

    private Button testButton;
    private TextView testText;
    private int count = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        testButton = (Button) findViewById(R.id.test_button);
        testText = (TextView) findViewById(R.id.test_text);
        testButton.setOnClickListener(view -> onTestButtonClicked());
    }

    private void onTestButtonClicked() {

        count++;

        if (count % 5 == 0) {
        testButton.setBackgroundResource(R.color.colorPrimary);
        } else {
            testButton.setBackgroundResource(R.color.colorAccent);
        }
        testText.setText(getString(R.string.test_counter) + " " + String.valueOf(count));

//        Toast.makeText(this, R.string.login_success, Toast.LENGTH_SHORT).show();

    }
}
