package com.example.calvingonsalves.ecommerceapp;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.example.calvingonsalves.ecommerceapp.R.color.colorPrimary;

public class ProductDetailsActivity extends AppCompatActivity {

    private ViewPager productImagesViewPager;
    private TabLayout viewPagerIndicator;

    private static Boolean ALREADY_ADDED_WISH_LIST = false;
    private FloatingActionButton addToWishListBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        productImagesViewPager = findViewById(R.id.products_images_viewpager);
        viewPagerIndicator = findViewById(R.id.viewpager_indicator);
        addToWishListBtn = findViewById(R.id.add_to_wishlist_btn);


        List<Integer> productImages = new ArrayList<>();
        productImages.add(R.drawable.xperia);
        productImages.add(R.drawable.smartphone);
        productImages.add(R.drawable.banner);
        productImages.add(R.drawable.smartphone);

        ProductImagesAdapter productImagesAdapter = new ProductImagesAdapter(productImages);
        productImagesViewPager.setAdapter(productImagesAdapter);

        viewPagerIndicator.setupWithViewPager(productImagesViewPager,true);


        addToWishListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ALREADY_ADDED_WISH_LIST) {
                    ALREADY_ADDED_WISH_LIST = false;
                    addToWishListBtn.setImageTintList(ColorStateList.valueOf(Color.parseColor("a4a4a4")));
                } else {
                    ALREADY_ADDED_WISH_LIST = true;
                    addToWishListBtn.setImageTintList(ColorStateList.valueOf(getColor(R.color.colorPrimary)));
                }
            }
        });




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_and_cart_icon, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            //todo: search
            finish();
            return true;
        } else if (id == R.id.main_search_icon) {
            //todo: notification system
                return true;
        } else if (id == R.id.main_cart_icon) {
            //todo:cart
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
