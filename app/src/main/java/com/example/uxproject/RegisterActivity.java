package com.example.uxproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText etUsername = findViewById(R.id.et_reg_username);
        EditText etPassword = findViewById(R.id.et_reg_password);
        EditText etConfirmPassword = findViewById(R.id.et_reg_confirm_password);
        Button btnRegister = findViewById(R.id.btn_register);

        TextView tvLoginLink = findViewById(R.id.tv_login_link);
        if (tvLoginLink != null) {
            tvLoginLink.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameInput = etUsername.getText().toString().trim();
                String passwordInput = etPassword.getText().toString().trim();
                String confirmPasswordInput = etConfirmPassword.getText().toString().trim();

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

                if (confirmPasswordInput.isEmpty()) {
                    etConfirmPassword.setError("Confirm Password must be filled");
                    etConfirmPassword.requestFocus();
                    return;
                }

                if (!passwordInput.equals(confirmPasswordInput)) {
                    etConfirmPassword.setError("Password and Confirm Password must be the same");
                    etConfirmPassword.requestFocus();
                    return;
                }

                GlobalData.username = usernameInput;

                Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
    }
}