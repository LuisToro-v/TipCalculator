package edu.qc.seclass.tipcalculator;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText checkAmount;
    private EditText partySize;
    private Button computeButton;
    //Tips per person
    private EditText fifteenPercentTip;
    private EditText twentyPercentTip;
    private EditText twentyfivePercentTip;

    //Tip + Total per person
    private EditText fifteenPercentTotal;
    private EditText twentyPercentTotal;
    private EditText twentyfivePercentTotal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkAmount = findViewById(R.id.checkAmountValue);
        partySize = findViewById(R.id.partySizeValue);
        computeButton = findViewById(R.id.buttonCompute);

        fifteenPercentTip = findViewById(R.id.fifteenPercentTipValue);
        fifteenPercentTotal = findViewById(R.id.fifteenPercentTotalValue);

        twentyPercentTip = findViewById(R.id.twentyPercentTipValue);
        twentyPercentTotal = findViewById(R.id.twentyPercentTotalValue);

        twentyfivePercentTip = findViewById(R.id.twentyfivePercentTipValue);
        twentyfivePercentTotal = findViewById(R.id.twentyfivePercentTotalValue);

        computeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAmount.onEditorAction(EditorInfo.IME_ACTION_DONE);
                partySize.onEditorAction(EditorInfo.IME_ACTION_DONE);
                compute();
            }
        });
    }

    public void compute(){
        //Convert Views into String
        String checkAmountStr=checkAmount.getText().toString();
        String partSizeStr = partySize.getText().toString();
        //String str = checkAmountStr;
        if(checkAmountStr.length() == 0 || partSizeStr.length() == 0) {
            Toast toast = Toast.makeText(getApplicationContext(),"Empty or incorrect value(s)", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.BOTTOM, 0, 200);
            toast.show();
        }

        else {
            //Convert Strings into Numbers
            double check_amount = Double.parseDouble(checkAmountStr);
            int party_size = Integer.parseInt(partSizeStr);

            // tipPerPerson:Value each person must pay
            int tipPerPerson15 = (int) ((Math.ceil(check_amount * 0.15 / party_size)));
            int tipPerPerson20 = (int) ((Math.ceil(check_amount * 0.2/ party_size)));
            int tipPerPerson25 = (int) ((Math.ceil(check_amount * 0.25/ party_size)));
            //tipPerPersonStr: String version of tipPerPerson @ p percent
            String tipPerPersonStr15 = String.valueOf(tipPerPerson15);
            String tipPerPersonStr20 = String.valueOf(tipPerPerson20);
            String tipPerPersonStr25 = String.valueOf(tipPerPerson25);
            // Changes editView to be the tip amount a single person must pay @ p percent
            fifteenPercentTip.setText(tipPerPersonStr15);
            twentyPercentTip.setText(tipPerPersonStr20);
            twentyfivePercentTip.setText(tipPerPersonStr25);

            // perPersonCheckAmount: Amount each person must pay before tip
            Double perPersonCheckAmount = check_amount / party_size; //Record this for later use

            //totalPerPerson: Amount each person must pay after p percent has been included
            int totalPerPerson15 = (int) (Math.ceil(perPersonCheckAmount + tipPerPerson15));
            int totalPerPerson20 = (int) (Math.ceil(perPersonCheckAmount + tipPerPerson20));
            int totalPerPerson25 = (int) (Math.ceil(perPersonCheckAmount + tipPerPerson25));
            // fifteenPercentTotalValue: String of totalPerPerson15
            String percentTotalValue15 = String.valueOf(totalPerPerson15);
            String percentTotalValue20 = String.valueOf(totalPerPerson20);
            String percentTotalValue25= String.valueOf(totalPerPerson25);

            //Changes editView to be the total amount a single person must pay @ p Percent
            fifteenPercentTotal.setText(percentTotalValue15);
            twentyPercentTotal.setText(percentTotalValue20);
            twentyfivePercentTotal.setText(percentTotalValue25);
        }

    }



}