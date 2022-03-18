package `in`.yesandroid.sampleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.yesandroid.kfirst.Get_Retrofit_Client
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    val data = ArrayList<ItemsViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_main)

        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)

        // ArrayList of class ItemsViewModel

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


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.example_menu, menu)
        val searchitem = menu.findItem(R.id.action_search)
        val searchView = searchitem.actionView as SearchView
        //SearchView
        searchView.queryHint = "Search content"

        /*
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        searchView.setQueryHint("Search Content"); */searchView.setOnSearchClickListener { }
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                //mRecyclerAdapterApps.getFilter().filter(newText);
                filter(newText.toLowerCase())
                return false
            }
        })
        return true
    }

    fun filter(text: String?) {
       // val temp: MutableList<AppInfo?> = java.util.ArrayList<Any?>()
        val temp = ArrayList<ItemsViewModel>()
        for (d in data) {
            //or use .equal(text) with you want equal match
            //use .toLowerCase() for better matches
            if (d.text.toLowerCase().contains(text.toString())) {
                temp.add(d)
            }
        }

        val updatedAdapter = CustomAdapter(temp)

        val recyclervie = findViewById<RecyclerView>(R.id.recyclerview)

        recyclervie.adapter=updatedAdapter

        // Setting the Adapter with the recyclerview


        //update recyclerview

    }

}