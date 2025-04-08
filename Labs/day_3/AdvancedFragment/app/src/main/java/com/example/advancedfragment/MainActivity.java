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
    int counter = 0 ;

    public void increment()
    {
        counter++ ;
    }

    @Override
    public void respond() {
        this.increment();

        FragmentB f2 = (FragmentB) manger.findFragmentByTag("display");
        if(f2 != null) {
            f2.changeData(counter);
        }
    }
    private void restore()
    {
        FragmentB f2 = (FragmentB) manger.findFragmentByTag("display");
        if(f2 != null) {
            f2.changeData(counter);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("counter",counter);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        counter =savedInstanceState.getInt("counter");

        this.restore();
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