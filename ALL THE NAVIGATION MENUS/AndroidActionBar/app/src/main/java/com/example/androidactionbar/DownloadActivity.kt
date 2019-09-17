package com.example.androidactionbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class DownloadActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_download)
        var x = supportActionBar
        x?.setTitle("DIVYANSHU MADARCHOD")
        x?.setSubtitle("Sabse bada madarchod")

        //To add an icon to our actionBar
        x?.setIcon(R.drawable.refresh)
        // Just adding the above line won't set up the icon in the action Bar. You need to add these 2 lines too.
        x?.setDisplayUseLogoEnabled(true)
        x?.setDisplayShowHomeEnabled(true)

        //Now we will set up the backButton here.
        x?.setDisplayHomeAsUpEnabled(true) //Only writing this won't work. You have to add "meta data" in the manifest file under the second Activity bracket

    }
}
