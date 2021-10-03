package com.example.studentloancalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText loan_input;
    private EditText interest_input;
    private EditText years_input;
    private Button calculate_button;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addListenerOnButton();
    }

    protected void addListenerOnButton() {
        loan_input = findViewById(R.id.loan_input);
        interest_input = findViewById(R.id.interest_input);
        years_input = findViewById(R.id.years_input);
        calculate_button = findViewById(R.id.calculate_button);
        image = findViewById(R.id.cat);
        calculate_button.setOnClickListener(view -> {
            double p = Double.parseDouble(loan_input.getText().toString());
            double r = Double.parseDouble(interest_input.getText().toString()) / 100;
            double n = Double.parseDouble(years_input.getText().toString());
            double monthly = p * Math.pow(1 + r, n) / (12 * n);
            double total_interest = monthly * n * 12 - p;
            InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getApplicationWindowToken(),0);
            Toast.makeText(getApplicationContext(), "Monthly payment: $" + Math.round(monthly * 100) / 100.0 + "\nTotal interest paid: $" + Math.round(total_interest * 100) / 100.0, Toast.LENGTH_LONG).show();
            image.setVisibility(View.VISIBLE);
        });
    }
}