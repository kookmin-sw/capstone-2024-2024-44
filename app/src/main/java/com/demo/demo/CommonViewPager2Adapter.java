package com.demo.demo;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

public class CommonViewPager2Adapter extends FragmentStateAdapter {

    private List<Fragment> fragmentList;

    public CommonViewPager2Adapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public CommonViewPager2Adapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    public CommonViewPager2Adapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (fragmentList != null && !fragmentList.isEmpty()) {
            return fragmentList.get(position);
        } else {
            return new Fragment();
        }
    }

    @Override
    public int getItemCount() {
        if (fragmentList != null) {
            return fragmentList.size();
        } else {
            return 0;
        }
    }

    void setFragmentList(List<Fragment> fragmentList) {
        this.fragmentList = fragmentList;
    }
}
