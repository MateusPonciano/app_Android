package com.example.mateus.myapplication;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final Button bRegister = (Button) findViewById(R.id.bRegister);
        //final EditText etCpf = (EditText) findViewById(R.id.etCpf);
        //final EditText etTel = (EditText) findViewById(R.id.etTel);
        final EditText etEmail = (EditText) findViewById(R.id.etEmail);

        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //final String telefone = etTel.getText().toString();
                final String username = etUsername.getText().toString();
                //final String cpf = etCpf.getText().toString();
                final String password = etPassword.getText().toString();
                final String email = etEmail.getText().toString();

                JSONObject jsonObject = new JSONObject();
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                String url = "http://192.168.1.9/accounts/cadastro";

                try {

                    jsonObject.putOpt("username", username);
                    jsonObject.putOpt("password", password);
                    jsonObject.putOpt("email", email);
                    //idn.putOpt("telefone", telefone);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                        new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    //JSONObject jsonResponse = new JSONObject(response);
                                    boolean success = response.getBoolean("success");
                                    if (success) {
                                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                        RegisterActivity.this.startActivity(intent);
                                    } else {
                                        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                        builder.setMessage("Register Failed")
                                                .setNegativeButton("Retry", null)
                                                .create()
                                                .show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                        }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {


                    }

                });

                queue.add(jsObjRequest);
                //Singleton.getInstance(RegisterActivity.this).addToRequestQueue(jsObjRequest);
            }
        });
    }
}

/*JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.POST, url, null,
            new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {
                    try {
                        //JSONObject jsonResponse = new JSONObject(response);
                        boolean success = response.getBoolean("success");
                        if (success) {
                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                            RegisterActivity.this.startActivity(intent);
                        } else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                            builder.setMessage("Register Failed")
                                    .setNegativeButton("Retry", null)
                                    .create()
                                    .show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }, new Response.ErrorListener() {

        @Override
        public void onErrorResponse(VolleyError error) {


        }

    });

                queue.add(jsObjRequest);*/