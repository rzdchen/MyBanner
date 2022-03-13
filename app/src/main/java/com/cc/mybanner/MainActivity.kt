package com.cc.mybanner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.cc.mybanner.lab.core.BannerMo
import com.cc.mybanner.lab.core.MyBannerMo
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        val moList = mutableListOf<MyBannerMo>()
        for (i in 0..7){
            val mo = BannerMo()
            mo.url = "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.jj20.com%2Fup%2Fallimg%2Ftp09%2F210611094Q512b-0-lp.jpg&refer=http%3A%2F%2Fimg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1649780940&t=7d5cc38b40ba0bb53cf3cb2ee3b795c9"
            moList.add(mo)
        }
        myBanner.setBannerData(moList)
        myBanner.setBindAdapter { viewHolder, mo, position ->
            Glide.with(this@MainActivity).load(mo?.url).into(viewHolder?.rootView as ImageView)
        }
    }
}