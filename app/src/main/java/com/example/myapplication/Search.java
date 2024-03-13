package com.example.myapplication;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.HashMap;
import java.util.Map;

public class Search extends AppCompatActivity {
    private SearchView searchView;
    private LinearLayout carContainer;
    private Map<String, Integer> carImages;
    private Map<String, String> carDescriptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        Intent intent = getIntent();
        if (intent != null) {
            String carId = intent.getStringExtra("carId");
            if (carId != null && carContainer != null) {
                filterCars(carId);
            }
        }


        searchView = findViewById(R.id.searchView);
        carContainer = findViewById(R.id.carContainer);

        // Initialize car images
        carImages = new HashMap<>();
        carImages.put("lamborghini", R.drawable.lamborghini);
        carImages.put("cisitalia", R.drawable.cisitalia);
        carImages.put("honda", R.drawable.honda);
        carImages.put("morris", R.drawable.morris);
        carImages.put("tesla", R.drawable.tesla);
        carImages.put("elfin", R.drawable.elfin);
        carImages.put("ford", R.drawable.ford);
        carImages.put("bmw", R.drawable.bmw);
        carImages.put("ferrari", R.drawable.ferrari);
        carImages.put("bmw_m", R.drawable.bmw_m);
        carImages.put("dodge", R.drawable.dodge);
        carImages.put("mazda", R.drawable.mazda);
        carImages.put("hyundai", R.drawable.hyundai);
        carImages.put("marcos", R.drawable.marcos);
        carImages.put("kaiser", R.drawable.kaiser);
        carImages.put("shacman", R.drawable.shacman);
        carImages.put("porsche", R.drawable.porsche);
        carImages.put("subaru", R.drawable.subaru);
        carImages.put("innocenti", R.drawable.innocenti);
        carImages.put("cadillac", R.drawable.cadillac);
        carImages.put("duesenberg", R.drawable.duesenberg);
        // Add more car images...

        // Initialize car descriptions
        carDescriptions = new HashMap<>();
        carDescriptions.put("lamborghini", "The Lamborghini is one of the fastest cars in the world.");
        carDescriptions.put("cisitalia", "Cisitalia Description");
        carDescriptions.put("honda", "honda Description");
        carDescriptions.put("morris", "morris Description");
        carDescriptions.put("tesla", "tesla Description");
        carDescriptions.put("elfin", "elfin Description");
        carDescriptions.put("ford", "ford Description");
        carDescriptions.put("bmw", "bmw Description");
        carDescriptions.put("dodge", "dodge Description");
        carDescriptions.put("mazda", "mazda Description");
        carDescriptions.put("hyundai", "hyundai Description");
        carDescriptions.put("marcos", "marcos Description");
        carDescriptions.put("kaiser", "kaiser Description");
        carDescriptions.put("shacman", "shacman Description");
        carDescriptions.put("subaru", "subaru Description");
        carDescriptions.put("innocenti", "innocenti Description");
        carDescriptions.put("cadillac", "cadillac Description");
        carDescriptions.put("duesenberg", "duesenberg Description");

        // Add more car descriptions...

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterCars(newText);
                return true;
            }
        });

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.black)));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.black)); // Use your color resource here
        }

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.bottom_search);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.bottom_home) {
                startActivity(new Intent(getApplicationContext(), Main.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            } else if (item.getItemId() == R.id.bottom_settings) {
                startActivity(new Intent(getApplicationContext(), settings.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            } else if (item.getItemId() == R.id.bottom_profile) {
                startActivity(new Intent(getApplicationContext(), profile.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            }
            return false;
        });

        // Add click listeners to each card
        for (String carId : carImages.keySet()) {
            View cardView = carContainer.findViewWithTag(carId);
            if (cardView != null) {
                cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showCarDetails((String) v.getTag());
                    }
                });
            }
        }
    }

    private void filterCars(String query) {
        carContainer.removeAllViews(); // Clear existing cards
        for (String carId : carImages.keySet()) {
            if (carId.toLowerCase().contains(query.toLowerCase())) {
                // Add card to layout
                View cardView = getLayoutInflater().inflate(R.layout.card_layout, null);
                ImageView imageView = cardView.findViewById(R.id.imageView);
                TextView nameTextView = cardView.findViewById(R.id.nameTextView);
                TextView descriptionTextView = cardView.findViewById(R.id.descriptionTextView);
                imageView.setImageResource(carImages.get(carId));

                // Set the car name
                nameTextView.setText(carId.replace("_", " ").toUpperCase());

                // Set the car description
                String description = carDescriptions.get(carId);
                if (description != null) {
                    descriptionTextView.setText(description);
                }

                cardView.setTag(carId);
                cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showCarDetails((String) v.getTag());
                    }
                });
                carContainer.addView(cardView);
            }
        }
    }





    private void showCarDetails(String carId) {
        // Start activity to show details for the selected car
        Intent intent = new Intent(Search.this, CarDetailsActivity.class);
        intent.putExtra("carId", carId);
        startActivity(intent);
    }
}
