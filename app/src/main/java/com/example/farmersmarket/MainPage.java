package com.example.farmersmarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

public class MainPage extends AppCompatActivity {
    public static final String PREFS_NAME = "UserLoginPrefs";
    public static final String PREF_USER_LOGGED_IN = "UserLoggedIn";
    public static final String PREF_USERNAME = "Username";

    Button btnsignin, btnsignup;
    EditText username, password;
    ArrayList<User> userList = new ArrayList<>();
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        btnsignin = findViewById(R.id.btnsignin);
        btnsignup = findViewById(R.id.btnsignup);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        boolean isLoggedIn = preferences.getBoolean(PREF_USER_LOGGED_IN, false);
        if (isLoggedIn) {
            Intent intent = new Intent(MainPage.this, Home.class);
            startActivity(intent);
            finish();
        }

        btnsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPage.this, SignUp.class);
                startActivityForResult(intent, 1);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            String name = data.getStringExtra("name");
            String email = data.getStringExtra("email");
            String username = data.getStringExtra("username");
            String password = data.getStringExtra("password");

            User user = new User(name, email, username, password);
            userList.add(user);

        }
    }

    public void login() {
        String userText = username.getText().toString().trim();
        String passText = password.getText().toString().trim();

        for (User u : userList) {
            if (u.getUsername().equals(userText) && u.getPassword().equals(passText)) {
                SharedPreferences preferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean(PREF_USER_LOGGED_IN, true);

                Gson gson = new Gson();
                String userJson = gson.toJson(u);
                editor.putString(PREF_USERNAME, userJson);
                editor.apply();

                Intent intent = new Intent(MainPage.this, Home.class);
                startActivity(intent);
                finish();
                return;
            }
        }
        Toast.makeText(this, "Access Denied", Toast.LENGTH_LONG).show();
    }
}





