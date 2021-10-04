package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView data;
    TextView output;
    String oldNumber="";
    String op="+";
    boolean newOp= true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        data=findViewById(R.id.data);
        output=findViewById(R.id.output);
    }

    public void numberEvent(View view) {
        if(newOp)
            data.setText("");
        newOp=false;
        String number= data.getText().toString();
        switch (view.getId()){
            case R.id.number0:
                number+="0";
                break;
            case R.id.number1:
                number+="1";
                break;
            case R.id.number2:
                number+="2";
                break;
            case R.id.number3:
                number+="3";
                break;
            case R.id.number4:
                number+="4";
                break;
            case R.id.number5:
                number+="5";
                break;
            case R.id.number6:
                number+="6";
                break;
            case R.id.number7:
                number+="7";
                break;
            case R.id.number8:
                number+="8";
                break;
            case R.id.number9:
                number+="9";
                break;
            case R.id.dot:
                number+=".";
                break;
            case R.id.addorsub:
                number="-"+number;
                break;
        }
        data.setText(number);

    }

    public void operatorEvent(View view) {
        newOp=true;
        oldNumber=data.getText().toString();
        switch (view.getId()){
            case R.id.division:
                op="/";
                break;
            case R.id.substraction:
                op="-";
                break;
            case R.id.multiply:
                op="*";
                break;
            case R.id.add:
                op="+";
                break;
            case R.id.percentage:
                op="%";
                break;

        }

    }

    public void equalEvent(View view) {
        String newNumber=data.getText().toString();
        double result=0.0;
        switch (op){
            case "+":
                result= Double.parseDouble(oldNumber)+Double.parseDouble(newNumber);
                break;
            case "-":
                result= Double.parseDouble(oldNumber)-Double.parseDouble(newNumber);
                break;
            case "*":
                result= Double.parseDouble(oldNumber)*Double.parseDouble(newNumber);
                break;
            case "/":
                result= Double.parseDouble(oldNumber)/Double.parseDouble(newNumber);
                break;

        }
        output.setText(result+"");

    }

    public void clearEvent(View view) {
        data.setText("0");
        newOp= true;
    }

    public void percentEvent(View view) {
        double no= Double.parseDouble(data.getText().toString())/100;
        data.setText(no+"");
        newOp=true;
    }
}