package com.example.farmersmarket;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.farmersmarket.Product;

import java.util.List;
import java.util.function.Consumer;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    private List<Product> cartItems;
    private Consumer<Product> onRemoveProduct;

    public CartAdapter(List<Product> cartItems, Consumer<Product> onRemoveProduct) {
        this.cartItems = cartItems;
        this.onRemoveProduct = onRemoveProduct;
    }

    public void setCartItems(List<Product> newCartItems) {
        cartItems = newCartItems;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cart_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Product product = cartItems.get(position);
        holder.productName.setText(product.getName());
        holder.productPrice.setText(String.format("$%.2f", product.getPrice()));
        holder.removeFromCartButton.setOnClickListener(view -> onRemoveProduct.accept(product));
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView productName, productPrice;
        public Button removeFromCartButton;

        public ViewHolder(View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.productName);
            productPrice = itemView.findViewById(R.id.productPrice);
            removeFromCartButton = itemView.findViewById(R.id.removeFromCartButton);
        }
    }
}
