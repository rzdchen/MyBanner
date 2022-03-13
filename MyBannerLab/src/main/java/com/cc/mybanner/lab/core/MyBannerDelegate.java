package com.cc.mybanner.lab.core;

import android.content.Context;
import android.widget.FrameLayout;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

import com.cc.mybanner.lab.MyBanner;
import com.cc.mybanner.lab.R;
import com.cc.mybanner.lab.indicator.MyCircleIndicator;
import com.cc.mybanner.lab.indicator.MyIndicator;

import java.util.List;

/**
 * MyBanner的控制器
 * 辅助MyBanner完成各种功能的控制
 * 将MyBanner的一些逻辑内聚在这，保证暴露给使用者的MyBanner干净整洁
 */
public class MyBannerDelegate implements ViewPager.OnPageChangeListener, IMyBanner {
    private Context mContext;
    private MyBanner mBanner;
    private MyBannerAdapter mAdapter;
    private MyIndicator mMyIndicator;
    private boolean mAutoPlay;
    private boolean mLoop;
    private List<? extends MyBannerMo> mMyBannerMos;
    private ViewPager.OnPageChangeListener mOnPageChangeListener;
    private int mIntervalTime = 5000;
    private MyBanner.OnBannerClickListener mOnBannerClickListener;
    private MyViewPager myViewPager;
    private int mScrollDuration = -1;

    public MyBannerDelegate(Context context, @NonNull MyBanner banner) {
        mContext = context;
        mBanner = banner;
    }

    @Override
    public void setBannerData(@LayoutRes int layoutResId, @NonNull List<? extends MyBannerMo> models) {
        mMyBannerMos = models;
        init(layoutResId);
    }

    @Override
    public void setBannerData(@NonNull List<? extends MyBannerMo> models) {
        setBannerData(R.layout.my_banner_item_image, models);
    }

    public void setAdapter(MyBannerAdapter adapter) {
        this.mAdapter = adapter;
    }

    @Override
    public void setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        this.mOnPageChangeListener = onPageChangeListener;
    }

    @Override
    public void setOnBannerClickListener(MyBanner.OnBannerClickListener onBannerClickListener) {
        this.mOnBannerClickListener = onBannerClickListener;
    }

    @Override
    public void setMyIndicator(MyIndicator myIndicator) {
        this.mMyIndicator = myIndicator;
    }

    @Override
    public void setAutoPlay(boolean autoPlay) {
        this.mAutoPlay = autoPlay;
        if (mAdapter != null) mAdapter.setAutoPlay(autoPlay);
        if (myViewPager != null) myViewPager.setAutoPlay(autoPlay);
    }

    @Override
    public void setLoop(boolean loop) {
        this.mLoop = loop;
    }

    @Override
    public void setBindAdapter(IBindAdapter bindAdapter) {
        mAdapter.setBindAdapter(bindAdapter);
    }

    @Override
    public void setIntervalTime(int intervalTime) {
        if (intervalTime > 0) {
            this.mIntervalTime = intervalTime;
        }
    }

    @Override
    public void setScrollDuration(int duration) {
        this.mScrollDuration = duration;
        if (myViewPager != null && duration > 0) myViewPager.setScrollDuration(duration);
    }

    private void init(@LayoutRes int layoutResId) {
        if (mAdapter == null) {
            mAdapter = new MyBannerAdapter(mContext);
        }
        if (mMyIndicator == null) {
            mMyIndicator = new MyCircleIndicator(mContext);
        }
        mMyIndicator.onInflate(mMyBannerMos.size());
        mAdapter.setLayoutResId(layoutResId);
        mAdapter.setBannerData(mMyBannerMos);
        mAdapter.setAutoPlay(mAutoPlay);
        mAdapter.setLoop(mLoop);
        mAdapter.setOnBannerClickListener(mOnBannerClickListener);

        myViewPager = new MyViewPager(mContext);
        myViewPager.setIntervalTime(mIntervalTime);
        myViewPager.addOnPageChangeListener(this);
        myViewPager.setAutoPlay(mAutoPlay);
        if (mScrollDuration > 0) myViewPager.setScrollDuration(mScrollDuration);
        FrameLayout.LayoutParams layoutParams =
                new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);

        myViewPager.setAdapter(mAdapter);

        if ((mLoop || mAutoPlay) && mAdapter.getRealCount() != 0) {
            //无限轮播关键点：使第一张能反向滑动到最后一张，已达到无限滚动的效果
            int firstItem = mAdapter.getFirstItem();
            myViewPager.setCurrentItem(firstItem, false);
        }

        //清除缓存view
        mBanner.removeAllViews();
        mBanner.addView(myViewPager, layoutParams);
        mBanner.addView(mMyIndicator.get(), layoutParams);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (null != mOnPageChangeListener && mAdapter.getRealCount() != 0) {
            mOnPageChangeListener.onPageScrolled(position % mAdapter.getRealCount(), positionOffset, positionOffsetPixels);
        }

    }

    @Override
    public void onPageSelected(int position) {
        if (mAdapter.getRealCount() == 0) {
            return;
        }
        position = position % mAdapter.getRealCount();
        if (mOnPageChangeListener != null) {
            mOnPageChangeListener.onPageSelected(position);
        }
        if (mMyIndicator != null) {
            mMyIndicator.onPointChange(position, mAdapter.getRealCount());
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (mOnPageChangeListener != null) {
            mOnPageChangeListener.onPageScrollStateChanged(state);
        }
    }
}
