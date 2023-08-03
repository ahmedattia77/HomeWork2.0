package com.example.homework20.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.homework20.R
import com.example.homework20.listener.VacationOnClickListener
import com.example.homework20.models.VacationModel


class VacationAdapter(private val vList: List<VacationModel> ,var vListener: VacationOnClickListener)
    : RecyclerView.Adapter<VacationAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.single_item, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val currentItem = vList[position]
//        holder.imageView.setImageResource(currentItem.image)

        // sets the text to the textview from our itemHolder class
        currentItem.image?.let { holder.image.setImageResource(it) }
        holder.title.text = currentItem.title
        holder.review.text = currentItem.review

        holder.itemView.rootView.setOnClickListener {
            vListener.onItemClick(currentItem)
        }
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return vList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val image: ImageView = itemView.findViewById(R.id.image)
        val title: TextView = itemView.findViewById(R.id.title_)
        val review: TextView = itemView.findViewById(R.id.review)

    }


}

