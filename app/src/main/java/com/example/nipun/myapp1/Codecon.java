package com.example.nipun.myapp1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.attr.value;
import static com.example.nipun.myapp1.R.id.ip;
import static com.example.nipun.myapp1.R.id.num;

public class Codecon extends AppCompatActivity implements  AdapterView.OnItemSelectedListener {
    String tans,answer,answer1,val1="Select",val2="Select";
    EditText in;
    TextView out;
    int t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_codecon);

        Spinner spinner1 = (Spinner) findViewById(R.id.code1);     //select flipflop
        Spinner spinner2 = (Spinner) findViewById(R.id.code2);     //convert to
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Select_cc, android.R.layout.simple_spinner_item);

// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner1.setAdapter(adapter);
        spinner2.setAdapter(adapter);

        //Spinner spinner = (Spinner) findViewById(spinner);
        spinner1.setOnItemSelectedListener(this);
        spinner2.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        out=(TextView)findViewById(R.id.op);
        in=(EditText)findViewById(R.id.ccinput);

        RelativeLayout ll=(RelativeLayout)findViewById(R.id.input);   //Input Part
        if(parent.getId()==R.id.code1)
        {
            val1=parent.getSelectedItem().toString();
             out.setText("");
            if (!val1.equals("Select") && !val2.equals("Select") && !val1.equals(val2)) {
                TextView tv = (TextView) findViewById(ip);
                in.setText("");
                tv.setText("Enter Number (" + val1 + ")");
                ll.setVisibility(View.VISIBLE);            //visible input part to get input

            } else {
                // e1.setText("-1");
                ll.setVisibility(View.GONE);
            }
        }
        if(parent.getId()==R.id.code2)
        {
            val2=parent.getSelectedItem().toString();
            out.setText("");
            if (!val1.equals("Select") && !val2.equals("Select") && !val1.equals(val2)) {
                TextView tv = (TextView) findViewById(ip);
                in.setText("");
                tv.setText("Enter Code (" + val1 + ")");
                ll.setVisibility(View.VISIBLE);            //visible input part to get input
            } else {
                // e1.setText("-1");
                ll.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(getApplicationContext(),"nothing selected" ,Toast.LENGTH_LONG).show();
    }
    //Performing action onItemSelected and onNothing selected
    public void calculateCC(View view) {
        String regex = "[0-1]+";
        out = (TextView) findViewById(R.id.op);
        in = (EditText) findViewById(R.id.ccinput);
        String inp=in.getText().toString();
         int check=0;
        String ans = "";
        if (in.getText().toString().equals("")) {
            Toast.makeText(this, "Enter input number", Toast.LENGTH_SHORT).show();
        } else {
            int ip = Integer.parseInt(in.getText().toString());
            int num=ip;
            if (val1.equals("decimal")) {

                if (val2.equals("bcd(8421)")) {
                    ans = DECtoBCD(ip);
                    out.setText("BCD(8421) Equivalent = " + ans);
                } else if (val2.equals("2421")) {
                    ans = DECtoTFTO(ip);
                    out.setText("2421 Equivalent = " + ans);
                } else if (val2.equals("excess-3")) {
                    ans = DECtoE3(ip);
                    out.setText("excess-3 Equivalent = " + ans);
                } else if (val2.equals("84-2-1")) {
                    ans = DECtoEFMM(ip);
                    out.setText("84-2-1 Equivalent = " + ans);
                } else {

                }
            } else if (val1.equals("bcd(8421)")) {

                if(in.getText().toString().length()<4)
                    Toast.makeText(this, "Enter atleast 4-bit BCD code", Toast.LENGTH_SHORT).show();
                else if(!(in.getText().toString().matches(regex)) || !(((in.getText().toString().length())%4)==0))
                    Toast.makeText(this, "Enter VALID BCD code", Toast.LENGTH_SHORT).show();
                //else if(inp.contains("1010")||inp.contains("1011")||inp.contains("1100")||inp.contains("1101")||inp.contains("1110")||inp.contains("1111"))
                  //  Toast.makeText(this, "BCD code contains unused bit combinations", Toast.LENGTH_SHORT).show();
                else {

                    while (num != 0) {
                        t = num % 10000;
                        tans = Integer.toString(t);
                        while (tans.length() < 4)
                            tans = "0" + tans;
                        if (tans.equals("1010") || tans.equals("1011") || tans.equals("1100") || tans.equals("1101") || tans.equals("1110") || tans.equals("1111")) {
                            check = 1;
                            break;
                        }
                        num /= 10000;
                    }
                    if (check==0) {
                        if (val2.equals("decimal")) {
                            ans = BCDtoDEC(ip);
                            out.setText("decimal Equivalent = " + ans);
                        } else if (val2.equals("excess-3")) {
                            ans = BCDtoE3(ip);
                            out.setText("excess-3 Equivalent = " + ans);
                        } else if (val2.equals("2421")) {
                            ans = BCDtoTFTO(ip);
                            out.setText("2421 Equivalent = " + ans);
                        } else if (val2.equals("84-2-1")) {
                            ans = BCDtoEFMM(ip);
                            out.setText("84-2-1 Equivalent = " + ans);
                        } else {

                        }
                    }
                    else
                    {
                        Toast.makeText(this, "BCD code contains unused bit combinations", Toast.LENGTH_SHORT).show();
                    }
                }
            } else if (val1.equals("2421")) {

                if(in.getText().toString().length()<4)
                    Toast.makeText(this, "Enter atleast 4-bit 2421 code", Toast.LENGTH_SHORT).show();
                else if(!(in.getText().toString().matches(regex)) || !(((in.getText().toString().length())%4)==0))
                    Toast.makeText(this, "Enter VALID 2421 code", Toast.LENGTH_SHORT).show();
              //  else if(inp.contains("0101")||inp.contains("0110")||inp.contains("0111")||inp.contains("1000")||inp.contains("1001")||inp.contains("1010"))
                  //  Toast.makeText(this, "2421 code contains unused bit combinations", Toast.LENGTH_SHORT).show();
                else {

                    while (num != 0) {
                            t = num % 10000;
                            tans = Integer.toString(t);
                            while (tans.length() < 4)
                                tans = "0" + tans;
                            if (tans.equals("0101") || tans.equals("0110") || tans.equals("0111") || tans.equals("1000") || tans.equals("1001") || tans.equals("1010")) {
                                check = 1;
                                break;
                            }
                            num /= 10000;
                    }
                    if(check==0) {
                        if (val2.equals("decimal")) {
                            ans = TFTOtoDEC(ip);
                            out.setText("decimal Equivalent = " + ans);
                        } else if (val2.equals("excess-3")) {
                            ans = TFTOtoE3(ip);
                            out.setText("excess-3 Equivalent = " + ans);
                        } else if (val2.equals("bcd(8421)")) {
                            ans = TFTOtoBCD(ip);
                            out.setText("BCD(8421) Equivalent = " + ans);
                        } else if (val2.equals("84-2-1")) {
                            ans = TFTOtoEFMM(ip);
                            out.setText("84-2-1 Equivalent = " + ans);
                        } else {

                        }
                    }
                    else
                    {
                        Toast.makeText(this, "2421 code contains unused bit combinations", Toast.LENGTH_SHORT).show();
                    }

                }
            } else if (val1.equals("excess-3")) {

                if(in.getText().toString().length()<4)
                    Toast.makeText(this, "Enter atleast 4-bit ecxess-3 code", Toast.LENGTH_SHORT).show();
                else if(!(in.getText().toString().matches(regex)) || !(((in.getText().toString().length())%4)==0))
                    Toast.makeText(this, "Enter VALID ecxess-3 code", Toast.LENGTH_SHORT).show();
               // else if(inp.contains("0000")||inp.contains("0001")||inp.contains("0010")||inp.contains("1101")||inp.contains("1110")||inp.contains("1111"))
                   // Toast.makeText(this, "ecxess-3 code contains unused bit combinations", Toast.LENGTH_SHORT).show();
                else {
                    if(num==0)
                        check=1;
                    else {
                        while (num != 0) {
                            t = num % 10000;
                            tans = Integer.toString(t);
                            while (tans.length() < 4)
                                tans = "0" + tans;
                            if (tans.equals("0000") || tans.equals("0001") || tans.equals("0010") || tans.equals("1101") || tans.equals("1110") || tans.equals("1111")) {
                                check = 1;
                                break;
                            }
                            num /= 10000;
                        }
                    }
                    if(check==0) {
                        if (val2.equals("decimal")) {
                            ans = E3toDEC(ip);
                            out.setText("decimal Equivalent = " + ans);
                        } else if (val2.equals("2421")) {
                            ans = E3toTFTO(ip);
                            out.setText("2421 Equivalent = " + ans);
                        } else if (val2.equals("bcd(8421)")) {
                            ans = E3toBCD(ip);
                            out.setText("BCD(8421) Equivalent = " + ans);
                        } else if (val2.equals("84-2-1")) {
                            ans = E3toEFMM(ip);
                            out.setText("84-2-1 Equivalent = " + ans);
                        } else {

                        }
                    }
                    else
                    {
                        Toast.makeText(this, "ecxess-3 code contains unused bit combinations", Toast.LENGTH_SHORT).show();
                    }
                }
            } else if (val1.equals("84-2-1")) {

                if(in.getText().toString().length()<4)
                    Toast.makeText(this, "Enter atleast 4-bit 84-2-1 code", Toast.LENGTH_SHORT).show();
                else if(!(in.getText().toString().matches(regex)) || !(((in.getText().toString().length())%4)==0))
                    Toast.makeText(this, "Enter VALID 84-2-1 code", Toast.LENGTH_SHORT).show();
                //else if(inp.contains("0001")||inp.contains("0010")||inp.contains("0011")||inp.contains("1100")||inp.contains("1101")||inp.contains("1110"))
                  //  Toast.makeText(this, "84-2-1 code contains unused bit combinations", Toast.LENGTH_SHORT).show();
                else {

                    while (num != 0) {
                        t = num % 10000;
                        tans = Integer.toString(t);
                        while (tans.length() < 4)
                            tans = "0" + tans;
                        if (tans.equals("0001") || tans.equals("0010") || tans.equals("0011") || tans.equals("1100") || tans.equals("1101") || tans.equals("1110")) {
                            check = 1;
                            break;
                        }
                        num /= 10000;
                    }
                    if(check==0) {
                        if (val2.equals("decimal")) {
                            ans = EFMMtoDEC(ip);
                            out.setText("decimal Equivalent = " + ans);
                        } else if (val2.equals("2421")) {
                            ans = EFMMtoTFTO(ip);
                            out.setText("2421 Equivalent = " + ans);
                        } else if (val2.equals("bcd(8421)")) {
                            ans = EFMMtoBCD(ip);
                            out.setText("BCD(8421) Equivalent = " + ans);
                        } else if (val2.equals("excess-3")) {
                            ans = EFMMtoE3(ip);
                            out.setText("excess-3 Equivalent = " + ans);
                        } else {

                        }
                    }
                    else
                    {
                        Toast.makeText(this, "84-2-1 code contains unused bit combinations", Toast.LENGTH_SHORT).show();
                    }
                }
            } else {

            }
        }

    }


        //start Conversion here function

    public String DECtoBCD(int num) {
        answer = "";
        tans = "";
        if(num==0)
            answer="0000";
        else {
            while (num != 0) {
                t = num % 10;
                tans = Integer.toBinaryString(t);
                while (tans.length() < 4) {
                    tans = "0" + tans;
                }
                answer = tans + answer;
                num /= 10;
            }
        }
        return answer;
    }

    public String BCDtoDEC(int num) {
        answer = "";
        tans = "";
        if(num==0000)
            answer="0";
        else {
            while (num != 0) {
                t = num % 10000;
                tans = Integer.toString(Integer.parseInt(Integer.toString(t), 2));
                answer = tans + answer;
                num /= 10000;
            }
        }
        return answer;
    }

    public String DECtoTFTO(int num) {
        answer1 = "";
        tans = "";
        if(num==0)
            answer1="0000";
        else {
            while (num != 0) {
                t = num % 10;
                if(t==0)
                    answer1="0000"+answer1;
                else if (t <= 4 && t > 0) {
                    answer1 = (DECtoBCD(t)) + answer1;
                } else if (t > 4 && t < 9) {
                    answer1 = oneC(DECtoBCD(4 - (t - 4 - 1))) + answer1;

                } else {
                    answer1 = "1111" + answer1;
                }
                num /= 10;
            }
        }
        return answer1;
    }

    public String TFTOtoDEC(int num) {
        answer = "";
        tans = "";
        int temp;
        //String s="";
        if(num==0000)
            answer="0";
        else {
            while (num != 0) {
                t = num % 10000;
                tans = Integer.toString(t);
                while (tans.length() < 4) {
                    tans = "0" + tans;
                }
                //temp =(((int)(tans.charAt(0)))*2)+(((int)(tans.charAt(1)))*4)+(((int)(tans.charAt(2)))*2)+(((int)(tans.charAt(3)))*1);
                temp = ((Character.getNumericValue(tans.charAt(0))) * 2) + ((Character.getNumericValue(tans.charAt(1))) * 4) + ((Character.getNumericValue(tans.charAt(2))) * 2) + ((Character.getNumericValue(tans.charAt(3))) * 1);
                answer = Integer.toString(temp) + answer;
                num /= 10000;
            }
        }
        return answer;
    }

    public String BCDtoE3(int num) {
        answer = "";
        tans = "";
        if(num==0000)
            answer="0011";
        else {
            while (num != 0) {
                t = num % 10000;
                tans = Integer.toBinaryString((Integer.parseInt(Integer.toString(t), 2) + Integer.parseInt("0011", 2)));
                while (tans.length() < 4) {
                    tans = "0" + tans;
                }
                answer = tans + answer;
                num /= 10000;
            }
        }
        return answer;
    }

    public String DECtoE3(int num) {             //check again
        answer1 = "";
        tans = "";
        if(num==0)
            answer1="0011";
        else{
        while (num != 0) {
            t = num % 10;
            tans = BCDtoE3(Integer.parseInt(DECtoBCD(t)));
            num /= 10;
            answer1 = tans + answer1;
        }
        }

        return answer1;
    }

    public String TFTOtoE3(int num) {
        answer1 = "";
        tans = "";
        tans = TFTOtoDEC(num);
        answer1 = DECtoE3(Integer.parseInt(tans));
        return answer1;
    }

    public String TFTOtoBCD(int num) {
        answer1 = "";
        tans = "";
        tans = TFTOtoDEC(num);
        answer1 = DECtoBCD(Integer.parseInt(tans));
        return answer1;
    }

    public String BCDtoTFTO(int num) {
        answer1 = "";
        tans = "";
        tans = BCDtoDEC(num);
        answer1 = DECtoTFTO(Integer.parseInt(tans));
        return answer1;
    }


    public String DECtoEFMM(int num) {
        answer1 = "";
        tans = "";
        if(num==0)
            answer1="0000";
        else {
            while (num != 0) {
                t = num % 10;
                if (t == 0)
                    answer1 = "0000" + answer1;
                else if (t <= 4 && t > 0) {
                    answer1 = (DECtoBCD(8 - t)) + answer1;
                } else if (t > 4 && t < 9) {
                    answer1 = oneC(DECtoBCD(t - 1)) + answer1;

                } else {
                    answer1 = "1111" + answer1;
                }
                num /= 10;
            }
        }
        return answer1;
    }

    public String BCDtoEFMM(int num) {
        answer1 = "";
        tans = "";
        tans = BCDtoDEC(num);
        answer1 = DECtoEFMM(Integer.parseInt(tans));
        return answer1;
    }

    public String TFTOtoEFMM(int num) {
        answer1 = "";
        tans = "";
        tans = TFTOtoDEC(num);
        answer1 = DECtoEFMM(Integer.parseInt(tans));
        return answer1;
    }

    public String E3toBCD(int num) {
        answer = "";
        tans = "";

        while (num != 0) {
            t = num % 10000;
            tans = Integer.toBinaryString((Integer.parseInt(Integer.toString(t), 2) - Integer.parseInt("0011", 2)));
            while (tans.length() < 4) {
                tans = "0" + tans;
            }
            answer = tans + answer;
            num /= 10000;
        }
        return answer;
    }

    public String E3toDEC(int num) {
        answer1 = "";
        tans = "";

        answer1 = BCDtoDEC(Integer.parseInt(E3toBCD(num)));
        return answer1;
    }

    public String E3toTFTO(int num) {
        answer1 = "";
        tans = "";

        answer1 = BCDtoTFTO(Integer.parseInt(E3toBCD(num)));
        return answer1;
    }

    public String E3toEFMM(int num) {
        answer1 = "";
        tans = "";

        answer1 = BCDtoEFMM(Integer.parseInt(E3toBCD(num)));
        return answer1;
    }

    public String EFMMtoDEC(int num) {
        answer = "";
        tans = "";
        int temp;
        //String s="";
        if(num==0000)
            answer="0";
        else {
            while (num != 0) {
                t = num % 10000;
                tans = Integer.toString(t);
                while (tans.length() < 4) {
                    tans = "0" + tans;
                }
                //temp =(((int)(tans.charAt(0)))*2)+(((int)(tans.charAt(1)))*4)+(((int)(tans.charAt(2)))*2)+(((int)(tans.charAt(3)))*1);
                temp = ((Character.getNumericValue(tans.charAt(0))) * 8) + ((Character.getNumericValue(tans.charAt(1))) * 4) + ((Character.getNumericValue(tans.charAt(2))) * (-2)) + ((Character.getNumericValue(tans.charAt(3))) * (-1));
                answer = Integer.toString(temp) + answer;
                num /= 10000;
            }
        }
        return answer;
    }

    public String EFMMtoTFTO(int num) {
        answer1 = "";
        tans = "";

        answer1 = DECtoTFTO(Integer.parseInt(EFMMtoDEC(num)));
        return answer1;
    }

    public String EFMMtoBCD(int num) {
        answer1 = "";
        tans = "";

        answer1 = DECtoBCD(Integer.parseInt(EFMMtoDEC(num)));
        return answer1;
    }

    public String EFMMtoE3(int num) {
        answer1 = "";
        tans = "";

        answer1 = DECtoE3(Integer.parseInt(EFMMtoDEC(num)));
        return answer1;
    }


    public String oneC(String bin) {
        int n = bin.length();
        int i;

        String ones;
        ones = "";

        //  for ones complement flip every bit
        for (i = 0; i < n; i++) {
            if (bin.charAt(i) == '0')
                ones += "1";
            else
                ones += "0";
        }

        return ones;

    }



}

