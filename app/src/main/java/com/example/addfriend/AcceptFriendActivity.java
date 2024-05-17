package com.example.addfriend;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AcceptFriendActivity extends AppCompatActivity {

    private Button passBtn, refuseBtn;
    private TextView usernameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accept_friend);

        passBtn = (Button) findViewById(R.id.passBtn);
        refuseBtn = (Button) findViewById(R.id.refuseBtn);
        usernameTextView = (TextView) findViewById(R.id.usernameTextView);
        passBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        refuseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
