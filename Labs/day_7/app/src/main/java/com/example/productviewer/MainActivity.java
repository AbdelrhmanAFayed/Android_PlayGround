package com.example.productviewer;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://dummyjson.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ProductService service = retrofit.create(ProductService.class);
        Call<ProductResponse> call = service.getProducts();

        Log.i("onCreate","Hello");
        call.enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if(response.isSuccessful())
                {
                    Log.i("onResponse","Success");
                    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
                    recyclerView.setHasFixedSize(true);
                    LinearLayoutManager manger = new LinearLayoutManager(MainActivity.this);
                    recyclerView.setLayoutManager(manger);
                    manger.setOrientation(RecyclerView.HORIZONTAL);
                    ProductAdapter adapter = new ProductAdapter(MainActivity.this,response.body().products);
                    recyclerView.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {

                Log.i("onFailure","Failure");

            }
        });

        Log.i("onCreate","Goodbye");

    }







}



