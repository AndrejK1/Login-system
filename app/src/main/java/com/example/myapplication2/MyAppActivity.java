package com.example.myapplication2;

import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

public class MyAppActivity extends AppCompatActivity {

    User currentUser;
    TextView userInfo;
    Switch agentSwitch;
    ImageView userIcon;
    ConstraintLayout app_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_app);
        app_layout = findViewById(R.id.app_layout);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            app_layout.setBackground(getResources().getDrawable(R.color.white_color));
        }
        currentUser = (User) getIntent().getSerializableExtra("CurrentUser");
        userInfo = findViewById(R.id.name_surname);
        agentSwitch = findViewById(R.id.switch_agent_status);
        agentSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (agentSwitch.isChecked()) {
                    userIcon.setImageResource(R.drawable.super_agent);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        app_layout.setBackground(getResources().getDrawable(R.color.agent_bg_color));
                    }
                } else {
                    userIcon.setImageResource(R.drawable.default_user_icon);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        app_layout.setBackground(getResources().getDrawable(R.color.white_color));
                    }
                }
            }
        });
        userIcon = findViewById(R.id.userIcon);
        String info = currentUser.getName() + " " + currentUser.getSurname();
        userInfo.setText(info);
    }
}
