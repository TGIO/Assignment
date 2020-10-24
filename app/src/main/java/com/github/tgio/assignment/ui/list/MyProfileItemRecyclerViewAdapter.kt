package com.github.tgio.assignment.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.tgio.assignment.R

class MyProfileItemRecyclerViewAdapter(
    private val listener: ProfileItemClickListener
) : RecyclerView.Adapter<MyProfileItemRecyclerViewAdapter.ViewHolder>() {

    private val values: ArrayList<String> = arrayListOf()

    fun setValues(newItems: List<String>) {
        values.clear()
        values.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_profile, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.contentView.text = item
        holder.contentView.setOnClickListener {
            listener.onProfileClicked(item)
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val contentView: TextView = view.findViewById(R.id.content)

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }
}