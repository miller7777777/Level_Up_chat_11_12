package ru.levelp.chatlevelup_11_12;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AuthActivity extends AppCompatActivity{

    private String login;
    private String password;
    private EditText etAuthLogin;
    private EditText etAuthPassword;
    private Button signInButton;
    private Button signUpButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        etAuthLogin = (EditText) findViewById(R.id.et_auth_login_input);
        etAuthPassword = (EditText) findViewById(R.id.et_auth_password_input);
        signInButton = (Button) findViewById(R.id.btn_auth_sign_in_button);
        signUpButton = (Button) findViewById(R.id.btn_auth_sign_up_button);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login = etAuthLogin.getText().toString();
                password = etAuthPassword.getText().toString();

                if ((login.length() == 0) || (password.length() < 8)){
                    Toast.makeText(AuthActivity.this, getString(R.string.auth_error), Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(AuthActivity.this, R.string.auth_ok, Toast.LENGTH_SHORT).show();

                }
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AuthActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });

    }
}
