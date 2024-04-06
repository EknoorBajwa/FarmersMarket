package com.example.farmersmarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class OrderConfirmationActivity extends AppCompatActivity {
    private TextView orderNumberTextView;
    private Button returnHomeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirmation);

        orderNumberTextView = findViewById(R.id.orderNumberTextView);
        returnHomeButton = findViewById(R.id.returnHomeButton);

        int orderNumber = new Random().nextInt(100) + 1;
        orderNumberTextView.setText(String.format("Your order number is #%04d", orderNumber));

        returnHomeButton.setOnClickListener(v -> {
            Intent intent = new Intent(OrderConfirmationActivity.this, Home.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });
    }
}
