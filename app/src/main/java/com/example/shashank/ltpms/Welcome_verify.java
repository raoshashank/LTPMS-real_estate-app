package com.example.shashank.ltpms;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
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

public class Welcome_verify extends AppCompatActivity {
    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Intent parent = getIntent();
        final String email = parent.getStringExtra("email");
        String resp =parent.getStringExtra("response");
        setContentView(R.layout.activity_welcome_verify);
        RelativeLayout rel = (RelativeLayout)findViewById(R.id.rel);
        Snackbar.make(rel,resp,Snackbar.LENGTH_INDEFINITE).show();
        TextView verify = (TextView)findViewById(R.id.verify);
        final String URL_Verify = "http://10.50.17.79/webapp/test.php";
        requestQueue = Volley.newRequestQueue(this);
      verify.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              StringRequest request = new StringRequest(Request.Method.POST, URL_Verify, new Response.Listener<String>() {
                  @Override
                  public void onResponse(String response) {
                      try {
                          JSONObject jsonObject = new JSONObject(response);
                          if (jsonObject.names().get(0).equals("success")) {
                              Intent intent = new Intent(getApplication(),HomeScreen.class);
                              intent.putExtra("email",email);
                              startActivity(intent);
                          }
                          else{
                              Toast.makeText(getApplicationContext(),response.toString(),Toast.LENGTH_LONG).show();
                          }

                      } catch (JSONException j) {
                      }
                  }
              }, new Response.ErrorListener() {
                  @Override
                  public void onErrorResponse(VolleyError error) {

                  }
              }){
                  @Override
                  protected Map<String, String> getParams() throws AuthFailureError {
                      HashMap<String,String> hashMap = new HashMap<>();
                      hashMap.put("email",parent.getStringExtra("email"));
                      return hashMap;
                  }
              };


              requestQueue.add(request);
          }
      });

        TextView lognow = (TextView)findViewById(R.id.lognow);
        lognow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplication(),MainActivity.class));
            }
        });
    }

}
