package com.example.shashank.ltpms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.DatePicker;
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

public class UserProfile extends AppCompatActivity {
    RequestQueue requestQueue;
    EditText name_entry,current_address_entry,cntc_entry_1,cntc_entry_2,idp_entry_1,idp_entry_2,permanent_address_entry;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        final String URL_profile = "http://10.50.17.79/webapp/profile_user.php";
        name_entry = (EditText)findViewById(R.id.name_entry);
        final TextView email_entry = (TextView)findViewById(R.id.email_entry);
        DatePicker dt = (DatePicker) findViewById(R.id.DOB_entry);
        cntc_entry_1 = (EditText)findViewById(R.id.cntc_entry_1);
       // cntc_entry_2=(EditText)findViewById(R.id.cntc_entry_2);
        permanent_address_entry = (EditText)findViewById(R.id.permanent_address_entry);
        TextView done = (TextView)findViewById(R.id .done_profile);
        final Intent intent = getIntent();
        email_entry.setText(intent.getStringExtra("email"));
        requestQueue = Volley.newRequestQueue(this);
       done.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               StringRequest request = new StringRequest(
                       Request.Method.POST, URL_profile, new Response.Listener<String>() {
                   @Override
                   public void onResponse(String response) {
                       try {
                           JSONObject jsonObject = new JSONObject(response);
                           Toast.makeText(getApplicationContext(),response.toString(),Toast.LENGTH_SHORT).show();

                       } catch (JSONException j) {

                       }
                   }
               }, new Response.ErrorListener() {
                   @Override
                   public void onErrorResponse(VolleyError error) {

                   }
               }
               ){
                   @Override
                   protected Map<String, String> getParams() throws AuthFailureError {
                       HashMap<String,String> hashMap = new HashMap<String, String>();
                       hashMap.put("name",name_entry.getText().toString());
                       hashMap.put("email",intent.getStringExtra("email"));
                       hashMap.put("cntc1",cntc_entry_1.getText().toString());
                      // hashMap.put("cntc2",cntc_entry_2.getText().toString());
                       hashMap.put("perman",permanent_address_entry.getText().toString());
                       return hashMap;
                   }
               };
               requestQueue.add(request);





           }
       });





    }
}
