package com.example.shashank.ltpms;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final String URL_LOGIN = "http://192.168.0.106/webapp/login.php";
        final EditText email_et = (EditText) findViewById(R.id.email);
        final EditText password_et = (EditText) findViewById(R.id.password);

        //Typeface font =  Typeface.createFromAsset(getAssets(), "fonts/Roboto-Black.ttf");
        TextView login = (TextView) findViewById(R.id.login);
        //login.setTypeface(font);
        TextView signup = (TextView) findViewById(R.id.signup);
        signup.setText(Html.fromHtml("<p><u>SignUp</u></p>"));
        requestQueue = Volley.newRequestQueue(MainActivity.this);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringRequest request = new StringRequest(Request.Method.POST, URL_LOGIN, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.names().get(0).equals("success")) {
                            //    Toast.makeText(getApplicationContext(), jsonObject.names().getString(0).toString(), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(MainActivity.this,HomeScreen.class);
                                intent.putExtra("email",email_et.getText().toString());
                                startActivity(intent);
                            } else {
                                Toast.makeText(getApplicationContext(), jsonObject.getString("error"), Toast.LENGTH_SHORT).show();
                            }


                        } catch (JSONException j) {

                        }
                        ;


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String, String> hashMap = new HashMap<String, String>();
                        hashMap.put("email", email_et.getText().toString());

                        hashMap.put("password", password_et.getText().toString());
                        return hashMap;
                    }
                };
                requestQueue.add(request);

            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });





    }
}
