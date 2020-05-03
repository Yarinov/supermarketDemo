package com.yarinov.supermarket;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AdminActivity extends AppCompatActivity {

    private Button addProductButton;
    private LinearLayout chooseActionLayout, addProductLayout;

    private Spinner catSpinner;

    private EditText productNameEditText, productPriceEditText;

    private boolean inNewProductMood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        addProductButton = findViewById(R.id.addProductButton);

        chooseActionLayout = findViewById(R.id.chooseActionLayout);
        addProductLayout = findViewById(R.id.addProductLayout);

        productNameEditText = findViewById(R.id.productNameEditText);
        productPriceEditText = findViewById(R.id.productPriceEditText);

        catSpinner = findViewById(R.id.catSpinner);

        inNewProductMood = false;

        List<String> categoryList = new ArrayList<String>();
        categoryList.add("1");
        categoryList.add("2");
        categoryList.add("3");
        categoryList.add("4");
        categoryList.add("5");
        categoryList.add("6");
        categoryList.add("7");
        categoryList.add("8");
        categoryList.add("9");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, categoryList);
        dataAdapter.setDropDownViewResource(R.layout.spinner_item);
        catSpinner.setAdapter(dataAdapter);


        addProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveToNewProduct();
            }
        });


    }

    private void moveToNewProduct() {

        fadeOutAndInViews(chooseActionLayout, addProductLayout);
        inNewProductMood = true;
    }

    public void addNewProduct(View view) {

        String productName = productNameEditText.getText().toString();
        String productPrice = productPriceEditText.getText().toString();

        String itemUUID = UUID.randomUUID().toString();

        int catNum = catSpinner.getSelectedItemPosition();

        Item newItem = new Item(itemUUID, productName, productPrice);

        if (inNewProductMood && productName != "" && productPrice != "") {

            FirebaseDatabase.getInstance().getReference().
                    child("products/" + getCategory(catNum) + "/" + newItem.getUuid()).setValue(newItem);
        }
    }

    private String getCategory(int catNum) {

        switch (catNum+1) {
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

    private void fadeOutAndInViews(final View viewToFadeOut, final View viewToFadeIn) {

        viewToFadeOut.animate()
                .alpha(0.0f).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

                viewToFadeOut.setVisibility(View.GONE);

                viewToFadeIn.setVisibility(View.VISIBLE);
                viewToFadeIn.setAlpha(0.0f);

                viewToFadeIn.animate().alpha(1.0f).setListener(null);
            }
        });

    }
}
