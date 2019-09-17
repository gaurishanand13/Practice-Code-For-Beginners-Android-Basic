package com.example.filereadandwrite_to_externalstorage

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class MainActivity : AppCompatActivity() {

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if ((grantResults[0] == PackageManager.PERMISSION_DENIED) || (grantResults[1] == PackageManager.PERMISSION_DENIED)) {
            Toast.makeText(
                this,
                "Cannot run without permissions",
                Toast.LENGTH_SHORT
            ).show()
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Though we can easily write in the data directory of the "app" without permission and make it private(hidden). But if we want to write in the
        // externalStorage of our phone. We would be need to take permission from the device to access th permament storage. Otherwise the app would crash

        ActivityCompat.requestPermissions(this,
            arrayOf(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ),
            111
        )




        //Therefore to get the path opf external directory, we do this -
        val extDir = Environment.getExternalStorageDirectory()
        val myfile = File(extDir,"myFile.txt")



        SaveButton.setOnClickListener {
            myfile.writeText(editText.text.toString())
        }
        AppendButton.setOnClickListener {
            myfile.appendText(editText.text.toString())
        }
        OutputButton.setOnClickListener {
            textView.text = myfile.readText()
        }
    }
}
