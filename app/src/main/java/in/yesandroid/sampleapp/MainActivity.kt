package `in`.yesandroid.sampleapp

import `in`.yesandroid.sampleapp.adapter.CustomAdapter
import `in`.yesandroid.sampleapp.adapter.ItemsViewModel
import `in`.yesandroid.sampleapp.api.Api_Response
import `in`.yesandroid.sampleapp.api.Get_Interface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import `in`.yesandroid.sampleapp.api.Get_Retrofit_Client
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.view.Window
import android.widget.Toast
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


        // This will pass the ArrayList to our Adapter


        if (!checkForInternet(this)) {

            showDialog("No Internet")

        }



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




    private fun checkForInternet(context: Context): Boolean {

        // register activity with the connectivity manager service
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        // if the android version is equal to M
        // or greater we need to use the
        // NetworkCapabilities to check what type of
        // network has the internet connection
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            // Returns a Network object corresponding to
            // the currently active default data network.
            val network = connectivityManager.activeNetwork ?: return false

            // Representation of the capabilities of an active network.
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

            return when {
                // Indicates this network uses a Wi-Fi transport,
                // or WiFi has network connectivity
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true

                // Indicates this network uses a Cellular transport. or
                // Cellular has network connectivity
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true

                // else return false
                else -> false
            }
        } else {
            // if the android version is below M
            @Suppress("DEPRECATION") val networkInfo =
                connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
    }



    private fun showDialog(title: String) {
        val builder = AlertDialog.Builder(this)
        //set title for alert dialog
        builder.setTitle(title)
        //set message for alert dialog
        builder.setMessage("Please check network connection and Restart the app.")
        builder.setIcon(android.R.drawable.ic_dialog_alert)

        //performing positive action
     /*   builder.setPositiveButton("Yes"){dialogInterface, which ->
            Toast.makeText(applicationContext,"clicked yes",Toast.LENGTH_LONG).show()
        }
        //performing cancel action
        builder.setNeutralButton("Cancel"){dialogInterface , which ->
            Toast.makeText(applicationContext,"clicked cancel\n operation cancel",Toast.LENGTH_LONG).show()
        } */
        //performing negative action
        builder.setNegativeButton("OK"){dialogInterface, which ->
          //  Toast.makeText(applicationContext,"clicked No",Toast.LENGTH_LONG).show()
        }
        // Create the AlertDialog
        val alertDialog: AlertDialog = builder.create()
        // Set other dialog properties
        alertDialog.setCancelable(false)
        alertDialog.show()

    }

}