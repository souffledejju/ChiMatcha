package com.example.uxproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText etUsername = findViewById(R.id.et_username);
        EditText etPassword = findViewById(R.id.et_password);

        Button btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameInput = etUsername.getText().toString().trim();
                String passwordInput = etPassword.getText().toString().trim();

                if (usernameInput.isEmpty()) {
                    etUsername.setError("Username must be filled");
                    etUsername.requestFocus();
                    return;
                }

                if (usernameInput.length() <= 6) {
                    etUsername.setError("Username length must be greater than 6");
                    etUsername.requestFocus();
                    return;
                }

                if (passwordInput.isEmpty()) {
                    etPassword.setError("Password must be filled");
                    etPassword.requestFocus();
                    return;
                }

                GlobalData.username = usernameInput;
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        TextView tvRegisterLink = findViewById(R.id.tv_register_link);
        tvRegisterLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}