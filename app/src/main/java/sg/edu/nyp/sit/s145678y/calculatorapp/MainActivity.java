package sg.edu.nyp.sit.s145678y.calculatorapp;

import android.graphics.Typeface;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Stack;


public class MainActivity extends AppCompatActivity {
    private Menu myMenu = null;
    private Button equal = null;
    private Button backspace = null;
    private TextView displayView = null;
    private TextView viewTextView = null;
    private String operand = "";
    private int counter = 0;
    private int checkCounter = 0;
    private double total = 0;
    private double number1 = 0;
    private boolean isEqual = false;

    private boolean isCalculated = false;
    private String allTheValues = "";
    String currentNumber = "";

    ArrayList<String> calculation = new ArrayList<String>();
    ArrayList<Double> totals = new ArrayList<Double>();

    private DecimalFormat df = new DecimalFormat("#.#####");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayView = (TextView) findViewById(R.id.displayView);
        equal = (Button) findViewById(R.id.btnEqual);
        viewTextView = (TextView) findViewById(R.id.viewTextView);
        backspace = (Button) findViewById(R.id.btnBackspace);

        Typeface iconFont = FontManager.getTypeface(getApplicationContext(), FontManager.ARIAL);
        FontManager.markAsIconContainer(findViewById(R.id.icons_container), iconFont);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    public void inputNumber(View v){
        if(isCalculated == true){
            displayView.setText("");
            counter = 0;
            checkCounter = 0;
            total = 0;
            currentNumber = "";
            operand = "";
            isCalculated = false;
        }

        if(v.getId() == R.id.btnDecimal) {

            displayView.setText(displayView.getText() + ".");
            currentNumber = currentNumber + ".";
        }

        if(v.getId() == R.id.btn0) {

            displayView.setText(displayView.getText() + "0");
            currentNumber = currentNumber + "0";
        }
        if(v.getId() == R.id.btn1) {
            displayView.setText(displayView.getText() + "1");
            currentNumber = currentNumber + "1";
        }
        if(v.getId() == R.id.btn2) {
            displayView.setText(displayView.getText() + "2");
            currentNumber = currentNumber + "2";
        }
        if(v.getId() == R.id.btn3) {
            displayView.setText(displayView.getText() + "3");
            currentNumber = currentNumber + "3";
        }
        if(v.getId() == R.id.btn4) {
            displayView.setText(displayView.getText() + "4");
            currentNumber = currentNumber + "4";
        }
        if(v.getId() == R.id.btn5) {
            displayView.setText(displayView.getText() + "5");
            currentNumber = currentNumber + "5";
        }
        if(v.getId() == R.id.btn6) {
            displayView.setText(displayView.getText() + "6");
            currentNumber = currentNumber + "6";
        }
        if(v.getId() == R.id.btn7) {
            displayView.setText(displayView.getText() + "7");
            currentNumber = currentNumber + "7";
        }
        if(v.getId() == R.id.btn8) {
            displayView.setText(displayView.getText() + "8");
            currentNumber = currentNumber + "8";
        }
        if(v.getId() == R.id.btn9) {
            displayView.setText(displayView.getText() + "9");
            currentNumber = currentNumber + "9";
        }

        viewTextView.setText(currentNumber);
    }

    public void operands(View v){
        boolean check = false;


        if(isCalculated == true){
            displayView.setText(df.format(total).toString());
            isCalculated = false;
        }

        String displayText = displayView.getText().toString();

        if(displayText.length() < 1){
            check = true;
        }
        counter ++;



        if(v.getId() == R.id.btnDivide && check == false) {
            displayView.setText(displayView.getText() + "÷");
            checkOperand('÷');
            calculate();
            operand = "÷";
            currentNumber = "";
        }
        if(v.getId() == R.id.btnMultiply && check == false) {
            displayView.setText(displayView.getText() + "×");
            checkOperand('×');
            calculate();
            operand = "×";
            currentNumber = "";
        }
        if(v.getId() == R.id.btnMinus && check == false) {
            displayView.setText(displayView.getText() + "-");
            checkOperand('-');
            calculate();
            operand = "-";
            currentNumber = "";
        }
        if(v.getId() == R.id.btnPlus && check == false) {
            displayView.setText(displayView.getText() + "+");
            checkOperand('+');
            calculate();
            operand = "+";
            currentNumber = "";
        }
        if(v.getId() == R.id.btnEqual && check == false) {
            isEqual = true;
            calculate();
            operand = "";
            currentNumber = "";
            isEqual = false;
            isCalculated = true;

        }



    }

    public void checkOperand(char clickedOperand){
        String displayText = displayView.getText().toString();

        if(displayText.length() > 2){
            char temp = displayText.charAt(displayText.length() - 1);
            char temp2 = displayText.charAt(displayText.length() - 2);

            if ((temp == '+' || temp == '-' || temp == '×' || temp == '÷') && (temp2 == '+' || temp2 == '-' || temp2 == '×' || temp2 == '÷')) {
                operand="";
                displayView.setText(displayText.substring(0, displayText.length() - 2) + String.valueOf(clickedOperand));
            }
        }

    }
    public void backspace(View v){
        if(v.getId() == R.id.btnBackspace){


            String displayText = displayView.getText().toString();
            String viewText = viewTextView.getText().toString();
            char lastValue = 0;

            if(displayText.length() > 0 && viewText.length() > 0){
                char temp = displayText.charAt(displayText.length()-1);
                if(temp == '+' || temp == '-' || temp == '×' || temp == '÷'){
                    operand = "";
                }
                displayView.setText(displayText.substring(0, displayText.length()-1));
                lastValue = displayText.charAt(displayText.length()-1);
            }
            if(viewText.length() > 0 && ( (lastValue != '-') && (lastValue != '+') && (lastValue != '÷')&& (lastValue != '×')) && (displayText.length() > 0)){
                viewTextView.setText(viewText.substring(0, viewText.length()-1));
                currentNumber = viewText.substring(0, viewText.length()-1);

            }
            if(viewText.length() == 1){
                viewTextView.setText("0");
            }
        }
    }

    public void calculate(){

        if(checkCounter == 0) {
            number1 = Double.parseDouble(currentNumber);
            checkCounter++;
        }
        if(operand.equals("÷") && counter > 1){
            total = number1  /  Double.parseDouble(currentNumber);
            number1 = total;
        }
        if(operand.equals("+") && counter > 1){
            total = number1  +  Double.parseDouble(currentNumber);
            number1 = total;
        }
        if(operand.equals("-") && counter > 1){
            total = number1  -  Double.parseDouble(currentNumber);
            number1 = total;
        }
        if(operand.equals("×") && counter > 1){
            total = number1  *  Double.parseDouble(currentNumber);
            number1 = total;
        }
        if(counter > 1){
            viewTextView.setText("" + df.format(total));
        }

        if(counter > 1 && isEqual == true){
            calculation.add(displayView.getText().toString());
            displayView.setText("");
            viewTextView.setText("" + df.format(total));
            totals.add(total);

        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.about) {
            Intent intent = new Intent(this, About.class);
            startActivity(intent);
        }
        else if(id == R.id.memoryList){
            Intent intent = new Intent(this, MemoryList.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
