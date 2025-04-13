package com.example.basic_view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CatAdapter extends RecyclerView.Adapter<CatAdapter.ViewHolder> {


    Context context ;
    List<Cat> cats ;


    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView textTitle ;
        TextView textDesc ;
        ImageView imgView ;
        ConstraintLayout constraintLayout ;
        View layout ;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView ;
            textTitle = itemView.findViewById(R.id.textTitle);
            textDesc = itemView.findViewById(R.id.textDesc);
            imgView = itemView.findViewById(R.id.imageView);
            constraintLayout = itemView.findViewById(R.id.layout);

        }
    }








    public CatAdapter(Context context, List<Cat> cats) {
        this.context = context;
        this.cats = cats ;
    }

    String TAG = "RecyclerView" ;



    @NonNull
    @Override
    public CatAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       LayoutInflater inflater = LayoutInflater.from(parent.getContext());
       View v = inflater.inflate(R.layout.singlerow,parent,false);
       ViewHolder vh = new ViewHolder(v);

        Log.i(TAG,"=========onCreateViewHolder=============");
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull CatAdapter.ViewHolder holder, int position) {
        holder.imgView.setImageResource(cats.get(position).getImg());
        holder.textTitle.setText(cats.get(position).getTitle());
        holder.textDesc.setText(cats.get(position).getDesc());
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, cats.get(holder.getAdapterPosition()).getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
        Log.i(TAG,"**************onBindViewHolder******************");


    }

    @Override
    public int getItemCount() {
        return cats.size() ;
    }


}
