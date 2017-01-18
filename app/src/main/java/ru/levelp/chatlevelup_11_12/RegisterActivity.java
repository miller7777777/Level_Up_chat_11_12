package ru.levelp.chatlevelup_11_12;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity{

    private String login;
    private String password;
    private String confirmPassword;
    private EditText etRegLogin;
    private EditText etRegPassword;
    private EditText etRegConfirmPassword;
    private Button regButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        etRegLogin = (EditText) findViewById(R.id.et_reg_login_input);
        etRegPassword = (EditText) findViewById(R.id.et_reg_password_input);
        etRegConfirmPassword = (EditText) findViewById(R.id.et_reg_confirm_password_input);
        regButton = (Button) findViewById(R.id.btn_reg_register_button);



        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login = etRegLogin.getText().toString();
                password = etRegPassword.getText().toString();
                confirmPassword = etRegConfirmPassword.getText().toString();

                if((login.length() == 0)||(password.length() < 8)||(!confirmPassword.equals(password))){
                    Toast.makeText(RegisterActivity.this, getString(R.string.register_error), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(RegisterActivity.this, getString(R.string.register_ok), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
