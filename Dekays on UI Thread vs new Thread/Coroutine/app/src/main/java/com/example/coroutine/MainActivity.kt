package com.example.coroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import kotlin.random.Random

//To use a couritine we need a scope. And we initialise the context with the help of CoroutineContext
class MainActivity : AppCompatActivity(),CoroutineScope{


    //To handle all the coroutines we need a supervisor which will handle all its children. As the coroutines once made.
    //can't be deleted on its own. So we delete them with the help of the supervisor which would superwise all its children.

    val supervisor = SupervisorJob()

    //Here in the context we will mention the default thread on which we want the coroutine to run. Like here all the coroutines by default if nothing
    // is mentioned in the dispacture , they will make will run on the MainThread only as we have mentioned the dispatcher as MAIN Dispatcher.
    // We have attached the supervisor we made above to handle them.
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + supervisor


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //To make a coroutine, we should use the keyword Launch or async
        launch {
            //Here since no dispatcher is mentioned in the context. Therefore it will take the default dispatcher i.e this coroutine will run on main thread only
        }

        //Like if we know that the work in the couritine will be done on large data. Then we can here mention it as default i.e to do that Task on the seperate Thread.
        launch(Dispatchers.IO) {
            //Here since we have mentioned the dispatcher as IO(this dispatcher is used for networking). Therefore this coroutine will work on another thread.
            //here since this coroutine will not be accessed on the mainThread. therefore we can't make changes from the UI thread from this coroutine
        }

        button.setOnClickListener{

            //Now like if we want that the coroutine works on the mainThread except for the HTTP/NETWORK protocols we can mention it to do on seperate Thread.
            //We use Dispatchers.IO to mention of HTTP protocols are there do on seperate thread. Though here no HTTP protocol is there but still i am writing this to show you.
            launch{
                for(i in 1..10)
                {
                    kotlinx.coroutines.delay(4000) // This is a suspend function called from another suspend function or coroutine. Therefore this launch will
                    // get paused here . But the other activities going on this UI thread will continue as it is.

                    //Now since after the above function has got executed. It would come back to its dispatcher thread(here UI thread) i.e come back to resume state. Therefore
                    //can make changes in the UI as we are on a UI thread.
                    textView.text = i.toString()
                }
            }
        }
        Button2.setOnClickListener{
            val num = Random(System.currentTimeMillis()).nextInt()
            textView.text = "$num"
        }
    }
}
