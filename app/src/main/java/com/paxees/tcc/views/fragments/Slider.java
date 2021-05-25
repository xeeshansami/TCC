package com.paxees.tcc.views.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.paxees.tcc.R;
import com.paxees.tcc.utils.SessionManager;

public class Slider extends Fragment implements View.OnClickListener {
    ViewPager mImageViewPager;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private LinearLayout dotsLayout;
    private TextView[] dots;
    private int[] layouts;
    private Button btn_next;
    private TextView btn_skip;
    SessionManager sessionManager;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
    }



    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_slider, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sessionManager = new SessionManager(getActivity());
        init(view);
    }

    public void init(View view) {
        viewPager = (ViewPager) view.findViewById(R.id.view_pager);
        dotsLayout = (LinearLayout) view.findViewById(R.id.layoutDots);
        btn_next = (Button) view.findViewById(R.id.btn_next);
        btn_skip = (TextView) view.findViewById(R.id.btn_skip);
        btn_next.setOnClickListener(this);
        btn_skip.setOnClickListener(this);
        layouts = new int[]{
                R.layout.viewpager_slider_1,
                R.layout.viewpager_slider_2,
                R.layout.viewpager_slider_3,};

        // adding bottom dots
        addBottomDots(0);
        viewPagerAdapter = new ViewPagerAdapter();
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);
    }

    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);
            // changing the next button text 'NEXT' / 'GOT IT'
            if (position == layouts.length - 1) {
                // last page. make button text to GOT IT
//                btnNext.setText(getString(R.string.str_start));
//                btnSkip.setVisibility(View.GONE);
            } else {
                // still pages are left
//                btnNext.setText(getString(R.string.str_next));
//                btnSkip.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };

    //addBottomsDots Mthd
    private void addBottomDots(int currentPage) {
        dots = new TextView[layouts.length];
        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(getActivity());
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setPadding(3, 3, 0, 0);
            dots[i].setTextColor(getResources().getColor(R.color.grey));
            dotsLayout.addView(dots[i]);
        }
        if (dots.length > 0)
            dots[currentPage].setTextColor(getResources().getColor(R.color.colorTheme));
    }

    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_skip:
                sessionManager.setSlidders(true);
                skip();
                break;
            case R.id.btn_next:
                next();
                break;
        }
    }

    public void next() {
        // checking for last page
        // if last page home screen will be launched
        int current = getItem(+1);
        if (current < layouts.length) {
            // move to next screen
            viewPager.setCurrentItem(current);
        } else {
            skip();
        }
    }

    private void skip() {
        NavHostFragment.findNavController(Slider.this).navigate(R.id.slide_to_dashboard);
    }

    public class ViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;

        public ViewPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(layouts[position], container, false);
            /*Typeface contm = Typeface.createFromAsset(getAssets(), "fonts/contm.ttf");
            if (position == 0) {
                sliderTV1 = view.findViewById(R.id.slider1_tv1);
                sliderTV2 = view.findViewById(R.id.slider1_tv2);
                sliderTV1.setTypeface(contm);
                sliderTV2.setTypeface(contm);
            } else if (position == 1) {
                slider2TV1 = view.findViewById(R.id.slider2_tv1);
                slider2TV2 = view.findViewById(R.id.slider2_tv2);
                slider2TV1.setTypeface(contm);
                slider2TV2.setTypeface(contm);
            } else if (position == 2) {
                slider3TV1 = view.findViewById(R.id.slider3_tv1);
                slider3TV2 = view.findViewById(R.id.slider3_tv2);
                slider3TV1.setTypeface(contm);
                slider3TV2.setTypeface(contm);
            }*/
            container.addView(view);
            return view;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
        /*=====================================*/
    }

}