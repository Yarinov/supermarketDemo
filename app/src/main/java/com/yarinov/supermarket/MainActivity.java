package com.yarinov.supermarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton bakeSection, cleanSection, candySection, fishSection,
            breadsSection, alcoholSection, fruitsAndVegetablesSection, dairySection, meatSection;

    private ImageView adminLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bakeSection = findViewById(R.id.bakeSection);
        cleanSection = findViewById(R.id.cleanSection);
        candySection = findViewById(R.id.candySection);
        fishSection = findViewById(R.id.fishSection);
        breadsSection = findViewById(R.id.breadsSection);
        alcoholSection = findViewById(R.id.alcoholSection);
        fruitsAndVegetablesSection = findViewById(R.id.fruitsAndVegetablesSection);
        dairySection = findViewById(R.id.dairySection);
        meatSection = findViewById(R.id.meatSection);

        adminLoginButton = findViewById(R.id.adminLoginButton);


        adminLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AdminActivity.class));
            }
        });


    }

    //On category press direct to the item list of that category
    public void buttonPress(View v) {
        switch (v.getId()) {
            case R.id.meatSection:
               openCategory(3);
                break;
            case R.id.dairySection:
                openCategory(2);
                break;
            case R.id.fruitsAndVegetablesSection:
                openCategory(1);
                break;
            case R.id.alcoholSection:
                openCategory(6);
                break;
            case R.id.breadsSection:
                openCategory(5);
                break;
            case R.id.fishSection:
                openCategory(4);
                break;
            case R.id.candySection:
                openCategory(9);
                break;
            case R.id.cleanSection:
                openCategory(8);
                break;
            case R.id.bakeSection:
                openCategory(7);
                break;
        }
    }

    private void openCategory(int i) {

        Intent moveToCategoryIntent = new Intent(MainActivity.this, CategoryActivity.class);
        moveToCategoryIntent.putExtra("categoryOrder", i);
        startActivity(moveToCategoryIntent);

    }
}
