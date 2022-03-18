package `in`.janitri.sampleapp

import android.graphics.Color
import android.icu.number.NumberFormatter.with
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso


class CustomAdapter(private val mList: List<ItemsViewModel>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {


    var isPresent = ArrayList<Int>()


    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        if(isPresent.contains(position))
        {
            holder.itemView.setBackgroundColor(Color.CYAN)
        }
        else{
            holder.itemView.setBackgroundColor(Color.WHITE)
        }

        val ItemsViewModel = mList[position]
      //  holder.itemView.setBackgroundColor(if (ItemsViewModel.isSelected) Color.CYAN else Color.WHITE)


        // sets the image to the imageview from our itemHolder class
      //  holder.imageView.setImageResource(ItemsViewModel.image)
        Picasso.get().load(ItemsViewModel.url).into(holder.imageView)
        // sets the text to the textview from our itemHolder class
        holder.textView.text = ItemsViewModel.text

        holder?.textView?.setOnClickListener {
            Log.d("Tk->",ItemsViewModel.text)
        }

        holder?.itemView?.setOnClickListener{
            Log.d("Tk->",ItemsViewModel.text)

            Log.d("item-",isPresent.toString())


            if(isPresent.contains(position))
            {
                isPresent.remove(position)
                holder.itemView.setBackgroundColor(Color.CYAN)
            }
            else{
                isPresent.add(position)
                holder.itemView.setBackgroundColor(Color.WHITE)
            }

            Log.d("item-",isPresent.toString())
        }




    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {

        val imageView: ImageView = itemView.findViewById(R.id.imageview)
        val textView: TextView = itemView.findViewById(R.id.textView)

    }
}


