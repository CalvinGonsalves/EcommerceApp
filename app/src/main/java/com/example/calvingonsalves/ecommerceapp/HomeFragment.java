package com.example.calvingonsalves.ecommerceapp;


import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }

    private RecyclerView categoryRecyclerView;
    private CategoryAdapter categoryAdapter;
    private RecyclerView testing;
    private List<CategoryModel> categoryModelList;
    private FirebaseFirestore firebaseFirestore;

    /////// Banner Slider

    private ViewPager bannerSliderViewPager;
    private List<SliderModel> sliderModelList;
    private int currentPage =2; //original list starts at index 2
    private Timer timer;
    final private long DELAY_TIME = 3000;
    final private long PERIOD_TIME = 3000;

    /////// Banner Slider

    ////// Strip Ad
    private ImageView stripAdImage;
    private ConstraintLayout stripAdContainer;
    ////// Strip Ad

    ////// Horizontal Product Layout
    private TextView horizontalLayoutTitle;
    private Button horizontalViewAllBtn;
    private RecyclerView horizontalRecyclerView;

    ////// Horizontal Product Layout

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        categoryRecyclerView = view.findViewById(R.id.category_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity()); // getActivity() due to inside fragment
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        categoryRecyclerView.setLayoutManager(layoutManager);

        categoryModelList = new ArrayList<CategoryModel>();

        categoryAdapter = new CategoryAdapter(categoryModelList); //passing list into adapter
        categoryRecyclerView.setAdapter(categoryAdapter);//setting adapter on recycler view

        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection("CATEGORIES").orderBy("index").get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()){
                                        for (QueryDocumentSnapshot documentSnapshot : Objects.requireNonNull(task.getResult())){
                                            categoryModelList.add(new CategoryModel(Objects.requireNonNull(documentSnapshot.get("icon")).toString(), Objects.requireNonNull(documentSnapshot.get("categoryName")).toString()));

                                        }
                                        categoryAdapter.notifyDataSetChanged();

                                    }else {
                                        String error = Objects.requireNonNull(task.getException()).getMessage();
                                        Toast.makeText(getContext(),error,Toast.LENGTH_SHORT).show();
                                    }
                            }
                        });



//        categoryModelList.add(new CategoryModel("link","Home"));
//        categoryModelList.add(new CategoryModel("link","Electronics"));
//        categoryModelList.add(new CategoryModel("link","Appliances"));
//        categoryModelList.add(new CategoryModel("link","Furniture"));
//        categoryModelList.add(new CategoryModel("link","Fashion"));
//        categoryModelList.add(new CategoryModel("link","Toys"));
//        categoryModelList.add(new CategoryModel("link","Sports"));
//        categoryModelList.add(new CategoryModel("link","Wall Arts"));
//        categoryModelList.add(new CategoryModel("link","Books"));
//        categoryModelList.add(new CategoryModel("link","Shoes"));



        ///////////// Banner Slider

        bannerSliderViewPager = view.findViewById(R.id.banner_slider_view_pager); //view. because inside fragment

        sliderModelList = new ArrayList<SliderModel>();

        sliderModelList.add(new SliderModel(R.mipmap.custom_error,"#077AE4"));
        sliderModelList.add(new SliderModel(R.mipmap.banner1,"#077AE4"));

        sliderModelList.add(new SliderModel(R.mipmap.green_email,"#077AE4"));
        sliderModelList.add(new SliderModel(R.mipmap.red_email,"#077AE4"));
        sliderModelList.add(new SliderModel(R.mipmap.app_icon,"#077AE4"));
        sliderModelList.add(new SliderModel(R.mipmap.ic_launcher,"#077AE4"));
        sliderModelList.add(new SliderModel(R.mipmap.profile_placeholder,"#077AE4"));
        sliderModelList.add(new SliderModel(R.mipmap.house,"#077AE4"));
        sliderModelList.add(new SliderModel(R.mipmap.custom_error,"#077AE4"));
        sliderModelList.add(new SliderModel(R.mipmap.banner1,"#077AE4"));


        sliderModelList.add(new SliderModel(R.mipmap.green_email,"#077AE4"));
        sliderModelList.add(new SliderModel(R.mipmap.red_email,"#077AE4"));


        SliderAdapter sliderAdapter = new SliderAdapter(sliderModelList);
        bannerSliderViewPager.setAdapter(sliderAdapter);
        bannerSliderViewPager.setClipToPadding(false);
        bannerSliderViewPager.setPageMargin(20);

        bannerSliderViewPager.setCurrentItem(currentPage);

        ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                currentPage = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {//identifying state of page
                if (state == ViewPager.SCROLL_STATE_IDLE) {

                    pageLooper();
                }
            }
        };
        bannerSliderViewPager.addOnPageChangeListener(onPageChangeListener);

        startBannerSlideShow();

        bannerSliderViewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                pageLooper();
                stopBannerSlideShow(); //when user touches banner , slideshow stops


                if (event.getAction() == MotionEvent.ACTION_UP){
                    startBannerSlideShow();
                }


                return false;
            }
        });


        ///////////// Banner Slider

        ////// Strip Ad
        stripAdImage = view.findViewById(R.id.strip_ad_image);
        stripAdContainer = view.findViewById(R.id.strip_ad_container);

        stripAdImage.setImageResource(R.drawable.mobile_banner);
        stripAdContainer.setBackgroundColor(Color.parseColor("#000000"));
        ////// Strip Ad

        ////// Horizontal Product Layout

        horizontalLayoutTitle = view.findViewById(R.id.horizontal_scroll_layout_title);
        horizontalViewAllBtn = view.findViewById(R.id.horizontal_scroll_view_all_btn);
        horizontalRecyclerView = view.findViewById(R.id.horizontal_scroll_layout_recycler_view);


        List<HorizontalProductScrollModel>  horizontalProductScrollModelList = new ArrayList<>();

        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.smartphone,"Samsung Galaxy S8","SD 865 Processor", "$400"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.smartphone,"Samsung Galaxy S8","SD 865 Processor", "$400"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.smartphone,"Samsung Galaxy S8","SD 865 Processor", "$400"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.smartphone,"Samsung Galaxy S8","SD 865 Processor", "$400"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.smartphone,"Samsung Galaxy S8","SD 865 Processor", "$400"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.smartphone,"Samsung Galaxy S8","SD 865 Processor", "$400"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.smartphone,"Samsung Galaxy S8","SD 865 Processor", "$400"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.smartphone,"Samsung Galaxy S8","SD 865 Processor", "$400"));


        HorizontalProductScrollAdapter horizontalProductScrollAdapter = new HorizontalProductScrollAdapter(horizontalProductScrollModelList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        horizontalRecyclerView.setLayoutManager(linearLayoutManager);

        horizontalRecyclerView.setAdapter(horizontalProductScrollAdapter);
        horizontalProductScrollAdapter.notifyDataSetChanged();
        ////// Horizontal Product Layout


        ////// Grid Product Layout

        TextView gridLayoutTitle = view.findViewById(R.id.grid_product_layout_title);
        Button gridLayoutViewAllBtn = view.findViewById(R.id.grif_product_layout_viewallbtn);
//        GridView gridView = view.findViewById(R.id.grid_product_layout_gridview);


//        gridView.setAdapter(new GridProductLayoutAdapter(horizontalProductScrollModelList));


        ////// Grid Product Layout


        //////  ///////////////////////////////////////////
        testing = view.findViewById(R.id.home_page_recycler_view);
        LinearLayoutManager testingLayoutManager = new LinearLayoutManager(getContext());
        testingLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        testing.setLayoutManager(testingLayoutManager);


        List<HomePageModel> homePageModelList = new ArrayList<>();
        homePageModelList.add(new HomePageModel(0, sliderModelList));
        homePageModelList.add(new HomePageModel(1, R.drawable.mobile_banner,"#000000"));
        homePageModelList.add(new HomePageModel(2, "Deals of the day!!",horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(3, "Deals of the day!!",horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(3, "Deals of the day!!",horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(2, "Deals of the day!!",horizontalProductScrollModelList));

        homePageModelList.add(new HomePageModel(0, sliderModelList));
        homePageModelList.add(new HomePageModel(1, R.drawable.banner,"#ffff00"));
        homePageModelList.add(new HomePageModel(0, sliderModelList));
        homePageModelList.add(new HomePageModel(1, R.drawable.mobile_banner,"#ff0000"));

        HomePageAdapter adapter = new HomePageAdapter(homePageModelList);

        testing.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        ///////////////////////////////////////////////////



        return (view);

    }

    ///////////// Banner Slider

    private void pageLooper()  //for infinte scrolling
    {
        if (currentPage == sliderModelList.size() - 2) {
            currentPage =2;
            bannerSliderViewPager.setCurrentItem(currentPage,false);
        }
        if (currentPage == 1) {
            currentPage = sliderModelList.size() - 3;
            bannerSliderViewPager.setCurrentItem(currentPage,false);
        }
    }

    private void startBannerSlideShow() {
        final Handler handler = new Handler();
        final Runnable update = new Runnable() {
            @Override
            public void run() {
                if (currentPage >= sliderModelList.size()){

                    currentPage = 1;
                }
                bannerSliderViewPager.setCurrentItem(currentPage++, true);
            }
        };
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(update);
            }
        },DELAY_TIME,PERIOD_TIME);


    }
    private void stopBannerSlideShow() {
        timer.cancel();
    }
    ///////////// Banner Slider


}
