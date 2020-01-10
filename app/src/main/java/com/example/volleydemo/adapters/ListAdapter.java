package com.example.volleydemo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.volleydemo.R;
import com.example.volleydemo.models.AmountDetails;
import com.example.volleydemo.models.Detail;
import com.example.volleydemo.models.Detail_;
import com.example.volleydemo.models.Phase;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends BaseAdapter
{
    private ArrayList<Phase> phaseArrayList;
    private List<Detail> detailArrayList = new ArrayList<>();
    private List<Detail_> subdetail_arrayList = new ArrayList<>();

    private ArrayList<AmountDetails> amountDetailsArrayList = new ArrayList<>();

    ArrayList<String> arrayList = new ArrayList<>();

    ArrayList<Detail_> detail_arrayList=new ArrayList<>();

    private Detail detail = new Detail();
    private static boolean check = false;

    Context context;
    LayoutInflater layoutInflater;

    public ListAdapter(ArrayList<Detail>detailArrayList,ArrayList<Detail_> detail_arrayList,ArrayList<AmountDetails>amountDetailsArrayList,Context context)
    {
        this.amountDetailsArrayList=amountDetailsArrayList;
        this.detail_arrayList=detail_arrayList;
        this.detailArrayList = detailArrayList;
        this.context = context;
        layoutInflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount()
    {
        return detailArrayList.size();
    }

    @Override
    public Object getItem(int position)
    {
        return null;
    }

    @Override
    public long getItemId(int position)
    {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        final RelativeLayout relativeLayout,relativeLayoutExpand = null;
        final ListView listView;
        final TextView tvTitleExpand,tvExpandunit,tvExpandrate,tvExpandAmount;


        View view=layoutInflater.inflate(R.layout.list_layout,null);
        TextView tvTitle=view.findViewById(R.id.txtTitle);
        TextView tvAmount=view.findViewById(R.id.tvamount);
        listView=view.findViewById(R.id.expandedList);


        relativeLayout=view.findViewById(R.id.card_id);

        tvTitle.setText(detailArrayList.get(position).getTitle());
        tvAmount.setText(""+detailArrayList.get(position).getAmount());

        Detail_ detail_=new Detail_();


        relativeLayout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
             {

                if (check==false)
                {
                    relativeLayout.setBackgroundResource(R.drawable.expanded_list);

                   // relativeLayoutExpand.setVisibility(View.VISIBLE);
                    ExpandedListadapter expandedListadapter=new ExpandedListadapter(context,detail_arrayList,amountDetailsArrayList);

                    listView.setAdapter(expandedListadapter);
                    expandedListadapter.notifyDataSetChanged();

                    check=true;
                }
                else if (check==true)
                {
                   // relativeLayoutExpand.setVisibility(View.GONE);
                    relativeLayout.setBackgroundResource(R.drawable.card_shape);
                    check=false;
                }

            }
        });

        return view;
    }
}
