package com.yarinov.supermarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {

    private RecyclerView itemsRecyclerView;
    private ItemsAdapter itemsAdapter;

    private ArrayList<Item> items;

    String currentCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);


        itemsRecyclerView = findViewById(R.id.itemsRecyclerView);

        Bundle extras = getIntent().getExtras();

        int categoryNum = extras.getInt("categoryOrder");
        currentCategory = getCategoryString(categoryNum);

        items = new ArrayList<>();
        getCategoryItems();

        itemsAdapter = new ItemsAdapter(this, items);

        itemsRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
        itemsRecyclerView.setItemViewCacheSize(25);
        itemsRecyclerView.setAdapter(itemsAdapter);
        itemsRecyclerView.setHasFixedSize(true);



    }

    private void getCategoryItems() {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference().child("products/" + currentCategory);

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()){

                    for (DataSnapshot item :
                            dataSnapshot.getChildren()) {
                        String itemName = item.child("name").getValue().toString();
                        String itemPrice = item.child("price").getValue().toString();

                        Item itemToInsert = new Item(item.getKey(), itemName, itemPrice);
                        items.add(itemToInsert);
                    }

                    itemsAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private String getCategoryString(int categoryNum) {

        switch (categoryNum) {
            case 1:
                return "fruitsAndVegetablesSection";
            case 2:
                return "dairySection";
            case 3:
                return "meatSection";
            case 4:
                return "fishSection";
            case 5:
                return "breadsSection";
            case 6:
                return "alcoholSection";
            case 7:
               return "bakeSection";
            case 8:
                return "cleanSection";
            case 9:
                return "candySection";
        }

        return "";
    }

}
