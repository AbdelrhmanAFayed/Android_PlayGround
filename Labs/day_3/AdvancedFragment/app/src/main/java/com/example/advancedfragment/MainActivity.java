package com.example.advancedfragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements Communicator {

    FragmentManager manger ;
    FragmentA newFragA ;
    FragmentB newFragB ;



    @Override
    public void respond(int data) {


        FragmentB f2 = (FragmentB) manger.findFragmentByTag("display");
        if(f2 != null) {
            f2.changeData(data);
        }
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manger = getSupportFragmentManager();

        if(savedInstanceState == null) {
            newFragA = new FragmentA();
            newFragB = new FragmentB();


            FragmentTransaction trans = manger.beginTransaction();

            trans.add(R.id.fragmentContainerView, newFragA, "count");
            trans.add(R.id.fragmentContainerView2, newFragB, "display");

            trans.commit();
        }
        else
        {
            newFragA = (FragmentA) manger.findFragmentByTag("count");
            newFragB = (FragmentB) manger.findFragmentByTag("display");
        }



    }
}