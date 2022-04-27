package com.yesandroid.sampleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.yesandroid.sampleapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    // create instance of the ActivityMainBinding,
    // as we have only one layout activity_main.xml
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // create instance of the ActivityMainBinding,
        // as we have only one layout activity_main.xml
        var binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

        // binding.root returns the root layout,
        // which is activity_main.xml file itself
        setContentView(binding.root)


        // using the binding variable we can access the layout
        // properties and perform the operations on them as usual
        binding.button.setOnClickListener {
            val str: String = binding.editText.text.toString()
            if (str.isNotEmpty()) {
                Toast.makeText(this, "You entered " + binding.editText.text.toString(), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Please enter something", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
