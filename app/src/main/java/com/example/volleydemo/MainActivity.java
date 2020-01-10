package com.example.volleydemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.volleydemo.activity.NextApiActivity;
import com.example.volleydemo.adapters.ImagesListAdapter;
import com.example.volleydemo.models.DetailDatum;
import com.example.volleydemo.utils.ConnectionCheck;
import com.example.volleydemo.volleysupport.AppController;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button buttonHitApi;
    TextView tvTitle;
    RecyclerView recyclerView;
    Button btn_nextApi;

    private String url = "http://www.mocky.io/v2/5e0ef00c3400003b0f2d7dad";
    ArrayList<DetailDatum> detailDatumArrayList = new ArrayList();

    private RequestQueue mRequestQue;
    private StringRequest mStringRequest;
    private static final String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getAllIds();

        events();
    }

    private void events()
    {
        btn_nextApi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, NextApiActivity.class);
                startActivity(intent);

            }
        });
        buttonHitApi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectionCheck connectionCheck = new ConnectionCheck(getApplicationContext());
                if (connectionCheck.isInternetAccess()==true)
                {
                    getResponse();

                } else {
                    Toast.makeText(getApplicationContext(), " Not Connected ", Toast.LENGTH_LONG).show();
                }
            }

        });
    }

    private void getResponse() {
        mRequestQue = Volley.newRequestQueue(this);

        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                GsonBuilder gsonBuilder=new GsonBuilder();
                Gson gson;
                gson=gsonBuilder.create();

                try
                {




                    JSONObject jsonObjectResult = response.getJSONObject("result");
                    JSONObject jsonObjectData = jsonObjectResult.getJSONObject("data");
                    tvTitle.setText(jsonObjectData.get("title").toString());
                    JSONArray jsonArray = jsonObjectData.getJSONArray("detailData");
                    Log.d("Response", jsonArray.toString());
                    detailDatumArrayList.clear();
                        for (int i = 0; i <jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String title = jsonObject.getString("title");
                        String image = jsonObject.getString("image");
                        DetailDatum detailDatum =new DetailDatum();
                        detailDatum.setImage(image);
                        detailDatum.setTitle(title);
                        detailDatumArrayList.add(detailDatum);
                    }
                    if(detailDatumArrayList.size()!=0)
                    {
                        ImagesListAdapter imagesListAdapter=new ImagesListAdapter(detailDatumArrayList,MainActivity.this);
                        recyclerView.setAdapter(imagesListAdapter);
                        imagesListAdapter.notifyDataSetChanged();
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
        mRequestQue.add(jsonObjectRequest);

    }


    private void getAllIds()
    {

        btn_nextApi=findViewById(R.id.next_api);
        tvTitle = findViewById(R.id.tvtitle);
        buttonHitApi = findViewById(R.id.btn_hit_api);
        recyclerView = findViewById(R.id.recyclerview);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
    }
}
