package com.example.nipun.myapp1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.Layout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.attr.value;
import static com.example.nipun.myapp1.R.id.num;
import static com.example.nipun.myapp1.R.id.spinner;

public class FlipFlop extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
String val1,val2="Select";
    EditText e1,e,e2;        // declaration to get three input i.e i/p and current state
    TextView op;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flip_flop);

        Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);     //select flipflop
        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);     //convert to

        /**
         *  first and second choice for flip flop
         **/

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Select_ff, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner 1st choice of flip flop
        spinner1.setAdapter(adapter);
        spinner2.setAdapter(adapter);

        //Spinner spinner = (Spinner) findViewById(spinner);
        spinner1.setOnItemSelectedListener(this);
        spinner2.setOnItemSelectedListener(this);
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
       //Spinner spinner=(Spinner)parent;
        op=(TextView)findViewById(R.id.outputText);
        RelativeLayout ll=(RelativeLayout)findViewById(R.id.input);   //Input Part
         e1=(EditText)findViewById(R.id.ffinput1);
         e=(EditText)findViewById(R.id.ffinput);
         e2=(EditText)findViewById(R.id.psinput);
        if(parent.getId()==R.id.spinner1)
        {
            val1=parent.getSelectedItem().toString();
            op.setText("");
            if (val2.equals("T") || val2.equals("D")) {

                e1.setText("122");
                e1.setVisibility(View.GONE);
                e.setText("");
                e2.setText("");
            } else {

                e1.setVisibility(View.VISIBLE);
                e1.setText("");
                e.setText("");
                e2.setText("");
            }
            if (!val1.equals("Select") && !val2.equals("Select") && !val1.equals(val2)) {
                TextView tv = (TextView) findViewById(R.id.inputText);
                tv.setText("Select i/p (" + val2 + ")");
                ll.setVisibility(View.VISIBLE);            //visible input part to get input

            } else {
               // e1.setText("-1");
                ll.setVisibility(View.GONE);
            }
        }
        if(parent.getId()==R.id.spinner2)
        {
            val2 = parent.getSelectedItem().toString();
           op.setText("");
            if (val2.equals("T") || val2.equals("D")) {
                // EditText e1=(EditText)findViewById(R.id.ffinput1);
                e1.setText("122");
                e1.setVisibility(View.GONE);
                e.setText("");
                e2.setText("");
            } else {
                // EditText e1=(EditText)findViewById(R.id.ffinput1);
                e1.setVisibility(View.VISIBLE);
                e1.setText("");
                e.setText("");
                e2.setText("");
            }
            if (!val1.equals("Select") && !val2.equals("Select") && !val1.equals(val2)) {
                TextView tv = (TextView) findViewById(R.id.inputText);
                tv.setText("Select i/p (" + val2 + ")");
                ll.setVisibility(View.VISIBLE);

            } else {
               // e1.setText("1234");
                ll.setVisibility(View.GONE);
            }
        }
        }

        //Toast.makeText(getApplicationContext(),val ,Toast.LENGTH_LONG).show();
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(getApplicationContext(),"nothing selected" ,Toast.LENGTH_LONG).show();
    }

    public void calculateFF(View view) {
        op = (TextView) findViewById(R.id.outputText);
        e = (EditText) findViewById(R.id.ffinput);
        e2 = (EditText) findViewById(R.id.psinput);
        e1 = (EditText) findViewById(R.id.ffinput1);
           //System.out.println(t1.getText().toString());
       // System.out.println(t3.getText().toString());
       // System.out.println(t2.getText().toString());
        TextView invalid=(TextView)findViewById(R.id.invalid);
        invalid.setVisibility(View.GONE);
        int ans1, ans2;
        if ((e.getText().toString()).equals("") || e1.getText().toString().equals("") || e2.getText().toString().equals("")||e2.getText().toString().equals("i"))
        {
              if(e2.getText().toString().equals("i"))
                  e2.setText("");
               Toast.makeText(this, "Enter missing parameter", Toast.LENGTH_SHORT).show();
        } else {
            int ff1 = Integer.parseInt(e.getText().toString());
            int ff2 = Integer.parseInt(e1.getText().toString());
            int ff3 = Integer.parseInt(e2.getText().toString());
            if (!(ff1 == 1 || ff1 == 0) || !(ff2 == 1 || ff2 == 0 || ff2==122) || !(ff3 == 1 || ff3 == 0)) {
                Toast.makeText(this, "Enter either 1 0r 0(validate value) ", Toast.LENGTH_SHORT).show();
                op.setText("");
            } else {
                if (val1.equals("S-R")) {
                    if (val2.equals("J-K") || val2.equals("T")) {
                        if (val2.equals("J-K"))
                            ans2 = (ff2) * (ff3);
                        else
                            ans2 = (ff1) * (ff3);
                        if (ff3 == 0)
                            ans1 = (ff1) * (1);
                        else
                            ans1 = (ff1) * (0);
                        if (ff1 == 0 && ff3 == 0)
                            op.setText(val1 + " output : " + ans1 + " X");
                        else if (ff2 == 0 && ff3 == 1)
                            op.setText(val1 + " output : " + "X " + ans2);
                        else
                            op.setText(val1 + " output : " + ans1 + " " + ans2);
                    } else {
                        ans1 = ff1;
                        if (ff1 == 0)
                            ans2 = 1;
                        else
                            ans2 = 0;
                        if (ff1 == 0 && ff3 == 0)
                            op.setText(val1 + " output : " + ans1 + " X");
                        else if (ff2 == 1 && ff3 == 1)
                            op.setText(val1 + " output : " + "X " + ans2);
                        else
                            op.setText(val1 + " output : " + ans1 + " " + ans2);
                    }
                } else if (val1.equals("J-K")) {
                    if (val2.equals("S-R") || val2.equals("T")) {
                        if (val2.equals("S-R")) {
                            if(ff1==1 && ff2==1)
                            {e2.setText("i"); invalid.setVisibility(View.VISIBLE);}
                            ans1 = ff1;
                            ans2 = ff2;
                        } else {
                            ans1 = ff1;
                            ans2 = ff1;
                        }
                        if (ff3 == 0)
                            op.setText(val1 + " output : " + ans1 + " X");
                        else
                            op.setText(val1 + " output : " + "X " + ans2);
                        if (ff1 == 1 && ff2 == 1)
                            op.setText(val1 + " output : " + "X" + " X");
                    } else {
                        ans1 = ff1;
                        if (ff1 == 0)
                            ans2 = 1;
                        else
                            ans2 = 0;
                        if (ff3 == 0)
                            op.setText(val1 + " output : " + ans1 + " X");
                        else
                            op.setText(val1 + " output : " + "X " + ans2);
                    }
                } else if (val1.equals("D")) {
                    if (val2.equals("J-K") || val2.equals("T")) {
                        if (val2.equals("J-K")) {
                            if (ff2 == 0)
                                ans2 = 1 * ff3;
                            else
                                ans2 = 0;

                        } else {
                            if (ff1 == 0)
                                ans2 = 1 * ff3;
                            else
                                ans2 = 0;
                        }
                        if (ff3 == 0)
                            ans1 = (ff1 * 1) + ans2;
                        else
                            ans1 = 0 + ans2;
                        op.setText(val1 + " output : " + ans1);

                    } else {
                        if (ff2 == 0)
                            ans2 = 1 * ff3;
                        else
                            ans2 = 0;
                        ans1 = ff1 + ans2;
                        if (ff1 == 1 && ff2 == 1)
                        {e2.setText("i"); invalid.setVisibility(View.VISIBLE); op.setText(val1 + " output : " + "X");}
                        else
                            op.setText(val1 + " output : " + ans1);
                    }

                } else {
                    if (val2.equals("J-K") || val2.equals("S-R")) {

                        if (ff3 == 0)
                            ans1 = (ff1 * 1);
                        else
                            ans1 = 0;

                        ans2 = (ff2 * ff3) + ans1;
                        if (ff1 == 1 && ff2 == 1 && val2.equals("S-R"))
                        { e2.setText("i"); invalid.setVisibility(View.VISIBLE); op.setText(val1 + " output : " + "X");}
                        else
                            op.setText(val1 + " output : " + ans2);
                    } else {
                        if (ff3 == 1 || ff1 == 1)
                            op.setText(val1 + " output : " + "1");
                        else
                            op.setText(val1 + " output : " + "0");
                    }
                }
            }
        }
    }
}
