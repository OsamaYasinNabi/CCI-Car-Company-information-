package com.example.myapplication;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
public class CarDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_details);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.black)));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.black)); // Use your color resource here
        }

        Intent intent = getIntent();
        if (intent != null) {
            String carId = intent.getStringExtra("carId");
            // Use the carId as needed
            if (carId != null) {
                ImageView carImage = findViewById(R.id.carImage);
                TextView carName = findViewById(R.id.carName);
                TextView carDescription = findViewById(R.id.carDescription);

                // Set car image, name, and description
                int imageResId = getResources().getIdentifier(carId, "drawable", getPackageName());
                carImage.setImageResource(imageResId);
                carName.setText(carId.replace("_", " ").toUpperCase());

                // Set specific description for the selected car
                String specificDescription = getSpecificDescription(carId);
                carDescription.setText(specificDescription);
            }
        }
    }

    // Method to get specific description for the selected car
    private String getSpecificDescription(String carId) {
        switch (carId) {
            case "lamborghini":
                return "The Lamborghini is one of the fastest cars in the world.";
            case "cisitalia":
                return "Cisitalia Description";


            // Add more cases for other cars...
            default:
                return "";
        }
    }
}
