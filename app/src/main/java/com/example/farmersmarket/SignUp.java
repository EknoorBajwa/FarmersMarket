package com.example.farmersmarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {
    Button signup;
    EditText name, username, email, password, email2, password2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signup = findViewById(R.id.SignUp2);
        name = findViewById(R.id.EditName);
        username = findViewById(R.id.EditUsername);
        email = findViewById(R.id.EditEmail);
        email2 = findViewById(R.id.EditEmail2);
        password = findViewById(R.id.EditPassword);
        password2 = findViewById(R.id.EditPassword2);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameText = name.getText().toString().trim();
                String usernameText = username.getText().toString().trim();
                String emailText = email.getText().toString().trim();
                String emailConfirmText = email2.getText().toString().trim();
                String passwordText = password.getText().toString().trim();
                String password2Text = password2.getText().toString().trim();

                if (TextUtils.isEmpty(nameText) || TextUtils.isEmpty(usernameText) ||
                        TextUtils.isEmpty(emailText) || TextUtils.isEmpty(emailConfirmText) ||
                        TextUtils.isEmpty(passwordText) || TextUtils.isEmpty(password2Text)) {
                    Toast.makeText(SignUp.this, "All fields are required", Toast.LENGTH_SHORT).show();
                } else if (!emailText.equals(emailConfirmText)) {
                    Toast.makeText(SignUp.this, "Emails do not match", Toast.LENGTH_SHORT).show();
                } else if (!passwordText.equals(password2Text)) {
                    Toast.makeText(SignUp.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(SignUp.this, MainPage.class);
                    intent.putExtra("name", nameText);
                    intent.putExtra("email", emailText);
                    intent.putExtra("username", usernameText);
                    intent.putExtra("password", passwordText);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });

    }
}