package com.example.basic_view;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Cat> cats = Arrays.asList(new Cat[]{
            new Cat("Cat Number one", "This is Cat Number one", R.drawable.one),
            new Cat("Cat Number two", "This is Cat Number two", R.drawable.two),
            new Cat("Cat Number three", "This is Cat Number three", R.drawable.three),
            new Cat("Cat Number four", "This is Cat Number four", R.drawable.four),
            new Cat("Cat Number five", "This is Cat Number five", R.drawable.five),
            new Cat("Cat Number six", "This is Cat Number six", R.drawable.six),
            new Cat("Cat Number seven", "This is Cat Number seven", R.drawable.seven),
            new Cat("Cat Number eight", "This is Cat Number eight", R.drawable.eight)
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manger = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manger);
        manger.setOrientation(RecyclerView.VERTICAL);
        CatAdapter adapter = new CatAdapter(this,cats);
        recyclerView.setAdapter(adapter);

    }
}