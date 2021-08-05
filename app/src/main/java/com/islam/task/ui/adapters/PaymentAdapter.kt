package com.islam.task.ui.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.islam.task.R
import com.islam.task.data.network.response.Applicable
import com.islam.task.databinding.OneItemListBinding

class PaymentAdapter(
    private var list: MutableList<Applicable>
) : RecyclerView.Adapter<PaymentAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            OneItemListBinding.bind(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.one_item_list, parent, false)
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val listItems = list[position]
        holder.bind(listItems)

    }

    inner class ViewHolder(itemView: OneItemListBinding) : RecyclerView.ViewHolder(itemView.root) {
        private var label: TextView = itemView.label
        private var logo: ImageView = itemView.logo

        fun bind(listItems: Applicable) {
            label.text = listItems.code

            Glide.with(itemView.context).load(Uri.parse(listItems.links.logo))
                .placeholder(R.drawable.ic_language)
                .thumbnail(0.1f)
                .into(logo)

        }
    }

}