package com.example.styleomegafashionstore;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.styleomegafashionstore.model.Products;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class ProductDetailsActivity extends AppCompatActivity {

   // private FloatingActionButton addToCart;
    private ImageView productImage;
    private ElegantNumberButton numberButton;
    private TextView productPrice, productDescription, productName;
    private String productID=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        productID=getIntent().getStringExtra("pid");

        //addToCart=(FloatingActionButton)findViewById(R.id.add_product_to_cart);
        numberButton=(ElegantNumberButton) findViewById(R.id.number_button);
        productImage=(ImageView) findViewById(R.id.product_image_details);
        productDescription=(TextView) findViewById(R.id.product_description_details);
        productPrice=(TextView) findViewById(R.id.product_price_details);
        productName=(TextView) findViewById(R.id.product_name_details);

        getProductDetails(productID);

    }

    private void getProductDetails(final String productID) {

        DatabaseReference productsReference = FirebaseDatabase.getInstance().getReference().child("Products");

        productsReference.child(productID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){

                    Products products=dataSnapshot.getValue(Products.class);

                    productName.setText(products.getPname());
                    productDescription.setText(products.getPdescription());
                    productPrice.setText(products.getPprice());
                    Picasso.get().load(products.getPimage()).into(productImage);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
