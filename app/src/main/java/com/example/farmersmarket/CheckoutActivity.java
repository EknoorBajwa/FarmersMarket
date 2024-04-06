package com.example.farmersmarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CheckoutActivity extends AppCompatActivity {

    private EditText cardNumberEditText;
    private EditText expiryDateEditText;
    private EditText cvvEditText;
    private EditText addressEditText;
    private EditText cityEditText;
    private EditText postalCodeEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        double totalPrice = getIntent().getDoubleExtra("total_price", 0.0);
        TextView totalTextView = findViewById(R.id.totalTextView);
        totalTextView.setText(String.format("$%.2f", totalPrice));

        cardNumberEditText = findViewById(R.id.cardNumberEditText);
        expiryDateEditText = findViewById(R.id.expiryDateEditText);
        cvvEditText = findViewById(R.id.cvvEditText);
        addressEditText = findViewById(R.id.addressEditText);
        cityEditText = findViewById(R.id.cityEditText);
        postalCodeEditText = findViewById(R.id.postalCodeEditText);

        Button confirmPurchaseButton = findViewById(R.id.confirmPurchaseButton);
        confirmPurchaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateCardDetails()) {
                    Intent intent = new Intent(CheckoutActivity.this, OrderConfirmationActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(CheckoutActivity.this, "Please check your card details", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private boolean validateCardDetails() {
        String cardNumber = cardNumberEditText.getText().toString().trim();
        String expiryDate = expiryDateEditText.getText().toString().trim();
        String cvv = cvvEditText.getText().toString().trim();

        if (cardNumber.length() != 16) {
            cardNumberEditText.setError("Card number must be 16 digits");
            return false;
        }

        if (!expiryDate.matches("(0[1-9]|1[0-2])/[0-9]{2}")) {
            expiryDateEditText.setError("Invalid date format");
            return false;
        }

        if (cvv.length() != 3) {
            cvvEditText.setError("Invalid CVV");
            return false;
        }

        return true;
    }
}

