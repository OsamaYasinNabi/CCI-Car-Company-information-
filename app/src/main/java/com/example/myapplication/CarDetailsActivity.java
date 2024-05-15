package com.example.myapplication;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import android.os.Handler;
import java.util.ArrayList;
import java.util.List;

public class CarDetailsActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private TextView carName;
    private TextView carDescription;
    private Handler handler;
    private Runnable runnable;
    private int delay = 3000; // 3 seconds delay
    private int currentPage = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_details);
        // bo goryny navbar color
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.black)));
        // bo goryny colory sarw navbary
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.black)); // Use your color resource here
        }



        // Initialize views
        ImageView carImage = findViewById(R.id.carImage);

        viewPager = findViewById(R.id.viewPager);
        carDescription = findViewById(R.id.carDescription);
        carName = findViewById(R.id.carName);

        // Get car ID from intent
        Intent intent = getIntent();
        if (intent != null) {
            String carId = intent.getStringExtra("carId");
            if (carId != null) {
                // Set car image, name, and description
                int imageResId = getResources().getIdentifier(carId, "drawable", getPackageName());
                carImage.setImageResource(imageResId);
                // Set car name
                carName.setText(carId.replace("_", " ").toUpperCase());

                // Set specific description for the selected car
                String specificDescription = getSpecificDescription(carId);
                carDescription.setText(specificDescription);

                // Set up ViewPager2 with a custom adapter
                CarImageAdapter adapter = new CarImageAdapter(getCarImages(carId));
                viewPager.setAdapter(adapter);
                // Auto-scroll the ViewPager2 every 3 seconds
                handler = new Handler();
                runnable = new Runnable() {
                    public void run() {
                        if (currentPage == adapter.getItemCount()) {
                            currentPage = 0;
                        }
                        viewPager.setCurrentItem(currentPage++, true);
                        handler.postDelayed(this, delay);
                    }
                };
                handler.postDelayed(runnable, delay);
            }
        }
    }

    // Method to get specific description for the selected car
    private String getSpecificDescription(String carId) {
        switch (carId) {
            case "lamborghini":
                return "The Lamborghini is one of the fastest cars in the world. It is an Italian luxury sports car manufacturer founded in 1963 by Ferruccio Lamborghini. The company is known for its sleek and powerful supercars, often featuring aggressive styling and high-performance engines.";
            case "cisitalia":
                return "Cisitalia was an Italian automotive manufacturer founded in Turin, Italy, in 1946 by Piero Dusio. The company is best known for its iconic 202 Gran Sport, a groundbreaking design that influenced many future sports car designs. Cisitalia's cars are renowned for their elegant styling and innovative engineering.";
            case "honda":
                return "Honda is a Japanese multinational conglomerate known for manufacturing automobiles, motorcycles, and power equipment. Founded in 1946 by Soichiro Honda and Takeo Fujisawa, Honda has grown to become one of the largest and most respected automobile manufacturers in the world. Honda is known for its reliable and fuel-efficient vehicles.";
            case "morris":
                return "Morris was a British motor vehicle manufacturer founded in 1913 by William Morris. The company produced a wide range of vehicles, including cars, trucks, and vans. Morris vehicles were known for their durability and affordability, making them popular choices for families and businesses.";
            case "tesla":
                return "Tesla is an American electric vehicle and clean energy company founded in 2003 by Martin Eberhard and Marc Tarpenning. The company is known for its electric cars, solar panels, and energy storage solutions. Tesla's vehicles are known for their long range, high performance, and innovative features.";
            case "elfin":
                return "Elfin is an Australian sports car manufacturer founded in 1957 by Garrie Cooper. The company is known for its lightweight and high-performance sports cars, which are designed for both road and track use. Elfin cars are highly sought after by collectors and enthusiasts for their racing pedigree and unique styling.";
            case "ford":
                return "Ford is an American multinational automaker founded by Henry Ford in 1903. The company is known for its trucks, cars, and SUVs, as well as its iconic Mustang sports car. Ford has been a leader in automotive innovation, introducing mass production techniques and pioneering new technologies.";
            case "bmw":
                return "BMW is a German multinational company which produces luxury vehicles and motorcycles. The company was founded in 1916 as a manufacturer of aircraft engines, which it produced from 1917 until 1918 and again from 1933 to 1945. BMW is known for its performance-oriented vehicles and innovative technology.";
            case "ferrari":
                return "Ferrari is an Italian luxury sports car manufacturer founded by Enzo Ferrari in 1939. The company is known for its high-performance cars, often featuring sleek designs and powerful engines. Ferrari has a long history of success in motorsport, particularly in Formula One racing.";
            case "bmw_m":
                return "BMW M GmbH (previously: BMW Motorsport GmbH) is a subsidiary of BMW AG that manufactures high-performance cars. Founded in 1972, BMW M is known for its sports cars and luxury vehicles. BMW M cars are renowned for their performance, handling, and precision engineering.";
            case "dodge":
                return "Dodge is an American brand of automobiles and a division of Stellantis. Founded as the Dodge Brothers Company in 1900 by Horace Elgin Dodge and John Francis Dodge, Dodge was one of the first American automobile manufacturers. Dodge is known for its trucks and muscle cars, such as the Challenger and Charger.";
            case "mazda":
                return "Mazda is a Japanese multinational automaker known for its stylish and sporty vehicles. Founded in 1920 as the Toyo Cork Kogyo Co., Ltd., Mazda has grown to become one of the world's largest automakers. Mazda is known for its innovative engine technology, including the rotary engine used in the RX series.";
            case "hyundai":
                return "Hyundai is a South Korean multinational automotive manufacturer founded in 1967. The company is known for its affordable and reliable vehicles, including cars, SUVs, and trucks. Hyundai is one of the largest automakers in the world and has a strong presence in markets around the globe.";
            case "marcos":
                return "Marcos was a British sports car manufacturer founded in 1959 by Jem Marsh and Frank Costin. The company is known for its lightweight and high-performance sports cars, which were produced in limited numbers. Marcos cars are highly sought after by collectors for their unique styling and racing heritage.";
            case "kaiser":
                return "Kaiser was an American automobile manufacturer founded by Henry J. Kaiser in 1945. The company produced cars, trucks, and jeeps, and was known for its innovative designs and engineering. Kaiser's cars were popular in the post-war era and are now prized by collectors for their historical significance.";
            case "shacman":
                return "Shacman is a Chinese commercial vehicle manufacturer founded in 1968. The company is known for its trucks and buses, which are used in industries such as construction, mining, and transportation. Shacman vehicles are known for their durability and reliability, making them popular choices for commercial use.";
            case "porsche":
                return "Porsche is a German automobile manufacturer specializing in high-performance sports cars, SUVs, and sedans. Founded in 1931 by Ferdinand Porsche, the company is known for its iconic 911 sports car, as well as its successful racing heritage. Porsche is considered one of the most prestigious automotive brands in the world.";
            case "subaru":
                return "Subaru is a Japanese automobile manufacturer known for its use of boxer engine layout and all-wheel drive vehicles. Founded in 1953, Subaru has become one of the most popular automakers in the world, known for its rugged and reliable vehicles.";
            case "innocenti":
                return "Innocenti was an Italian machinery works originally established by Ferdinando Innocenti in 1920. The company produced a variety of products, including scooters, motorcycles, and automobiles. Innocenti cars, such as the iconic Mini, are known for their compact size and stylish design.";
            case "cadillac":
                return "Cadillac is an American luxury automobile marque founded in 1902 by William Murphy, Lemuel Bowen, and Henry M. Leland. The company is known for its luxurious and innovative vehicles, often featuring cutting-edge technology and elegant styling. Cadillac is considered one of the premier luxury car brands in the world.";
            case "duesenberg":
                return "Duesenberg was an American luxury automobile brand founded in 1913 by brothers August and Frederick Duesenberg. The company was known for its high-performance and luxury cars, which were among the most expensive and exclusive vehicles of their time. Duesenberg cars are now highly prized by collectors for their historical significance and exquisite craftsmanship.";

            // Add more cases for other cars...
            default:
                return "";
        }
    }

    // Method to get list of car images for the selected car
    private List<Integer> getCarImages(String carId) {
        List<Integer> carImages = new ArrayList<>();
        // Add image resource IDs for the selected car
        switch (carId) {
            case "lamborghini":
                carImages.add(R.drawable.lamborghini1);
                carImages.add(R.drawable.lamborghini2);
                // Add more images for lamborghini
                break;
            case "cisitalia":
                carImages.add(R.drawable.elfin1);
                carImages.add(R.drawable.elfin2);
                // Add more images for cisitalia
                break;
            case "honda":
                carImages.add(R.drawable.honda1);
                carImages.add(R.drawable.honda2);
                // Add more images for honda
                break;
            case "morris":
                carImages.add(R.drawable.morris1);
                carImages.add(R.drawable.morris2);
                // Add more images for morris
                break;
            case "tesla":
                carImages.add(R.drawable.tesla1);
                carImages.add(R.drawable.tesla2);
                // Add more images for tesla
                break;
            case "elfin":
                carImages.add(R.drawable.elfin1);
                carImages.add(R.drawable.elfin2);
                // Add more images for elfin
                break;
            case "ford":
                carImages.add(R.drawable.ford1);
                carImages.add(R.drawable.ford2);
                // Add more images for ford
                break;
            case "bmw":
                carImages.add(R.drawable.bmw1);
                carImages.add(R.drawable.bmw2);
                // Add more images for bmw
                break;
            case "ferrari":
                carImages.add(R.drawable.ferrari1);
                carImages.add(R.drawable.ferrari2);
                // Add more images for ferrari
                break;
            case "bmw_m":
                carImages.add(R.drawable.bmw1);
                carImages.add(R.drawable.bmw2);
                // Add more images for bmw_m
                break;
            case "dodge":
                carImages.add(R.drawable.dodge1);
                carImages.add(R.drawable.dodge2);
                // Add more images for dodge
                break;
            case "mazda":
                carImages.add(R.drawable.mazda1);
                carImages.add(R.drawable.mazda2);
                // Add more images for mazda
                break;
            case "hyundai":
                carImages.add(R.drawable.hyundai1);
                carImages.add(R.drawable.hyundai2);
                // Add more images for hyundai
                break;
            case "marcos":
                carImages.add(R.drawable.marcos1);
                carImages.add(R.drawable.marcos1);
                // Add more images for marcos
                break;
            case "kaiser":
                carImages.add(R.drawable.marcos1);
                carImages.add(R.drawable.shacman2);
                // Add more images for kaiser
                break;
            case "shacman":
                carImages.add(R.drawable.shacman1);
                carImages.add(R.drawable.shacman2);
                // Add more images for shacman
                break;
            case "porsche":
                carImages.add(R.drawable.porsche1);
                carImages.add(R.drawable.porsche2);
                // Add more images for porsche
                break;
            case "subaru":
                carImages.add(R.drawable.subaru1);
                carImages.add(R.drawable.subaru2);
                // Add more images for subaru
                break;
            case "innocenti":
                carImages.add(R.drawable.innocenti1);
                carImages.add(R.drawable.innocenti2);
                // Add more images for innocenti
                break;
            case "cadillac":
                carImages.add(R.drawable.cadillac1);
                carImages.add(R.drawable.cadilac2);
                // Add more images for cadillac
                break;
            case "duesenberg":
                carImages.add(R.drawable.duesenberg1);
                carImages.add(R.drawable.duesenberg2);
                // Add more images for duesenberg
                break;
            // Add cases for other cars...
        }
        return carImages;
    }

    // Custom adapter for ViewPager2
    private class CarImageAdapter extends RecyclerView.Adapter<CarImageAdapter.CarImageViewHolder> {

        private List<Integer> images;

        CarImageAdapter(List<Integer> images) {
            this.images = images;
        }

        @NonNull
        @Override
        public CarImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_car_image, parent, false);
            return new CarImageViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull CarImageViewHolder holder, int position) {
            holder.imageView.setImageResource(images.get(position));
        }

        @Override
        public int getItemCount() {
            return images.size();
        }

        class CarImageViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView;

            CarImageViewHolder(View itemView) {
                super(itemView);
                imageView = itemView.findViewById(R.id.imageView);
            }
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Stop the auto-scrolling when the activity is destroyed
        if (handler != null && runnable != null) {
            handler.removeCallbacks(runnable);
        }
    }
}
