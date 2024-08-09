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

public class ForgotActivity extends AppCompatActivity {

    private EditText editNational;
    private EditText editEmail;
    private Button buttonSubmit;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);

        editEmail = findViewById(R.id.editEmail);
        editNational = findViewById(R.id.editNational);
        buttonSubmit = findViewById(R.id.buttonSubmit);
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editEmail.getText().toString();
                String national = editNational.getText().toString();

                postData(email, national);
            }
        });
    }

    private void postData(String email, String msisdn) {
        String url = "https://reqres.in/api/users";
        RequestQueue queue = Volley.newRequestQueue(ForgotActivity.this);
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(ForgotActivity.this, "Data posted succesfully..", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ForgotActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ForgotActivity.this, "Fail to get response..", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);
                params.put("msisdn", msisdn);
                return params;
            }
        };
        queue.add(request);
    }
}
