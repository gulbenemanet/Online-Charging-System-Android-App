package com.example.i2i_app;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class HomePageActivity extends AppCompatActivity {

    TextView remainingD;
    TextView data;
    TextView data_EndDate;
    TextView remainingM;
    TextView minute;
    TextView minute_EndDate;
    TextView remainingS;
    TextView sms;
    TextView smsEndDate;

    String msisdn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        remainingD = findViewById(R.id.idRemainingD);
        data = findViewById(R.id.idData);
        data_EndDate = findViewById(R.id.idData_EndDate);
        remainingM = findViewById(R.id.idRemainingM);
        minute = findViewById(R.id.idMinute);
        minute_EndDate = findViewById(R.id.idMinute_EndDate);
        remainingS = findViewById(R.id.idRemainingS);
        sms = findViewById(R.id.idSMS);
        smsEndDate = findViewById(R.id.idSMS_EndDate);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
             msisdn = extras.getString("msisdn");
        }
        loadInfo();
    }

    private void loadInfo() {
        RequestQueue volleyQueue = Volley.newRequestQueue(HomePageActivity.this);
        String url = "https://reqres.in/api/users"; // 2 yerine msisdn gelecek
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        try {
                            System.out.println(response);
                            JSONObject register = new JSONObject();
                            DateTimeFormatter f = DateTimeFormatter.ofPattern( "dd/MM/uuuu" );

                            register.put("msisdn", "905433221252");
                            register.put("balanceData", 5120);
                            register.put("balanceSms", 1000);
                            register.put("balanceMinutes", 1500);
                            register.put("sdate", "2024-08-09T06:41:45.388+00:00");
                            register.put("edate", "2024-09-08T06:41:45.388+00:00");
                            // tüm paket de gelecek ona göre yüzde hesaplayacağım..

                            String edate = register.getString("edate").substring(0, 10);

                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("data");
                            JSONObject jo = jsonArray.getJSONObject(0);

                            remainingD.setText(register.getString("balanceData"));
                            data_EndDate.setText(edate);
                            remainingM.setText(register.getString("balanceSms"));
                            minute_EndDate.setText(edate);
                            remainingS.setText(register.getString("balanceMinutes"));
                            smsEndDate.setText(edate);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(HomePageActivity.this, "Some error occurred!", Toast.LENGTH_LONG).show();
                        // log the error message in the error stream
                        Log.e("HomePageActivity", "error: ${error.localizedMessage}");
                    }

                }
        );
        volleyQueue.add(stringRequest);
    }

}
