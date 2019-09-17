package com.example.viewpagers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //First we will link the pagerAdapter with the viewPager
        var pagerAdapter  = fragmentStatePagerAdapter(supportFragmentManager)
        viewPager.adapter = pagerAdapter

        //Now we will link the ViewPager and tabLayout. If we won't add this line then the TabLayout and viewPager won't sync together
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

        //Now we will do what we want to do if an item on the tabLayout is selected
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(p0: TabLayout.Tab?) {
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {
            }

            override fun onTabSelected(p0: TabLayout.Tab?) {
                viewPager.currentItem = p0!!.position
            }

        })
    }
}
