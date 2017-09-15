package com.example.mastro.selfit;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mastro on 4/12/2017.
 */

public class ClassFragment extends OriginalFragment implements View.OnClickListener {


    CommBridge Bridge;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    public ClassFragment(){



    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bridge = (CommBridge) getActivity();

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        AppCompatActivity activity = ((AppCompatActivity) getActivity());
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        View view = inflater.inflate(R.layout.class_fragment, container, false);

        viewPager = (ViewPager) view.findViewById(R.id.viewpager);

        setupViewPager(viewPager);

        tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new PlanFragment(), getString(R.string.Lunes));
        adapter.addFragment(new PlanFragment(), getString(R.string.Martes));
        adapter.addFragment(new PlanFragment(), getString(R.string.Miercoles));
        adapter.addFragment(new PlanFragment(), getString(R.string.Jueves));
        adapter.addFragment(new PlanFragment(), getString(R.string.Viernes));
        adapter.addFragment(new PlanFragment(), getString(R.string.Sabado));
        adapter.addFragment(new PlanFragment(), getString(R.string.Domingo));
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {

    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}