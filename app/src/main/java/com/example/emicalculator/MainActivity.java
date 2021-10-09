package com.example.emicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Declaring variables
    EditText etName, etLoanAmount, etRate, etTenure;
    TextView tvName, tvLoanAmount, tvRate, tvTenure, tvResult;
    Button btnEmi, btnReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Binding so that XML resources are available in Java File
        etName = findViewById(R.id.etName);
        etLoanAmount = findViewById(R.id.etLoanAmount);
        etRate = findViewById(R.id.etRate);
        etTenure = findViewById(R.id.etTenure);
        tvName = findViewById(R.id.tvName);
        tvLoanAmount = findViewById(R.id.tvLoanAmount);
        tvRate = findViewById(R.id.tvRate);
        tvTenure = findViewById(R.id.tvTenure);
        btnEmi = findViewById(R.id.btnEmi);
        btnReset = findViewById(R.id.btnReset);
        tvResult = findViewById(R.id.tvResult);

        // Button Calculate EMI
        btnEmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Getting input from the user
                String name  = etName.getText().toString();
                String loan = etLoanAmount.getText().toString();
                String rate = etRate.getText().toString();
                String tenure = etTenure.getText().toString();

                // performing validations
                if (name.isEmpty()){
                    etName.setError("Invalid Name");
                    return;
                }
                if (loan.isEmpty()){
                    etLoanAmount.setError("Invalid Loan Amount");
                    return;
                }
                if (rate.isEmpty()){
                    etRate.setError("Invalid Rate");
                    return;
                }
                if (tenure.isEmpty()){
                    etTenure.setError("Invalid Tenure");
                    return;
                }

                // converting to proper data type
                float p = Float.parseFloat(loan);
                float r = Float.parseFloat(rate)/100;
                int t = Integer.parseInt(tenure);

                //calculating result
                double res = p*r*(Math.pow((1+r),t))/(Math.pow((1+r),t)-1);
                String msg = "Name = " + name
                        + "\nLoan Amount (₹) = " + p
                        + "\nRate = " + r + " %"
                        + "\nTenure = " + t + " years"
                        + "\nEMI (₹) = " + String.format("%.2f", res);

                //displaying result
                tvResult.setText(msg);
            }
        });

        // Button Reset --> Resets all the Fields
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etName.setText("");
                etLoanAmount.setText("");
                etRate.setText("");
                etTenure.setText("");
                tvResult.setText("");
            }
        });

    }
}