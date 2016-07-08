package com.example.shashank.ltpms;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
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

public class RegisterActivity extends AppCompatActivity {
    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        final String URL_Register = "http://192.168.0.101/webapp/register.php";
        TextView register = (TextView) findViewById(R.id.register);
        requestQueue = Volley.newRequestQueue(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText email_et = (EditText)findViewById(R.id.email_register);
                final EditText pass_et = (EditText)findViewById(R.id.password_register);
                StringRequest request = new StringRequest(Request.Method.POST, URL_Register, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.names().get(0).equals("success")) {
                                Intent intent=  new Intent(getApplication(),Welcome_verify.class);
                                intent.putExtra("email",email_et.getText().toString());
                                intent.putExtra("response",jsonObject.getString("success"));
                                startActivity(intent);
                            } else {
                                Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException j) {
                            Toast.makeText(getApplicationContext(), j.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String,String> hashMap= new HashMap<>();
                        hashMap.put("email",email_et.getText().toString());
                        hashMap.put("password",pass_et.getText().toString());
                        return  hashMap;
                    }
                };
                requestQueue.add(request);



            }
        });

    }

}
