package com.example.nipun.myapp1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;




public class NumberSystem extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
long value;
    String ans1,ans2,ans3,ans4;
    TextView d1,d2,d3,d4;
    EditText num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_system);

        d1=(TextView)findViewById(R.id.display1);
        d2=(TextView)findViewById(R.id.display2);
        d3=(TextView)findViewById(R.id.display3);
        d4=(TextView)findViewById(R.id.display4);
        num =(EditText)findViewById(R.id.number);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Select_NS, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

       //Spinner spinner = (Spinner) findViewById(spinner);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        value=parent.getItemIdAtPosition(position);
        d1.setVisibility(View.GONE);
        d2.setVisibility(View.GONE);
        d3.setVisibility(View.GONE);
        d4.setVisibility(View.GONE);
        num.setText("");
        if(value==2)
        {
            num.setInputType(InputType.TYPE_CLASS_TEXT);
        }
        else
        {
            num.setInputType(InputType.TYPE_CLASS_NUMBER);
        }
        //Toast.makeText(getApplicationContext(),val ,Toast.LENGTH_LONG).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(getApplicationContext(),"nothing selected" ,Toast.LENGTH_LONG).show();
    }
    //Performing action onItemSelected and onNothing selected


    public void calculateNS(View view)
    {

        int val;
        num =(EditText)findViewById(R.id.number);
        String val1 = num.getText().toString();
        if(val1.equals(""))
        {
            Toast.makeText(getApplicationContext(),"Provide Input " ,Toast.LENGTH_LONG).show();
        }
        else {
            //integer value
             // string value
            // int temp;

            if (value == 0) {                                                         // input decimal


                val = Integer.parseInt(num.getText().toString());
                ans1 = Integer.toBinaryString(val);
                ans2 = (Integer.toHexString(val)).toUpperCase();
                ans3 = Integer.toOctalString(val);
                display(1);

            }
            if (value == 1) {                                                   //input binary
                val = Integer.parseInt(num.getText().toString());
                boolean status = true;
                while (true) {
                    if (val == 0) {
                        break;
                    } else {
                        // int tmp=val%10;
                        if ((val % 10) > 1) {
                            status = false;
                            break;
                        }
                        val = val / 10;
                    }
                }
                if (status) {

                    ans4 = Integer.toString(Integer.parseInt(val1, 2));
                    // temp=Integer.parseInt(val1,2);
                    ans2 = (Integer.toHexString(Integer.parseInt(ans4))).toUpperCase();
                    ans3 = Integer.toOctalString(Integer.parseInt(ans4));
                    display(2);
                } else {
                    d1.setVisibility(View.GONE);
                    d2.setVisibility(View.GONE);
                    d3.setVisibility(View.GONE);
                    d4.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "Invalid Binary Number ", Toast.LENGTH_LONG).show();

                }
            }

            if (value == 2) {
                boolean status = true;
                try {
                    Long.parseLong(val1, 16);

                } catch (NumberFormatException ex) {
                    // Error handling code...
                    status = false;
                    d1.setVisibility(View.GONE);
                    d2.setVisibility(View.GONE);
                    d3.setVisibility(View.GONE);
                    d4.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "Invalid Hexadecimal Number ", Toast.LENGTH_LONG).show();
                }

                if (status) {
                    String digits = "0123456789ABCDEF";
                    val1 = val1.toUpperCase();
                    int v = 0;
                    for (int i = 0; i < val1.length(); i++) {
                        char c = val1.charAt(i);
                        int d = digits.indexOf(c);
                        v = 16 * v + d;
                    }
                    ans4 = Integer.toString(v);
                    ans1 = Integer.toBinaryString(Integer.parseInt(ans4));
                    ans3 = Integer.toOctalString(Integer.parseInt(ans4));
                    display(3);
                }
            }
            if (value == 3) {
                val = Integer.parseInt(num.getText().toString());
                boolean status = true;
                while (true) {
                    if (val == 0) {
                        break;
                    } else {
                        // int tmp=val%10;
                        if ((val % 10) > 7) {
                            status = false;
                            break;
                        }
                        val = val / 10;
                    }
                }
                if (status) {

                    ans4 = Integer.toString(Integer.parseInt(val1, 8));
                    ans1 = Integer.toBinaryString(Integer.parseInt(ans4));
                    ans2 = (Integer.toHexString(Integer.parseInt(ans4))).toUpperCase();
                    display(4);
                } else {
                    d1.setVisibility(View.GONE);
                    d2.setVisibility(View.GONE);
                    d3.setVisibility(View.GONE);
                    d4.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "Invalid Octal Number ", Toast.LENGTH_LONG).show();
                }
            }


        }
    }
    public void display(int i)
    {

        if(i==1)
        {
            d1.setVisibility(View.VISIBLE);
            d2.setVisibility(View.VISIBLE);
            d3.setVisibility(View.VISIBLE);
            //d4.setVisibility(View.GONE);
        }
        else if(i==2)
        { //d1.setVisibility(View.GONE);
            d2.setVisibility(View.VISIBLE);
            d3.setVisibility(View.VISIBLE);
            d4.setVisibility(View.VISIBLE);
        }
        else if(i==3)
        {
            d1.setVisibility(View.VISIBLE);
           // d2.setVisibility(View.VISIBLE);
            d3.setVisibility(View.VISIBLE);
            d4.setVisibility(View.VISIBLE);
        }
        else
        {
            d1.setVisibility(View.VISIBLE);
            d2.setVisibility(View.VISIBLE);
            //d3.setVisibility(View.VISIBLE);
            d4.setVisibility(View.VISIBLE);
        }
        ans1="Binary:  "+ ans1;
        d1.setText(ans1);
        ans2="HexaDecimal:  "+ ans2;
        d2.setText(ans2);
        ans3="Octal:  "+ ans3;
        d3.setText(ans3);
        ans4="Decimal:  "+ ans4;
        d4.setText(ans4);
    }


}
