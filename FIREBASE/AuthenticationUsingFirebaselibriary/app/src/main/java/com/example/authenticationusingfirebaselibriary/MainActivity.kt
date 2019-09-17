package com.example.authenticationusingfirebaselibriary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.firebase.ui.auth.AuthUI
import com.google.android.gms.auth.api.Auth
import com.google.firebase.auth.FirebaseAuth
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if(resultCode == 123){

        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val firebaseUser = FirebaseAuth.getInstance()
        if(firebaseUser.currentUser!=null){
            //We are signed in

        }else{
            //We are signed out
            startActivityForResult(
                AuthUI.getInstance().createSignInIntentBuilder().build(),
                123
            )

            //seisSmartEnabled(false)
            //FirebaseUser.getUId
            //getName

//            startActivityForResult(
//                AuthUI.getInstance().createSignInIntentBuilder().setAvailableProviders(Arrays.asList(
//                    AuthUI.IdpConfig.GoogleBuilder().build(),
//                    AuthUI.IdpConfig.EmailBuilder().build(),
//                    AuthUI.IdpConfig.PhoneBuilder().build(),
//                    AuthUI.IdpConfig.AnonymousBuilder().build())).build()
//                ,123
//            )
        }
    }
}
