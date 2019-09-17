package com.example.firebasebasic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    //By making our variable as lazy. this variable will be made only only then when we use it for the first time otherwise this code won't be executed.
    //Note - > Here the name of the variable is auth
    val auth by lazy {
        FirebaseAuth.getInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //To add a firebase the app. Though we can integrate firebase from the web Browser to our app. But that is a lengthy task. Therefore we will do from here only
        //go to tools -> firebase -> first we will use Analytics which gives us the analysis of the users as discussed above.
        // 1)Now first connect this project to firebase by clicking Connect to Firebase. Now we have just adding  and connecting our app with the firebase which we can also confirm by seiing
        //under project View -> src -> firebase file is added which shows the project number and all stuff.


        //Now since we have now successfully added the firebase to our app. We will first add the analytics to our app . It will add the dependencies to our app. Just make sure the dependencies added are of the latest version. You can check it by the redbulb which comes besides the libriary where you will get the latest versioner.

        if(auth.currentUser!=null)
        {
            textView.text = "CURRENT USER IS ALREADY SIGNED IN"
            signOut.isEnabled = true
            loginButton.isEnabled = false
            signUpButton.isEnabled = false
        }else
        {
            textView.text = "NO USER IS SIGNED IN"
            signUpButton.isEnabled = true
            loginButton.isEnabled = true
            signOut.isEnabled = false
        }
        signOut.setOnClickListener {
            auth.signOut()
            textView.text = "NO USER IS SIGNED IN"
            signUpButton.isEnabled = true
            loginButton.isEnabled = true
            signOut.isEnabled = false
        }
        signUpButton.setOnClickListener {
            auth.createUserWithEmailAndPassword(usernameEditText.text.toString(),passwordEditText.text.toString())
                .addOnCompleteListener {
                    //It is used when the login is completed but not fully till it fetches the data
                    signUpButton.isEnabled = false
                    loginButton.isEnabled = false
                    signOut.isEnabled = false
                }.addOnSuccessListener {
                    //Now login is enabled
                    textView.text = "CURRENT USER IS ALREADY SIGNED IN"
                    signUpButton.isEnabled = false
                    loginButton.isEnabled = false
                    signOut.isEnabled = true
                }.addOnFailureListener{
                    textView.text = "NO USER IS SIGNED IN"
                    signUpButton.isEnabled = true
                    loginButton.isEnabled = true
                    signOut.isEnabled = false
                }
        }
        loginButton.setOnClickListener {
            auth.signInWithEmailAndPassword(usernameEditText.text.toString(),passwordEditText.text.toString())
                .addOnCompleteListener {
                    //It is used when the login is completed but not fully till it fetches the data
                    signUpButton.isEnabled = false
                    loginButton.isEnabled = false
                    signOut.isEnabled = false
                }.addOnSuccessListener {
                    //Now login is enabled
                    textView.text = "CURRENT USER IS ALREADY SIGNED IN"
                    signUpButton.isEnabled = false
                    loginButton.isEnabled = false
                    signOut.isEnabled = true
                }.addOnFailureListener{
                    textView.text = "NO USER IS SIGNED IN"
                    signUpButton.isEnabled = true
                    loginButton.isEnabled = true
                    signOut.isEnabled = false
                    Toast.makeText(this,it.localizedMessage,Toast.LENGTH_LONG).show()
                }
        }



        //Now we will see how we can do verification through otp
        var verificationId: String = ""

        val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(p0: PhoneAuthCredential?) {
                Toast.makeText(this@MainActivity, "Verification Completed", Toast.LENGTH_LONG).show()
                p0?.let {
                    signUpWithPhone(it)
                }
            }

            override fun onVerificationFailed(p0: FirebaseException?) {
                Toast.makeText(this@MainActivity, "Exception Completed ${p0?.localizedMessage}", Toast.LENGTH_LONG)
                    .show()
            }

            override fun onCodeSent(p0: String?, p1: PhoneAuthProvider.ForceResendingToken?) {
                super.onCodeSent(p0, p1)
                Toast.makeText(this@MainActivity, "Code Sent Completed", Toast.LENGTH_LONG).show()
                verificationId = p0 ?: ""

            }

        }


        generateOtp.setOnClickListener {
            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91${numberEditText.text}",  //The number to which you want to send the otp and verify, and
                60, //the time for which that otp is valid
                TimeUnit.SECONDS,
                this,
                callbacks)
        }


        verifyOTP.setOnClickListener {
            val credential = PhoneAuthProvider.getCredential(verificationId,numberEditText.text.toString())
            signUpWithPhone(credential)
        }
    }
    private fun signUpWithPhone(p0: PhoneAuthCredential) {


        //Now same thing as we have seen in with sign with email, id
        auth.signInWithCredential(p0)
            .addOnCompleteListener {

            }.addOnSuccessListener {

            }.addOnFailureListener {

            }

    }

}
