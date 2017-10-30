package com.example.nipun.myapp1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    Intent numbersIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the View that shows the numbers category
        /*TextView numbers = (TextView) findViewById(R.id.numbers);

       // Set a click listener on that View
        numbers.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {
                Intent numbersIntent = new Intent(MainActivity.this,NumberSystem.class);
                startActivity(numbersIntent);
            }
        });
    }*/
    }

    //call to number system activity when click on number system option
    public void nS(View view)
    {
         numbersIntent = new Intent(MainActivity.this,NumberSystem.class);
        startActivity(numbersIntent);
    }

    //call to flip flop activity when click on flip flop option
    public void ff(View view)
    {
        numbersIntent = new Intent(MainActivity.this,FlipFlop.class);
        startActivity(numbersIntent);
    }

    //call to code conversion activity when click on code conversion option
    public void cc(View view)
    {
        numbersIntent = new Intent(MainActivity.this,Codecon.class);
        startActivity(numbersIntent);
    }
}
