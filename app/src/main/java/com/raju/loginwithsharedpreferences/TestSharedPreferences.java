package com.raju.loginwithsharedpreferences;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TestSharedPreferences extends AppCompatActivity {

    TextView usernameText;
    SharedPreferences loginPreferences;
    String username;
    Button logoutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_shared_preferences);

        usernameText = findViewById(R.id.test_shared_preferences_username);
        loginPreferences = getSharedPreferences("credentials", Context.MODE_PRIVATE);

        if(loginPreferences.contains("username")) {
            username = loginPreferences.getString("username", "Dummy username");
            usernameText.setText(username);
        }
        else{
            startLoginActivity();
        }

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginPreferences.getAll().clear();
                loginPreferences.edit().commit();
                startLoginActivity();
            }
        });
    }

    private void startLoginActivity(){
        Intent loginIntent = new Intent(TestSharedPreferences.this, MainActivity.class);
        startActivity(loginIntent);
        finish();
    }
}
