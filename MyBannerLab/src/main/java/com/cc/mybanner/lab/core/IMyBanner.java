package com.cc.mybanner.lab.core;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

import com.cc.mybanner.lab.MyBanner;
import com.cc.mybanner.lab.indicator.MyIndicator;


import java.util.List;

public interface IMyBanner {
    void setBannerData(@LayoutRes int layoutResId, @NonNull List<? extends MyBannerMo> models);

    void setBannerData(@NonNull List<? extends MyBannerMo> models);

    void setMyIndicator(MyIndicator myIndicator);

    void setAutoPlay(boolean autoPlay);

    void setLoop(boolean loop);

    void setIntervalTime(int intervalTime);

    void setBindAdapter(IBindAdapter bindAdapter);

    void setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener);

    void setOnBannerClickListener(MyBanner.OnBannerClickListener onBannerClickListener);

    void setScrollDuration(int duration);

    interface OnBannerClickListener {
        void onBannerClick(@NonNull MyBannerAdapter.MyBannerViewHolder viewHolder, @NonNull MyBannerMo bannerMo, int position);
    }
}
