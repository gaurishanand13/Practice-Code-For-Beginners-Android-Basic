package com.example.filereadwrite

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.io.*
import android.util.Log
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*
        //SaveButton will save the data in the TextFile. So Here we are required to write this string to the textFile
        SaveButton.setOnClickListener{
            //Note every android app has a folder named "data folder" which is used to create and write files in it. These files are used to store
            //information. This data folder is specific to this app only as all other apps can't access it and even the user of the phone
            // can't view it in the sd card.

            //Here the dataDir variable will have the location of the "data folder" which is very hidden inside the device.
            //getDatDir function help us in getting this data directory

            val datDir : File? = ContextCompat.getDataDir(this)
            var myFile:File = File(dataDir,"file.txt") // Here we are asking to make a new file names "file.txt" which is to made in the
            //location of dataDir

            //if we need to write in a file we need to do it with a try/catch as it may happen that the file with the name we are searching.
            // Doesn't exist in the data directory folder

            try {
                //To input a string in the file. Here need to use FileOutputStream
                var fos : FileOutputStream = FileOutputStream(myFile)

                //Now first we should convert the data in form of bytes array to be able to write it in the file.txt folder
                fos.write(editText.text.toString().toByteArray())
                Toast.makeText(this,"DATA SUCCESSFULLY SAVED TO THE FILE",Toast.LENGTH_SHORT).show()
            }catch (fnfe : FileNotFoundException ) {
                Toast.makeText(this,"FILE NOT FOUND",Toast.LENGTH_SHORT).show()
            }catch (ioe : IOException) {
                Toast.makeText(this,"ERROR WHILE WRITING IN THE FILE",Toast.LENGTH_SHORT).show()
            }
        }

        //We should make note of this. It is not like if don't save the data with the help of the first button. We can't save it with the help of append button. We can do this
        //with the help of this button too. (Just remember your concepts of file handling where you used the concept of appending)
        AppendButton.setOnClickListener{

            val datDir : File? = ContextCompat.getDataDir(this)
            var myFile:File = File(dataDir,"file.txt") // Here we are asking to make a new file names "file.txt" which is to made in the
            //location of dataDir

            try {
                //To input a string in the file. Here need to use FileOutputStream
                var fos : FileOutputStream = FileOutputStream(myFile,true) //Here it is just the same as above. We use it if we don't want the file to delete the previous data
                //if it is made and if it has data()--> These both things can happend.

                //Now first we should convert the data in form of bytes array to be able to write it in the file.txt folder
                fos.write(editText.text.toString().toByteArray())
                Toast.makeText(this,"DATA SUCCESSFULLY SAVED TO THE FILE",Toast.LENGTH_SHORT).show()
            }catch (fnfe : FileNotFoundException ) {
                Toast.makeText(this,"FILE NOT FOUND",Toast.LENGTH_SHORT).show()
            }catch (ioe : IOException) {
                Toast.makeText(this,"ERROR WHILE WRITING IN THE FILE",Toast.LENGTH_SHORT).show()
            }

        }


        //Here we are required to read from the file
        OutputButton.setOnClickListener{
            val datDir : File? = ContextCompat.getDataDir(this)
            //We repeat the both statements here. This below statment does is that : it first searches for a file with the name file.txt -> if it
            // finds it, Then it will open and give access to the variable myFile. Otherwise it creates a new one.
            var myFile:File = File(dataDir,"file.txt")

            //Since to output a stream in the file we need a output stream. Similary to input a stream in a file we need a input Stream.
            try {
                var fis : FileInputStream= FileInputStream(myFile)
                //Note though we can easily write the whole string in a file in one go. But to output a string from the file we need to do it line by line.
                //We do it with the help of a buffer. What we do here is. We read each line and bring it our memory i.e buffer reads line by line.

                var isr: InputStreamReader  = InputStreamReader(fis)
                var br : BufferedReader = BufferedReader(isr)

                //Note if we make a string object here it is immutable as it can't be changed if we want to write something in it.
                //Therefore we are making the object of type StringBuilder in which we can make changes.
                var sb: StringBuilder = StringBuilder()

                //We will store each line the buffer reads in the string variable x and append it to the stringBuilder variavle(object) = sb
                var buffer:String? = br.readLine()
                while(buffer != null)
                {
                    sb.append(buffer)
                    buffer = br.readLine() // as soon as the buffer sees that there is no line next. The buffer return null
                }
                textView.text  = sb.toString()
            }catch (fnfe : FileNotFoundException) {

                Toast.makeText(this,"FILE NOT FOUND",Toast.LENGTH_SHORT).show()

            }catch (ioe : IOException) {
                Toast.makeText(this,"ERROR WHILE READING FROM THE FILE",Toast.LENGTH_SHORT).show()

            }
        }*/


        //Note though the above function works fine. But the code is similar to java. Therefore it is taking a lot of time. We can do this in a very few line using kotlin functions.
        SaveButton.setOnClickListener{
            val datDir : File? = ContextCompat.getDataDir(this)
            var myFile:File = File(dataDir,"file.txt")
            //Therefore writing the above whole code. We can simply use this to write. It works in the similar manner internally. But it make our code look easy and more understandable
            //It is not like we can't use the above method. This down function would also be working in the same way.

            //Till now writing the above line won't create the file with name "file.txt". It will be created automatically only when we write something in the file.

            Log.i("TAG","FILE EXIST = ${myFile.exists()}")  //Till here file exist will be false when this file will be created for the first time
            myFile.writeText(editText.text.toString())
            Log.i("TAG","FILE EXIST = ${myFile.exists()}") //Now since we written in the fil. Therefore here file will exist . Hence file exist = true

        }

        AppendButton.setOnClickListener{
            val datDir : File? = ContextCompat.getDataDir(this)
            var myFile:File = File(dataDir,"file.txt")
            myFile.appendText(editText.text.toString())
        }

        OutputButton.setOnClickListener{
            val datDir : File? = ContextCompat.getDataDir(this)
            var myFile:File = File(dataDir,"file.txt")
            textView.text = myFile.readText()
        }
    }
}
