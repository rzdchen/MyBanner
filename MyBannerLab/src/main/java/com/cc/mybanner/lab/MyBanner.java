package com.cc.mybanner.lab;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.cc.mybanner.lab.core.MyBannerDelegate;
import com.cc.mybanner.lab.core.MyBannerMo;
import com.cc.mybanner.lab.core.IBindAdapter;
import com.cc.mybanner.lab.core.IMyBanner;
import com.cc.mybanner.lab.indicator.MyIndicator;

import java.util.List;

/**
 * 核心问题：
 * 1. 如何实现UI的高度定制？
 * 2. 作为有限的item如何实现无线轮播呢？
 * 3. Banner需要展示网络图片，如何将网络图片库和Banner组件进行解耦？
 * 4. 指示器样式各异，如何实现指示器的高度定制？
 * 5. 如何设置ViewPager的滚动速度？
 */
public class MyBanner extends FrameLayout implements IMyBanner {
    public MyBannerDelegate delegate;

    public MyBanner(@NonNull Context context) {
        this(context, null);
    }

    public MyBanner(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyBanner(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        delegate = new MyBannerDelegate(getContext(), this);
        initCustomAttrs(context, attrs);
    }

    private void initCustomAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyBanner);
        boolean autoPlay = typedArray.getBoolean(R.styleable.MyBanner_autoPlay, true);
        boolean loop = typedArray.getBoolean(R.styleable.MyBanner_loop, false);
        int intervalTime = typedArray.getInteger(R.styleable.MyBanner_intervalTime, -1);
        setAutoPlay(autoPlay);
        setLoop(loop);
        setIntervalTime(intervalTime);
        typedArray.recycle();
    }

    @Override
    public void setBannerData(@LayoutRes int layoutResId, @NonNull List<? extends MyBannerMo> models) {
        delegate.setBannerData(layoutResId, models);
    }

    @Override
    public void setBannerData(@NonNull List<? extends MyBannerMo> models) {
        delegate.setBannerData(models);
    }

    @Override
    public void setMyIndicator(MyIndicator myIndicator) {
        delegate.setMyIndicator(myIndicator);
    }

    @Override
    public void setAutoPlay(boolean autoPlay) {
        delegate.setAutoPlay(autoPlay);
    }

    @Override
    public void setLoop(boolean loop) {
        delegate.setLoop(loop);
    }

    @Override
    public void setIntervalTime(int intervalTime) {
        delegate.setIntervalTime(intervalTime);
    }

    @Override
    public void setBindAdapter(IBindAdapter bindAdapter) {
        delegate.setBindAdapter(bindAdapter);
    }

    @Override
    public void setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        delegate.setOnPageChangeListener(onPageChangeListener);
    }

    @Override
    public void setScrollDuration(int duration) {
        delegate.setScrollDuration(duration);
    }

    @Override
    public void setOnBannerClickListener(OnBannerClickListener onBannerClickListener) {
        delegate.setOnBannerClickListener(onBannerClickListener);
    }

}
