package com.example.calvingonsalves.ecommerceapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CategoryActivity extends AppCompatActivity {


    private RecyclerView categoryRecyclerView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String title = getIntent().getStringExtra("CategoryName");
        Objects.requireNonNull(getSupportActionBar()).setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        categoryRecyclerView1 = findViewById(R.id.category_recycler_view);


/////////// Banner slider
        List<SliderModel> sliderModelList = new ArrayList<SliderModel>();

        sliderModelList.add(new SliderModel(R.mipmap.custom_error, "#077AE4"));
        sliderModelList.add(new SliderModel(R.mipmap.banner1, "#077AE4"));

        sliderModelList.add(new SliderModel(R.mipmap.green_email, "#077AE4"));
        sliderModelList.add(new SliderModel(R.mipmap.red_email, "#077AE4"));
        sliderModelList.add(new SliderModel(R.mipmap.app_icon, "#077AE4"));
        sliderModelList.add(new SliderModel(R.mipmap.ic_launcher, "#077AE4"));
        sliderModelList.add(new SliderModel(R.mipmap.profile_placeholder, "#077AE4"));
        sliderModelList.add(new SliderModel(R.mipmap.house, "#077AE4"));
        sliderModelList.add(new SliderModel(R.mipmap.custom_error, "#077AE4"));
        sliderModelList.add(new SliderModel(R.mipmap.banner1, "#077AE4"));


        sliderModelList.add(new SliderModel(R.mipmap.green_email, "#077AE4"));
        sliderModelList.add(new SliderModel(R.mipmap.red_email, "#077AE4"));

/////////Banner Slider


        ///////////// Horizontal product layout
        List<HorizontalProductScrollModel> horizontalProductScrollModelList = new ArrayList<>();

        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.smartphone, "Samsung Galaxy S8", "SD 865 Processor", "$400"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.smartphone, "Samsung Galaxy S8", "SD 865 Processor", "$400"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.smartphone, "Samsung Galaxy S8", "SD 865 Processor", "$400"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.smartphone, "Samsung Galaxy S8", "SD 865 Processor", "$400"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.smartphone, "Samsung Galaxy S8", "SD 865 Processor", "$400"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.smartphone, "Samsung Galaxy S8", "SD 865 Processor", "$400"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.smartphone, "Samsung Galaxy S8", "SD 865 Processor", "$400"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.smartphone, "Samsung Galaxy S8", "SD 865 Processor", "$400"));
        ///////////// Horizontal product layout

        /////////////////////////////////////////////////

        LinearLayoutManager testingLayoutManager = new LinearLayoutManager(this);
        testingLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        categoryRecyclerView1.setLayoutManager(testingLayoutManager);


        List<HomePageModel> homePageModelList = new ArrayList<>();
        homePageModelList.add(new HomePageModel(0, sliderModelList));
        homePageModelList.add(new HomePageModel(1, R.drawable.mobile_banner, "#000000"));
        homePageModelList.add(new HomePageModel(2, "Deals of the day!!", horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(3, "Deals of the day!!", horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(3, "Deals of the day!!", horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(2, "Deals of the day!!", horizontalProductScrollModelList));

        homePageModelList.add(new HomePageModel(1, R.drawable.banner, "#ffff00"));
        homePageModelList.add(new HomePageModel(1, R.drawable.mobile_banner, "#ff0000"));

        HomePageAdapter adapter = new HomePageAdapter(homePageModelList);

        categoryRecyclerView1.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        ///////////////////////////////////////////////////


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_icon, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.main_search_icon) {
            //todo: search
            return true;
        }else if (id == android.R.id.home){
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
