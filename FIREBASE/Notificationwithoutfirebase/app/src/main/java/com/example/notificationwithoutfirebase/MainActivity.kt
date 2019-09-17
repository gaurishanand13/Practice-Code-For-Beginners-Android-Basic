package com.example.notificationwithoutfirebase

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val notificationManager =  getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            //If the build version of the phone is more than the oreo version(or api 26), then we need to pass an extra argument which are channels. Therefore first create the notification manager for the phone's which have build version greater than 26
            notificationManager.createNotificationChannel(NotificationChannel("first","default",NotificationManager.IMPORTANCE_DEFAULT))
        }

        simpleNotification.setOnClickListener {
            //Now we will create the object of ouy notification
            val simpleNotification = NotificationCompat.Builder(this,"first")
                .setContentTitle("Notification from my app")
                .setContentText("This is simple notification")
                .setSmallIcon(R.drawable.notification_icon_background)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .build()

            //Now we will notify the notification manager to notify this notification
            notificationManager.notify(1,simpleNotification)
        }

        clickableNotification.setOnClickListener {

            val intent = Intent()
            intent.action = Intent.ACTION_VIEW
            intent.data = Uri.parse("https://www.google.com")
            val pendingIntent = PendingIntent.getActivity(this,123,intent,PendingIntent.FLAG_UPDATE_CURRENT)

            //on clicking this notification, it will open the chrome.
            //if wwe want to open our app while clicking on the notification
            val clickableNotification = NotificationCompat.Builder(this,"first")
                .setContentTitle("Notification from my app")
                .setContentText("This is simple notification")
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.notification_icon_background)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .build()

            //Now we will notify the notification manager to notify this notification
            notificationManager.notify(1,clickableNotification)
        }

        coolNotification.setOnClickListener {

            val intent = Intent()
            intent.action = Intent.ACTION_VIEW
            intent.data = Uri.parse("https://www.google.com")
            val pendingIntent = PendingIntent.getActivity(this,123,intent,PendingIntent.FLAG_UPDATE_CURRENT)

            val clickableNotification = NotificationCompat.Builder(this,"first")
                .setContentTitle("Notification from my app")
                .setContentText("This is simple notification")
                .addAction(R.drawable.notification_icon_background,"Click",pendingIntent)
                .setSmallIcon(R.drawable.notification_icon_background)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .build()
            //Now we will notify the notification manager to notify this notification
            notificationManager.notify(1,clickableNotification)

        }
    }
}

//we can use the property of setAutoCancel if we want that the user is not able to remove the notification on swiping it