package com.yesandroid.sampleapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import `in`.yesandroid.sampleapp.RecyvlerView.Third
import kotlin.toString as kotlinToString

class MainActivity : AppCompatActivity() {

    private lateinit var treeViewModel: TreeViewModel




    lateinit var a:List<Tree>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val ctxt = findViewById(R.id.Count) as EditText


        val txtavg = findViewById(R.id.avg) as EditText

        val txtmedian = findViewById(R.id.mdn) as EditText

        val bt = findViewById(R.id.addtree) as Button


        bt.setOnClickListener {

            val intent = Intent(this, Third::class.java)

            startActivity(intent)

        }




        treeViewModel = ViewModelProvider(this).get(TreeViewModel::class.java)






        treeViewModel.readAllData.observe(this, Observer {

           // Log.d("test------->", it[0].id.toString())

        })


        val observe = treeViewModel.getCount()?.observe(this,
            Observer {

                ctxt.setText("Total Number: "+it?.toString())

            })


        val aobserve = treeViewModel.getAge()?.observe(this,
            Observer {

                txtavg.setText("Average: "+it?.toString())

            })


        val mobserve = treeViewModel.getMedian()?.observe(this,
            Observer {

                txtmedian.setText("Median :"+it?.toString())

            })

    }
}