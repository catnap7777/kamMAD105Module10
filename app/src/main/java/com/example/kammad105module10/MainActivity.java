package com.example.kammad105module10;

//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//
//public class MainActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//    }
//}

//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    double billAmount;
    double tipPercent;
    double tipAmount;
    double totalBillAmount;
    String tipString;
    String formattedBillAmt;
    String formattedTipAmt;
    String formattedTotalBillAmt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //..get user input from the bill amount that the user entered on the screen and the
        //  tip amount selected in the drop box (spinner)
        final EditText billAmtTxt = findViewById(R.id.text_bill_amt);
        final Spinner tipPercentTxt = findViewById(R.id.spinner_tip);

        //..define a new button object associated with a listener so that the
        //   app knows when the button has been clicked
        Button calcTipButton = findViewById(R.id.btn_calc_tip);
        calcTipButton.setOnClickListener(new View.OnClickListener() {
            final TextView result = findViewById(R.id.text_result);
            @Override
            public void onClick(View view) {
                // get the bill amount and parse it into a double variable to do calculations
                billAmount = Double.parseDouble(billAmtTxt.getText().toString());

                //.. since the spinner drop down box has a string in it and not just a number,
                //    get the string and put into a string variable and then parse that string
                //    to get only the number portion out.  Since negative numbers for a tip are not
                //    allowed, these statements should work fine
                //
                //    below commented statement would have been ok if spinner drop down list only
                //    contained a number string that could be parsed to an integer
                //  -----> tipPercent = Integer.parseInt(tipPercentTxt.getSelectedItem().toString());

                tipString = tipPercentTxt.getSelectedItem().toString();
                tipPercent = Double.parseDouble(tipString.replaceAll("[^0-9]", ""));

                //.. do calcs
                tipAmount = billAmount * (tipPercent/100);
                totalBillAmount = billAmount + tipAmount;
                //.. use BigDecimal to eliminate an annoying rounding problem that I noticed
                BigDecimal roundTotalBillAmount = new BigDecimal(totalBillAmount).setScale(2, BigDecimal.ROUND_HALF_UP);

                //.. format numbers for output
                DecimalFormat currency = new DecimalFormat("$###,###.00");
                formattedBillAmt = currency.format(billAmount);
                formattedTipAmt = currency.format(tipAmount);
                formattedTotalBillAmt = currency.format(roundTotalBillAmount);

                //.. put result on screen
                result.setText("Bill Amount (" + formattedBillAmt + ") + " + "Tip Amount (" + formattedTipAmt + ") = Total Bill Amount (" + formattedTotalBillAmt + ")");

            }
        });

    }





}