package com.example.volleydemo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.volleydemo.R;
import com.example.volleydemo.models.DetailDatum;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ImagesListAdapter extends RecyclerView.Adapter<ImagesListAdapter.MyViewHolder> {
    private ArrayList<DetailDatum> arrayList;
    private Context mContext;

    public ImagesListAdapter(ArrayList<DetailDatum> arrayList, Context mContext) {
        this.arrayList = arrayList;
        this.mContext=mContext;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.layout_data_items, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
    final DetailDatum detailDatum=arrayList.get(position);
    String imagepath=detailDatum.getImage();
    Picasso.get().load(imagepath).into(holder.ivImage);
    holder.tvTitle.setText(detailDatum.getTitle());


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        ImageView ivImage;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

           tvTitle = itemView.findViewById(R.id.tvTitle);
           ivImage = itemView.findViewById(R.id.imageview);


        }
    }
}
