package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    TextView data;
    TextView output;
    String oldNumber="";
    String op="+";
    boolean newOp= true;
    Set<Character> oset = new HashSet<Character>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        data=findViewById(R.id.data);
        output=findViewById(R.id.output);
        oset.add('+'); oset.add('-'); oset.add('*'); oset.add('/'); oset.add('%');
        data.setMovementMethod(new ScrollingMovementMethod());
    }
    public void numberEvent(View view) {
        if(data.getText().toString().equals("0"))
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
            case R.id.brackets:
                char last = oldNumber.charAt(oldNumber.length()-1);
                if (last == '0' || oset.contains(last))
                    op="(";
                else
                    op=")";
                break;
        }
        if(data.getText().toString().equals("0"))
            data.setText(op);
        else
            data.setText(data.getText().toString()+op);

    }
    public static Double evaluate(String expression) {
        char[] tokens = expression.toCharArray();
        Stack<Double> values = new Stack<Double>();
        Stack<Character> ops = new Stack<Character>();
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i] == ' ')
                continue;
            if (tokens[i] >= '0' && tokens[i] <= '9'){
                StringBuffer sbuf = new StringBuffer();
                while (i < tokens.length && (tokens[i] == '.' || (tokens[i] >= '0' && tokens[i] <= '9')))
                    sbuf.append(tokens[i++]);
                values.push(Double.parseDouble(sbuf.toString()));
                i--;
            }
            else if (tokens[i] == '(')
                ops.push(tokens[i]);
            else if (tokens[i] == ')') {
                while (ops.peek() != '(')
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                ops.pop();
            }
            else if (tokens[i] == '+' || tokens[i] == '-' || tokens[i] == '*' || tokens[i] == '/') {
                while (!ops.empty() && hasPrecedence(tokens[i], ops.peek()))
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                ops.push(tokens[i]);
            }
        }
        while (!ops.empty())
            values.push(applyOp(ops.pop(), values.pop(), values.pop()));
        return values.pop();
    }

    public static boolean hasPrecedence(char op1, char op2)  {
        if (op2 == '(' || op2 == ')')
            return false;
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'))
            return false;
        else
            return true;
    }

    public static Double applyOp(char op, Double b, Double a)  {
        switch (op)            {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0)
                    throw new UnsupportedOperationException("Cannot divide by zero");
                return a / b;
        }
        return 0.0;
    }

    public void equalEvent(View view) {
        try {
            String newNumber=data.getText().toString();
            double result=0.0;
            result = Double.parseDouble(String.valueOf(evaluate(newNumber)));
            if((int)result==result){
                output.setText((int)result+"");
            }else{
                output.setText(result+"");
            }
        }catch (Exception e){
            Toast.makeText(this, "Invalid Operation!!", Toast.LENGTH_SHORT).show();
        }
    }

    public void clearEvent(View view) {
        data.setText("0");
        output.setText("0");
        newOp= true;
    }

    public void percentEvent(View view) {
        String curr = data.getText().toString();
        int i=curr.length();
        Double percent = 0.0;
        int j=0;
        while(i>0){
            if (oset.contains(curr.charAt(i-1)))
                break;
            percent = Character.getNumericValue(curr.charAt(i-1))*Math.pow(10,j++) + percent;
            i--;
        }
        percent = percent / 100;
        curr = curr.substring(0,i) + percent.toString();
        data.setText(curr);
        newOp=true;
    }

    public void backspaceEvent(View view) {
        String number= data.getText().toString();
        if(number.length()>0){
            number=number.substring(0,number.length()-1);
        }
        data.setText(number);
        if(number.equals(""))
            data.setText("0");
    }
}