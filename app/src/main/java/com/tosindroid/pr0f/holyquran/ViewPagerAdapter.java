package com.tosindroid.pr0f.holyquran;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter {


    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new SurahFragment();
            case 1:
                return new JuzFragment();
            default:
                return new FavoriteFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }

}
