package com.example.i2i_app;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.annotation.Native;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.android.volley.Response;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText editName;
    private EditText editSurname;
    private EditText editNational;
    private EditText editPassword;
    private EditText editMsisdn;
    private EditText editEmail;
    private Button buttonSubmit;
    private Button buttonLogin;
    private String tarif;

    Spinner spinnerTariff;
    ArrayList <String> tariffList = new ArrayList<String> ();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tariffList.add("Select tariff");
        loadTarif();

        editName = findViewById(R.id.editName);
        editSurname = findViewById(R.id.editSurname);
        editNational = findViewById(R.id.editNational);
        editPassword = findViewById(R.id.editPassword);
        editMsisdn = findViewById(R.id.editMsisdn);
        editEmail = findViewById(R.id.editEmail);
        buttonSubmit = findViewById(R.id.buttonSubmit);
        buttonLogin = findViewById(R.id.buttonLogin);
        spinnerTariff = (Spinner) findViewById(R.id.spinner_tariff);

        ArrayAdapter <CharSequence> adp = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,tariffList);
        adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTariff.setAdapter(adp);
        spinnerTariff.setOnItemSelectedListener(this);

        String selection = "Select tariff";
        int spinnerPosition = adp.getPosition(selection);
        spinnerTariff.setSelection(spinnerPosition);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editName.getText().toString();
                String surname = editSurname.getText().toString();
                String national = editNational.getText().toString();
                String password = editPassword.getText().toString();
                String msisdn = editMsisdn.getText().toString();
                String email = editEmail.getText().toString();

                if (name.isEmpty() || surname.isEmpty() || national.isEmpty() || password.isEmpty() || msisdn.isEmpty() || email.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter", Toast.LENGTH_SHORT).show();
                } else {
                    postData(name, surname, national, password, msisdn, tarif, email);
                }
            }
        });
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        tarif = tariffList.get(position);
        // Toast.makeText(MainActivity.this, "" + tariffList.get(position) + " Selected..", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        Toast.makeText(MainActivity.this, "Seçim Yap", Toast.LENGTH_SHORT).show();
    }

    private void postData(String name, String surname, String national, String password, String msisdn, String tarif, String email) {
        String url = "https://reqres.in/api/users";
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(MainActivity.this, "Data posted succesfully..", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // handling error on below line.
                Toast.makeText(MainActivity.this, "Fail to get response..", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                // on below line we are passing our key
                // and value pair to our parameters.
                params.put("name", name);
                params.put("surname", surname);
                params.put("national", national);
                params.put("password", password);
                params.put("msisdn", msisdn);
                params.put("tarif", tarif);
                params.put("email", email);
                return params;
            }
        };
        queue.add(request);
    }

    private void loadTarif() {
        RequestQueue volleyQueue = Volley.newRequestQueue(MainActivity.this);
        String url = "http://18.194.186.32/v1/api/packages/getAllPackages";
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        JSONObject register = new JSONObject();
                        try {
                            JSONArray array=new JSONArray(response);
                            //JSONArray array=object.getJSONArray(response);
                            // System.out.println(object);
                            for(int i=0;i<array.length();i++) {
                                JSONObject object1=array.getJSONObject(i);
                                //System.out.println(array.getJSONObject(i).getString("packageName"));
                                tariffList.add(array.getJSONObject(i).getString("packageName"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Some error occurred!", Toast.LENGTH_LONG).show();
                    }

                }
        );
        volleyQueue.add(stringRequest);
    }

}