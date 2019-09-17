package com.example.accessingcamera

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Rational
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.Size
import androidx.camera.core.*
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {

    fun startCamera()
    {


        //First we will learn how to show the preview of the camera on the our texture view
        val previewConfig = PreviewConfig.Builder().apply {
            setTargetResolution(android.util.Size(1080, 1080))
            setTargetAspectRatio(Rational(1, 1))   //It is the ratio of the length to the breadth of the photo
            setLensFacing(CameraX.LensFacing.BACK)  //If we don't mention this, by default it will be the back camera and if we want the from camera to come, we can even do it
        }.build()


        val preview = Preview(previewConfig)

        preview.setOnPreviewOutputUpdateListener {

            //what we are actucally doing is that we have set the observer on the preview which is accessing the camera and what camera is seeing is replaced by
            // that preview in the textureView each second. It is like showing each preview it see in every milliSecond on the texture view

            //More over we can't simply add the new texture View, because it will get compiled on one above other. Therefore first remove
            // the before texture View and add the new texture View and show preview in it, Therefore it will save the memory
            val parent = textureView.parent as ViewGroup
            parent.removeView(textureView)
            parent.addView(textureView, 0)
            textureView.surfaceTexture = it.surfaceTexture
        }






        //Now we will learn to capture the image through our phone
        val imageCaptureConfig = ImageCaptureConfig.Builder().apply {
            setTargetAspectRatio(Rational(1, 1))
            setCaptureMode(ImageCapture.CaptureMode.MIN_LATENCY)
        }.build()
        val imageCapture = ImageCapture(imageCaptureConfig)
        capture.setOnClickListener {

            //we will save this image in our android external directory. Since we are using the androidX to store only the android images in our android folder, therefore we
            // don't the permission to need to read the external store for now here We will save the image in file and name it accoding the time of the area as whenever an
            // image is created, its name will be unique . If we try to store the images with same name file, then new files aren't created with same name, older files are over written
            val file = File(externalMediaDirs.first(), "${System.currentTimeMillis()}.jpg")

            imageCapture.takePicture(file, object : ImageCapture.OnImageSavedListener {
                override fun onImageSaved(file: File) {
                    Toast.makeText(this@MainActivity,"Photo captured ${file.absolutePath}",Toast.LENGTH_LONG).show()
                }

                override fun onError(useCaseError: ImageCapture.UseCaseError, message: String, cause: Throwable?) {

                    //Here if we want that when the process is completed and want to show it to the other activity. Then we will need to access
                    Toast.makeText(this@MainActivity,"Error $message",Toast.LENGTH_LONG).show()
                }

            })
        }






        //Now we will finally link the preview to the CameraX
        CameraX.bindToLifecycle(this, preview,imageCapture)
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(requestCode == 12)
        {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                startCamera()
            }
            else
            {
                Toast.makeText(this,"PLEASE GRANT THE PERMISSION TO ACCESS THE CAMERA",Toast.LENGTH_SHORT).show()
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA), 12)
        }
        else
        {
            startCamera()
        }


        capture.setOnClickListener {

        }

    }
}




//There are basic 3 properties with android x(only first 2 are done here) -
// Image Capture
// Preview
// Image Analysis(this is generally used in ML) -> but try this

