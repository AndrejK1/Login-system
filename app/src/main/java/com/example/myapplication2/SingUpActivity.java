package com.example.myapplication2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SingUpActivity extends AppCompatActivity {

    private UsersControl users;
    private EditText name;
    private EditText surname;
    private EditText email;
    private EditText password;
    private EditText passwordRepeat;
    private Button singIn;
    private Button logIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_sing_up);

        users = new UsersControl(this);
        users.loadUsers(this);
        name = findViewById(R.id.inp_reg_name);
        surname = findViewById(R.id.inp_reg_surname);
        email = findViewById(R.id.inp_reg_email);
        password = findViewById(R.id.inp_reg_password);
        passwordRepeat = findViewById(R.id.inp_reg_password_repeat);
        singIn = findViewById(R.id.sing_in_button);
        logIn = findViewById(R.id.login_button);
    }

    public void buttonClick(View view){
        if (view.getId() == singIn.getId()) {
            if (!name.getText().toString().trim().isEmpty()) {
                if (!surname.getText().toString().trim().isEmpty()) {
                    if (!email.getText().toString().trim().isEmpty()) {
                        if (!users.emailExist(email.getText().toString().trim())) {
                            if (!password.getText().toString().trim().isEmpty()) {
                                if (passwordRepeat.getText().toString().trim().equals(password.getText().toString().trim())) {
                                    String userName = name.getText().toString().trim();
                                    String userSurname = surname.getText().toString().trim();
                                    String userEmail = email.getText().toString().trim();
                                    String userPassword = password.getText().toString().trim();
                                    User newUser = new User(userName, userSurname, userEmail, userPassword);
                                    users.userAdd(newUser);
                                    users.saveUsers(this);
                                    Toast.makeText(this, getResources().getText(R.string.user_added), Toast.LENGTH_SHORT).show();
                                } else
                                    Toast.makeText(this, getResources().getText(R.string.confirm_password_error), Toast.LENGTH_SHORT).show();
                            } else
                                Toast.makeText(this, getResources().getText(R.string.empty_password), Toast.LENGTH_SHORT).show();
                        } else
                            Toast.makeText(this, getResources().getText(R.string.email_exist), Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(this, getResources().getText(R.string.empty_email), Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(this, getResources().getText(R.string.empty_surname), Toast.LENGTH_SHORT).show();
            } else
                Toast.makeText(this, getResources().getText(R.string.empty_name), Toast.LENGTH_SHORT).show();

        } else if (view.getId() == logIn.getId()) {
            Intent intent = new Intent(this, LoginActivity.class);
            intent.putExtra("Users", users);
            startActivity(intent);
        }
    }
}
