package com.example.kisna_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class OrderStatusActivity extends AppCompatActivity {
    public RecyclerView recyclerView2;
    public FarmerOrdersAdapter farmerOrdersAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_status);
        recyclerView2 = findViewById(R.id.order_rv);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String id = user.getUid();
        FirebaseRecyclerOptions<FarmerMyOrderModel> options =
                new FirebaseRecyclerOptions.Builder<FarmerMyOrderModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("User").child(id).child("My_Orders"), FarmerMyOrderModel.class)
                        .build();

        farmerOrdersAdapter=new FarmerOrdersAdapter(options,OrderStatusActivity.this);
        recyclerView2.setAdapter(farmerOrdersAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        farmerOrdersAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        farmerOrdersAdapter.stopListening();
    }

}