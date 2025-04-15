package com.example.productviewer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    Context context ;
    List<Product> products ;


    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView textTitle ;
        TextView textPrice ;
        TextView textDesc ;
        RatingBar Bar ;
        ImageView imgView ;
        ConstraintLayout constraintLayout ;
        View layout ;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView ;
            textTitle = itemView.findViewById(R.id.textTitle);
            textPrice = itemView.findViewById(R.id.textPrice);
            textDesc = itemView.findViewById(R.id.textDesc);
            Bar = itemView.findViewById(R.id.ratingBar);
            imgView = itemView.findViewById(R.id.imgView);
            constraintLayout = itemView.findViewById(R.id.layout);



        }
    }

    private Bitmap Download(String url) throws IOException {

        URL imgURL = new URL(url);

        HttpsURLConnection connection  = (HttpsURLConnection) imgURL.openConnection();

        connection.setRequestMethod("GET");

        connection.connect();

        int RespondCode = connection.getResponseCode();

        InputStream is = connection.getInputStream();

        return BitmapFactory.decodeStream(is);
    }









    public ProductAdapter(Context context, List<Product> products) {
        this.context = context;
        this.products = products;
    }

    String TAG = "RecyclerView" ;



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.single_product,parent,false);
        ViewHolder vh = new ViewHolder(v);

        Log.i(TAG,"=========onCreateViewHolder=============");
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(context).load(products.get(position).imgURL)
                .into(holder.imgView);



        holder.textTitle.setText(products.get(position).getTitle());
        holder.textDesc.setText(products.get(position).getDesc());
        holder.textPrice.setText(products.get(position).getPrice().toString());
        holder.Bar.setRating(products.get(position).getRating().floatValue());

        Log.i(TAG,"**************onBindViewHolder******************");


    }

    @Override
    public int getItemCount() {
        return products.size() ;
    }


}
