package com.example.i2i_app;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private EditText editMsisdn;
    private EditText editPassword;
    private Button buttonSubmit;
    private Button buttonForgot;
    private Button buttonRegister;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editPassword = findViewById(R.id.editPassword);
        editMsisdn = findViewById(R.id.editMsisdn);
        buttonSubmit = findViewById(R.id.buttonSubmit);
        buttonForgot = findViewById(R.id.buttonForgot);
        buttonRegister = findViewById(R.id.buttonRegister);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String password = editPassword.getText().toString();
                String msisdn = editMsisdn.getText().toString();

                if (password.isEmpty() || msisdn.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please enter name or password", Toast.LENGTH_SHORT).show();
                } else {
                    postData(password, msisdn);
                }
            }
        });
        buttonForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, ForgotActivity.class);
                startActivity(intent);
            }
        });
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    private void postData(String password, String msisdn) {
        String url = "https://reqres.in/api/users";
        RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(LoginActivity.this, "Data posted succesfully..", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, HomePageActivity.class);
                intent.putExtra("msisdn", msisdn);
                startActivity(intent);
                finish();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // handling error on below line.
                Toast.makeText(LoginActivity.this, "Fail to get response..", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("password", password);
                params.put("msisdn", msisdn);
                return params;
            }
        };
        queue.add(request);
    }
}
