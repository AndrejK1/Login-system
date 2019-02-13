package com.example.myapplication2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    UsersControl users;
    EditText email;
    EditText password;
    Button logIn;
    TextView passForgot;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_log_in);

        back = findViewById(R.id.icon_back);
        email = findViewById(R.id.inp_reg_email1);
        password = findViewById(R.id.inp_reg_password1);
        logIn = findViewById(R.id.login_button1);
        passForgot = findViewById(R.id.lost_pass);
        users = (UsersControl) getIntent().getSerializableExtra("Users");
    }

    public void buttonClick1(View view) {
        if (view.getId() == logIn.getId()) {
            if (!email.getText().toString().trim().isEmpty()) {
                if (!password.getText().toString().trim().isEmpty()) {
                    String userEmail = email.getText().toString().trim();
                    String userPassword = password.getText().toString().trim();
                    if (users != null && users.userExist(userEmail, userPassword)) {
                        Intent intent = new Intent(this, MyAppActivity.class);
                        intent.putExtra("CurrentUser", users.getUser(userEmail));
                        startActivity(intent);
                    }
                    else
                        Toast.makeText(this, getResources().getText(R.string.user_not_exist), Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(this, getResources().getText(R.string.empty_password), Toast.LENGTH_SHORT).show();
            } else
                Toast.makeText(this, getResources().getText(R.string.empty_email), Toast.LENGTH_SHORT).show();
        }
        if (view.getId() == passForgot.getId()) {
            if (passForgot.getText().toString().contentEquals(getResources().getText(R.string.pass_change))) {
                if (!email.getText().toString().trim().isEmpty()) {
                    if (users.emailExist(email.getText().toString().trim())) {
                        if (!password.getText().toString().trim().isEmpty()) {
                            String userEmail = email.getText().toString().trim();
                            String newPassword = password.getText().toString().trim();
                            users.getUser(userEmail).setPassword(newPassword);
                            users.saveUsers(this);
                            Toast.makeText(this, getResources().getText(R.string.pass_change_update_done), Toast.LENGTH_SHORT).show();
                            passForgot.setText(getResources().getText(R.string.pass_forgot));
                        } else
                            Toast.makeText(this, getResources().getText(R.string.pass_change_update), Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(this, getResources().getText(R.string.pass_change_wrong_email), Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(this, getResources().getText(R.string.pass_change_empty_email), Toast.LENGTH_SHORT).show();
            } else
                passForgot.setText(getResources().getText(R.string.pass_change));
        }
        if (view.getId() == back.getId()) {
            onBackPressed();
        }
    }
}
