package com.cc.mybanner.lab.core;

/**
 * MyBanner的数据绑定接口，基于该接口可以实现数据的绑定和框架层解耦
 */
public interface IBindAdapter {
    void onBind(MyBannerAdapter.MyBannerViewHolder viewHolder, MyBannerMo mo, int position);
}
