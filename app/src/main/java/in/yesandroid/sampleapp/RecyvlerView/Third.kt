package `in`.yesandroid.sampleapp.RecyvlerView

import `in`.yesandroid.sampleapp.MainActivity
import `in`.yesandroid.sampleapp.R
import `in`.yesandroid.sampleapp.Tree
import `in`.yesandroid.sampleapp.TreeViewModel
import `in`.yesandroid.tree.RecyvlerView.ThirdAdapter

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView


import java.lang.Integer.parseInt
import java.text.DateFormat
import java.util.*


class Third : AppCompatActivity() {

    private lateinit var treeViewModel: TreeViewModel

    var a = ArrayList<String>()
    var b = ArrayList<String>()

    var c = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        val agetext = findViewById(R.id.treeage) as EditText
        val btn=findViewById(R.id.btn) as Button




        if (agetext.length() > 0) {
            agetext.getText().clear();
        }

        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()?.setDisplayShowHomeEnabled(true)

        treeViewModel = ViewModelProvider(this).get(TreeViewModel::class.java)



        treeViewModel.readAllData.observe(this, Observer {

            for (i in 0..it.size - 1) {
                a.add(it[i].id.toString())
                b.add(it[i].age.toString())
                c.add(it[i].timedate)
            }

            Log.d("size", it.size.toString())
            Log.d("a", a.toString())
            Log.d("b", b.toString())
        })



        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)

      //  recyclerView.removeAllViewsInLayout()
        recyclerView.adapter = ThirdAdapter(a, b, c)

        btn.setOnClickListener {


            if(agetext.text.length>0)
            {
                val agg= parseInt(agetext.text.toString())
                val timedate=java.util.Calendar.getInstance().toString()

                val currentDateTimeString = DateFormat.getDateTimeInstance().format(Date())

                val tree = Tree(
                    0,
                    agg,
                    currentDateTimeString,
                )
                treeViewModel.addTree(tree)


                tt();

                Toast.makeText(applicationContext,"data added", Toast.LENGTH_SHORT).show()


            }


//            val intent = Intent(this, Third::class.java)
//            startActivity(intent)
//            finish()




        }






    }

   fun tt(){
       val recyclerView: RecyclerView = findViewById(R.id.recycler_view)

       recyclerView.removeAllViewsInLayout()
       recyclerView.adapter = ThirdAdapter(a, b, c )
   }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

            }
        }
        return super.onOptionsItemSelected(item)
    }
}