package com.example.farmersmarket;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        GridLayout gridLayout = view.findViewById(R.id.gridView);

        List<Product> products =  new ArrayList<>();
        products.add(new Product("Apple", R.drawable.apple, 1));
        products.add(new Product("Banana", R.drawable.banana, 2));
        products.add(new Product("Blueberry", R.drawable.blueberry, 3));
        products.add(new Product("Grape", R.drawable.grape, 4));
        products.add(new Product("Orange", R.drawable.orange, 5));
        products.add(new Product("Pears", R.drawable.pears, 6));
        products.add(new Product("Raspberries", R.drawable.raspberries, 7));
        products.add(new Product("Strawberry", R.drawable.strawberry, 8));
        products.add(new Product("Blackberry", R.drawable.blackberry, 9));


        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            Product product = products.get(i);
            FrameLayout frameLayout = (FrameLayout) gridLayout.getChildAt(i);
            ImageButton imageButton = frameLayout.findViewById(R.id.imageButton + i);
            TextView textViewName = frameLayout.findViewById(R.id.textViewName + i);
            TextView textViewPrice = frameLayout.findViewById(R.id.textViewPrice + i);

            if(imageButton != null && textViewName != null && textViewPrice != null) {
                imageButton.setImageResource(product.getImageResourceId());
                textViewName.setText(product.getName());
                textViewPrice.setText(String.format("$%.2f", product.getPrice()));

                imageButton.setTag(product);

                imageButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Product clickedProduct = (Product) v.getTag();
                        Intent intent = new Intent(getActivity(), ProductDetailActivity.class);
                        intent.putExtra("product", clickedProduct);
                        startActivity(intent);
                    }
                });
            }
        }

        return view;
    }
}