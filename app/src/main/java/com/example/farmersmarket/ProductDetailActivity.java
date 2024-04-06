package com.example.farmersmarket;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ProductDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        ImageView productImage = findViewById(R.id.productImage);
        TextView productName = findViewById(R.id.productName);
        TextView productPrice = findViewById(R.id.productPrice);
        Button addToCartButton = findViewById(R.id.addToCartButton);

        Product product = (Product) getIntent().getSerializableExtra("product");

        productImage.setImageResource(product.getImageResourceId());
        productName.setText(product.getName());
        productPrice.setText(String.format("$%.2f", product.getPrice()));

        addToCartButton.setOnClickListener(view -> {
            CartManager.getInstance().addProductToCart(product);
            finish(); // Finish the activity immediately after adding to cart
        });
    }
}
