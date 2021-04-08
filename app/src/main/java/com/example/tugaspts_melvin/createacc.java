package com.example.tugaspts_melvin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class createacc extends AppCompatActivity {

    Button btn_back;
    Button btn_twitter;
    Button btn_register;
    Button btn_register2;
    ProgressBar pg_loading;
    LinearLayout linearlayout;
    TextInputEditText textInputEditTextusername, textInputEditTextemail, textInputEditTextpassword;
    private static String URL_REGIST = "http://192.168.1.2/android_register_login/register.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createaccount);
        linearlayout = (LinearLayout) findViewById(R.id.linearlayout);
        btn_back = (Button) findViewById(R.id.btn_back);
        btn_twitter = (Button) findViewById(R.id.btn_twitter);
        btn_register = (Button) findViewById(R.id.btn_register);
        textInputEditTextemail = findViewById(R.id.tb_email);
        textInputEditTextpassword = findViewById(R.id.tb_password);
        textInputEditTextusername = findViewById(R.id.tb_username);
        pg_loading = findViewById(R.id.pg_loading);

        //logic start here

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Regist();
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Backtomenu();
            }
        });


        linearlayout.setPadding(0, getStatusBarHeight(), 0, 0);





        //acc.java end phase (Don't delet this 2}})
    }

    private void Regist(){

        pg_loading.setVisibility(View.VISIBLE);
        btn_register.setVisibility(View.GONE);
        btn_register2.setVisibility(View.GONE);

        final String name = this.textInputEditTextusername.getText().toString().trim();
        final String email = this.textInputEditTextemail.getText().toString().trim();
        final String password = this.textInputEditTextpassword.getText().toString().trim();

        if (!name.equals("") && !email.equals("") && !password.equals("")) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                    try {

                        Toast.makeText(createacc.this, response, Toast.LENGTH_SHORT).show();
                        JSONObject jsonObject = new JSONObject(response);
                        String success = jsonObject.getString("success");

                        if(success.equals("1")){

                            Toast.makeText(createacc.this, "Register Success", Toast.LENGTH_SHORT).show();
                            pg_loading.setVisibility(View.GONE);
                            btn_register.setVisibility(View.VISIBLE);
                            btn_register2.setVisibility(View.VISIBLE);
                            Intent intentkemain = new Intent(createacc.this, LoginActivity.class);
                            startActivity(intentkemain);
                            finish();

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(createacc.this, "Register failed" + e.toString(), Toast.LENGTH_SHORT).show();
                        pg_loading.setVisibility(View.GONE);
                        btn_register.setVisibility(View.VISIBLE);
                        btn_register2.setVisibility(View.VISIBLE);

                    }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(createacc.this, "Register failed" + error.toString(), Toast.LENGTH_SHORT).show();
                        pg_loading.setVisibility(View.GONE);
                        btn_register.setVisibility(View.VISIBLE);
                        btn_register2.setVisibility(View.VISIBLE);

                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", name);
                params.put("email", email);
                params.put("password", password);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }else {
            Toast.makeText(createacc.this, "All fields are required", Toast.LENGTH_SHORT).show();
            pg_loading.setVisibility(View.GONE);
            btn_register.setVisibility(View.VISIBLE);
            btn_register2.setVisibility(View.VISIBLE);

        }}

    private void Backtomenu() {

        Intent kemainmenu = new Intent(createacc.this, MainTwitter.class);
        startActivity(kemainmenu);
        finish();

    }

    private int getStatusBarHeight() {
        int height;

        Resources myResources = getResources();
        int idStatusBarHeight = myResources.getIdentifier(
                "status_bar_height", "dimen", "android");
        if (idStatusBarHeight > 0) {
            height = getResources().getDimensionPixelSize(idStatusBarHeight);

        }else{
            height = 0;
        }

        return height;
    }

}




