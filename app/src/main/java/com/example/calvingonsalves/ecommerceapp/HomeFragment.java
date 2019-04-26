package com.example.calvingonsalves.ecommerceapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }

    private RecyclerView categoryRecyclerView;
    private CategoryAdapter categoryAdapter;

    /////// Banner Slider

    private ViewPager bannerSliderViewPager;
    private List<SliderModel> sliderModelList;
    private int currentPage =2; //original list starts at index 2


    /////// Banner Slider

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        categoryRecyclerView = view.findViewById(R.id.category_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity()); // getActivity() due to inside fragment
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        categoryRecyclerView.setLayoutManager(layoutManager);

        List<CategoryModel> categoryModelList = new ArrayList<CategoryModel>();
        categoryModelList.add(new CategoryModel("link","Home"));
        categoryModelList.add(new CategoryModel("link","Electronics"));
        categoryModelList.add(new CategoryModel("link","Appliances"));
        categoryModelList.add(new CategoryModel("link","Furniture"));
        categoryModelList.add(new CategoryModel("link","Fashion"));
        categoryModelList.add(new CategoryModel("link","Toys"));
        categoryModelList.add(new CategoryModel("link","Sports"));
        categoryModelList.add(new CategoryModel("link","Wall Arts"));
        categoryModelList.add(new CategoryModel("link","Books"));
        categoryModelList.add(new CategoryModel("link","Shoes"));

        categoryAdapter = new CategoryAdapter(categoryModelList); //passing list into adapter
        categoryRecyclerView.setAdapter(categoryAdapter);//setting adapter on recycler view
        categoryAdapter.notifyDataSetChanged();

        ///////////// Banner Slider

        bannerSliderViewPager = view.findViewById(R.id.banner_slider_view_pager); //view. because inside fragment

        sliderModelList = new ArrayList<SliderModel>();

        sliderModelList.add(new SliderModel(R.mipmap.house));
        sliderModelList.add(new SliderModel(R.mipmap.custom_error));

        sliderModelList.add(new SliderModel(R.mipmap.green_email));
        sliderModelList.add(new SliderModel(R.mipmap.red_email));
        sliderModelList.add(new SliderModel(R.mipmap.app_icon));
        sliderModelList.add(new SliderModel(R.mipmap.ic_launcher));
        sliderModelList.add(new SliderModel(R.mipmap.profile_placeholder));
        sliderModelList.add(new SliderModel(R.mipmap.house));
        sliderModelList.add(new SliderModel(R.mipmap.custom_error));

        sliderModelList.add(new SliderModel(R.mipmap.green_email));
        sliderModelList.add(new SliderModel(R.mipmap.red_email));


        SliderAdapter sliderAdapter = new SliderAdapter(sliderModelList);
        bannerSliderViewPager.setClipToPadding(false);
        bannerSliderViewPager.setPageMargin(20);



        ///////////// Banner Slider


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


    ///////////// Banner Slider


}
