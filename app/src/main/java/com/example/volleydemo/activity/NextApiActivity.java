package com.example.volleydemo.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.volleydemo.R;
import com.example.volleydemo.models.AmountDetails;
import com.example.volleydemo.models.Data;
import com.example.volleydemo.models.Detail;
import com.example.volleydemo.models.Detail_;
import com.example.volleydemo.models.Example;
import com.example.volleydemo.models.Phase;
import com.example.volleydemo.models.Result;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class NextApiActivity extends AppCompatActivity
{

    RecyclerView recyclerView;
    ListView listView;

    ArrayList<Phase>phaseArrayList=new ArrayList<>();

    ArrayList<Detail_>detail_arrayList=new ArrayList<>();

    ArrayList<Detail>detailArrayList=new ArrayList<>();

    ArrayList<AmountDetails>amountDetailsArrayList=new ArrayList<>();

    ArrayList<String>arrayList=new ArrayList<>();


    private RequestQueue mRequestQue;
    private String url="http://www.mocky.io/v2/5e1342063100007952d476ea";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_api);

        getAllIds();
       checkNetwork();
        cllApi();

    }

    private void checkNetwork()
    {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(getApplicationContext().CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (connectivityManager.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connectivityManager.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connectivityManager.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED) {


        } else if
        (
                connectivityManager.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
                        connectivityManager.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED) {

            Toast.makeText(getApplicationContext(), " Not Connected ", Toast.LENGTH_LONG).show();
        }
    }

    private void cllApi()
    {
        final Example example=new Example();
        final Detail detail = new Detail();
        final Result result=new Result();
        mRequestQue=Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    String jsonObject=response.getString("status");
                    JSONObject jsonObjectResult=response.getJSONObject("result");
                    JSONObject jsonObjectdata=jsonObjectResult.getJSONObject("data");

                    String estimateName=jsonObjectdata.getString("estimateName");
                    String estimateDate=jsonObjectdata.getString("estimateDate");
                    int estimateAmount=jsonObjectdata.getInt("estimateAmount");

                    JSONArray jsonArrayPhase=jsonObjectdata.getJSONArray("phase");
                    for (int i=0;i<jsonArrayPhase.length();i++) {
                        JSONObject jsonObjectPhase = jsonArrayPhase.getJSONObject(i);
                        String title = jsonObjectPhase.getString("title");

                        Phase phase=new Phase();
                        phase.setTitle(title);

                        phaseArrayList.add(phase);
                        Data data=new Data();
                        data.setPhase(phaseArrayList);

                        JSONArray jsonArrayDetail = jsonObjectPhase.getJSONArray("detail");

                        for (int j = 0; j < jsonArrayDetail.length(); j++)
                        {
                            JSONObject jsonObjectDetail=jsonArrayDetail.getJSONObject(j);
                            String detailTitle=jsonObjectDetail.getString("title");
                            int amount=jsonObjectDetail.getInt("amount");

                            Detail detail=new Detail();
                            detail.setTitle(detailTitle);
                            detail.setAmount(amount);

                            detailArrayList.add(detail);
                            phase.setDetail(detailArrayList);



                            // String sublTitle=jsonObjectDetail.getString("subtitle")==""?"":jsonObjectDetail.getString("subtitle");


                            JSONArray jsonArrayDetailIn=jsonObjectDetail.getJSONArray("detail");
                            for (int k=0;k<jsonArrayDetailIn.length();k++)
                            {

                                JSONObject jsonObjectDetailIn=jsonArrayDetailIn.getJSONObject(k);
                                String titleIn=jsonObjectDetailIn.getString("title");
                                String subtitle=jsonObjectDetailIn.getString("subtitle");

                                Detail_ detail1_=new Detail_();
                                detail1_.setTitle(titleIn);
                                detail1_.setSubtitle("subtitle");

                                JSONObject jsonObjectAmountDetails=jsonObjectDetailIn.getJSONObject("amountDetails");
                                String qty=jsonObjectAmountDetails.getString("qty");
                                String unit=jsonObjectAmountDetails.getString("unit");
                                String rate=jsonObjectAmountDetails.getString("rate");
                                String amountIn=jsonObjectAmountDetails.getString("amount");
                                ArrayList<AmountDetails>tempAmountDetailsArrayList=new ArrayList<>();
                                AmountDetails amountDetails=new AmountDetails();
                                amountDetails.setAmount(amountIn);
                                amountDetails.setQty(qty);
                                amountDetails.setRate(rate);
                                amountDetails.setUnit(unit);
                                tempAmountDetailsArrayList.add(amountDetails);
                                amountDetailsArrayList.add(amountDetails);
                                detail1_.setAmountDetails(tempAmountDetailsArrayList);
                                detail_arrayList.add(detail1_);
                                detail.setDetail(detail_arrayList);
                            }

                        }

                        phase.setDetail(detailArrayList);
                    }

                    ListAdapter listAdapter=new com.example.volleydemo.adapters.ListAdapter(detailArrayList,detail_arrayList,amountDetailsArrayList,getApplicationContext());

                    listView.setAdapter(listAdapter);
                    listView.deferNotifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();

            }
        });
        mRequestQue.add(jsonObjectRequest);
    }

    private void getAllIds()
    {
        listView=findViewById(R.id.list);

/*        recyclerView=findViewById(R.id.recyclerViewnextApi);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(NextApiActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(false);*/
    }
}
