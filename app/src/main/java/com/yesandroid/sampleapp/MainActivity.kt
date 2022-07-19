package com.yesandroid.sampleapp

//import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast


import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers //rxjava
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity()
{

    private lateinit var placeAdapter : PlacesAdapter


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)





        //  val places_list=findViewById<RecyclerView>(R.id.places_list) as RecyclerView
        placeAdapter = PlacesAdapter()



        places_list.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL))
        places_list.adapter = placeAdapter

        // Toast.makeText(this@MainActivity,"pass one ",Toast.LENGTH_SHORT).show()


        val retrofit : Retrofit = Retrofit.Builder()
            .baseUrl("https://yesandroid.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        val api = retrofit.create(Api::class.java)


        api.getPlaces()
            .subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())//rxjava
            .subscribe({ placeAdapter.setPlaces(it.data) },
                {
                    Toast.makeText(applicationContext, it.message, Toast.LENGTH_SHORT).show()
                })



    }


    inner class PlacesAdapter : RecyclerView.Adapter<PlacesAdapter.PlaceViewHolder>()
    {

        private val places: MutableList<Place> = mutableListOf()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder
        {
            return PlaceViewHolder(layoutInflater.inflate(R.layout.item_place_layout, parent, false))
        }

        override fun getItemCount(): Int
        {
            return places.size
        }

        override fun onBindViewHolder(holder: PlaceViewHolder, position: Int)
        {
            holder.bindModel(places[position])
        }

        fun setPlaces(data: List<Place>)
        {
            places.addAll(data)
            notifyDataSetChanged()
        }

        inner class PlaceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
        {

            val placeName : TextView = itemView.findViewById(R.id.place_name)


            val placeImage : ImageView = itemView.findViewById(R.id.place_id)

            fun bindModel(place: Place)
            {
                placeName.text = place.place

                Picasso.get().load(place.url).into(placeImage)
            }
        }
    }
}



