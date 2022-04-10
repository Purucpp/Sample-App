package com.yesandroid.sampleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.yesandroid.kfirst.Get_Retrofit_Client
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)

        // ArrayList of class ItemsViewModel
        val data = ArrayList<ItemsViewModel>()

        // This loop will create 20 Views containing
        // the image with the count of view
        for (i in 1..20) {
           // data.add(ItemsViewModel(R.drawable.ic_launcher_background, "Item " + i))
        }

        // This will pass the ArrayList to our Adapter




        val client= Get_Retrofit_Client().getClient();
        val service = client?.create(Get_Interface::class.java)
        val call = service?.getsocial()

        call?.enqueue(object : Callback<List<Api_Response>?>
        {
            override fun onResponse(call: Call<List<Api_Response>?>, response: Response<List<Api_Response>?>)
            {
                val items = response.body()
                if (items != null) {
                    for( i in 0 until items.count())
                    {
                        Log.d("name",items[i].getName().toString())
                        Log.d("url",items[i].getImageUrl().toString());
                        data.add(ItemsViewModel(R.drawable.ic_launcher_background, items[i].getName().toString(),items[i].getImageUrl().toString(),false))

                    }

                    val adapter = CustomAdapter(data)

                    // Setting the Adapter with the recyclerview
                    recyclerview.adapter = adapter

                }
            }
            override fun onFailure(call: Call<List<Api_Response>?>, t: Throwable) {
                Log.d("Failed------", t.message.toString());

            }
        })

    }
}