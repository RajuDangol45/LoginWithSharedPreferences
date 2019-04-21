package com.raju.loginwithsharedpreferences;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText usernameText;
    EditText passwordText;
    Button loginBtn;

    SharedPreferences loginPreferences;
    SharedPreferences.Editor loginPreferencesEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameText = findViewById(R.id.activity_main_username);
        passwordText = findViewById(R.id.activity_main_password);
        loginBtn = findViewById(R.id.activity_main_login_button);

        loginPreferences = getSharedPreferences("credentials", Context.MODE_PRIVATE);
        loginPreferencesEditor = loginPreferences.edit();

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!usernameText.getText().toString().isEmpty() && !passwordText.getText().toString().isEmpty()){
                    if(testCredentials(usernameText.getText().toString(), passwordText.getText().toString())){
                        loginPreferencesEditor.putString("username", usernameText.getText().toString());
                        loginPreferencesEditor.putString("password", passwordText.getText().toString());
                        loginPreferencesEditor.commit();
                        completeLoginActivity();
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Invalid username or password", Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), "Empty username or password", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private boolean testCredentials(String username, String password){
        if(username == "admin" && password == "admin"){
            return true;
        }
        return false;
    }

    private void completeLoginActivity(){
        Intent loginIntent = new Intent(MainActivity.this, TestSharedPreferences.class);
        startActivity(loginIntent);
        finish();
    }
}
