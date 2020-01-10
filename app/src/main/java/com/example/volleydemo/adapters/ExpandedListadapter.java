package com.example.volleydemo.adapters;

import android.content.Context;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.volleydemo.R;
import com.example.volleydemo.models.AmountDetails;
import com.example.volleydemo.models.Detail;
import com.example.volleydemo.models.Detail_;

import java.util.ArrayList;

public class ExpandedListadapter extends BaseAdapter
{

    Context context;
    ArrayList<Detail_>detail_arrayList=new ArrayList<>();
    ArrayList<AmountDetails>amountDetailsArrayList=new ArrayList<>();
    LayoutInflater layoutInflater;
    public ExpandedListadapter(Context context, ArrayList<Detail_> detail_arrayList,ArrayList<AmountDetails>amountDetailsArrayList)
    {
        this.amountDetailsArrayList=amountDetailsArrayList;
        this.detail_arrayList=detail_arrayList;
        this.context = context;
       layoutInflater =LayoutInflater.from(context);
    }

    @Override
    public int getCount()
    {
        return detail_arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position)
    {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        TextView tvRate, tvUnit, tvAmount, tvQty, tvTitle;
        LinearLayout linearLayout;

        View view = layoutInflater.inflate(R.layout.expanded_list, null);

        tvRate = view.findViewById(R.id.rate);
        tvQty = view.findViewById(R.id.tvQty);
        tvUnit = view.findViewById(R.id.unitId);
        tvAmount = view.findViewById(R.id.amount);
        tvTitle = view.findViewById(R.id.expandedTitle);
        tvRate.setText(amountDetailsArrayList.get(position).getRate());
        tvQty.setText(amountDetailsArrayList.get(position).getQty());
        tvAmount.setText(amountDetailsArrayList.get(position).getAmount());
        tvUnit.setText(amountDetailsArrayList.get(position).getUnit());

     //   amountDetailsArrayList=detail_arrayList.get(position).getAmountDetails();

        //AmountDetails amountDetails=amountDetailsArrayList.get(position);
        tvTitle.setText(detail_arrayList.get(position).getTitle());

        for (AmountDetails amountDetails:amountDetailsArrayList)
        {
            tvRate.setText(amountDetails.getRate());
            tvQty.setText(amountDetails.getQty());
            tvUnit.setText(amountDetails.getUnit());
            tvAmount.setText("" +amountDetails.getAmount());

        }

        return view;
    }
}
