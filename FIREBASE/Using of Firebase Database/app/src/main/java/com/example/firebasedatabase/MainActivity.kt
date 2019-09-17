package com.example.firebasedatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //To use any functioning of firebase, we need to add firebase analytics even if we are not using.
        //First go to tools -> firebase and add the firebase to your app
        //Now add the analytics dependency which add the core firebase dependeny
        //Now add the realtime database dependency and always make sure to make it to the latest version as it may not be of the latest version all the time

        //Now go to firebase console project where you have linked this project and for now we will work on realtime Database.
        //Since we want to both read and write the data, therefore we should open it in test mode. But like if you are opening the account of the authenticated user ,
        //then you should make sure that the app is in locked mode i.e if no user is logged in,you should not be able to read or write the data on the database(we will read about locked mode later)

        //First we will get the reference to our firebase database (root node) so that we can use it to read and write the data
        val myFirebaseDatabase = FirebaseDatabase.getInstance().reference






        //now we will access the childs on this firebase and add the change listener to it and it will be executed if any changes in the value of key "text" will be executed here
        //Thereofore to get the reference of that child "text", we will get it by child method. Therefore this realtime database is very helpful as we don't need to call the firebase again and again.
        // As it will automatically update the data again and again as soon as the data on the databse on the firebase console vhanges
        myFirebaseDatabase.child("messages")
            .child("user1")
            .child("text")
            .addValueEventListener(object : ValueEventListener{
                override fun onCancelled(p0: DatabaseError) {

                }

                override fun onDataChange(p0: DataSnapshot) {
                    //therefore if any changes in the data base occurs, it gets us the whole database. So this can be very bad at times as we then need to fetch the data one by one from the whole key.
                    // Therefore always prefer addOnValueChangeListener. But in some cases, this too would also be very helpful
                    textView.text = p0.value.toString()
                }
            })





        //This data class is of the messages type , i have just created it for our own understanding
        class Chat {
            var text: String = ""
            var time: String = ""
        }

        val list = arrayListOf<Chat>()

        myFirebaseDatabase.child("messages").addChildEventListener(object  : ChildEventListener{
            override fun onCancelled(p0: DatabaseError) {

                //When the operation to read the data from the firebase gets failed due to some reasons, then this method is called.
            }


            override fun onChildMoved(p0: DataSnapshot, p1: String?) {
                //When the position of subNode inside the child "messages" changes

            }


            override fun onChildChanged(p0: DataSnapshot, p1: String?) {

                //This method is called when the existing data is updated
                val result = p0.getValue(Chat::class.java) //to get the data, we should provide it the class of type of which exist on the database for which it is to be mapped.
                result?.let {
                    list.add(it)

                    //First clear the whole textView . Then add all the chats

                    textView.text = ""
                    list.forEach{
                        textView2.text = "${textView2.text} /n ${it.text}"
                    }
                }
            }


            override fun onChildAdded(p0: DataSnapshot, p1: String?) {

                //This function is called when the data is inserted to the "messages" node or reference
                val result = p0.getValue(Chat::class.java)
                textView2.text = ""
                result?.let {
                    list.add(it)
                    list.forEach{
                        textView2.text = "${textView2.text}  /n ${it.text}"
                    }
                }
            }


            override fun onChildRemoved(p0: DataSnapshot) {
                //This method is called when the data is removed

            }

        })






        //Now we will how can we add the add some data to our database through code. Here we pass the value of the string in the reference path only
        myFirebaseDatabase.child("messages/user3/text").setValue("New Text Added")


        //Now we will learn how can we generate random id. so that we don't have to add the id on its own
        var chat = Chat()
        chat.text = "New Text Added through Random Key Method"
        chat.time = "12 PM"
        val ref = myFirebaseDatabase.child("messages")
        val key = ref.push().key  //This method generates automatically a unique key looking at the firbase to use
        ref.child("${key}/").setValue(chat)   //If we don't want the key we can simply write --> ref.child("messages").push().setValue(chat) -> it will automatically generate a key and add thid data
    }

}


//In this app we have also learnt about the cloud messaging part which is very useful for sending messages to the app users through firebase
//to use this first add the cloud messaging dependency and add the fcm to your app. Now push the notification from the firebase database console



//We can also perform some task like - > performing login operation and then adding the chat. Since for each user the uid is unique, therefore under that node we can store the user's all the chats like this
//FirebaseAuth.getInstance().signInWithEmailAndPassword( "aggarwalpulkit596@gmail.com", "12345678" ).addOnSuccessListener{
//    uid = it.user.uid
//    val chat = Chat()
//    chat.text = "New text Added"
//    chat.time = "12 PM"
//    val key = ref.push().key
//    ref.child("$uid/$key/")
//        .setValue(chat)
// }


//Now we can make our app more protective if we change the rules of the firebase for the read and write mode like since we know that unless and until the
// user is login, then only we need to read or write from the database. We can make our rules like this ->
//Rules for firebase
//        {
//            "rules": {
//            ".read": "auth.uid !== null",
//            ".write": "auth.uid !== null"
//        }
//        }