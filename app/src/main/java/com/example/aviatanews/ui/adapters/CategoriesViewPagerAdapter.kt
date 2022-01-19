package com.example.aviatanews.ui.adapters

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.aviatanews.R


class CategoriesViewPagerAdapter(val listCategories: ArrayList<String>?) :
    RecyclerView.Adapter<CategoriesViewPagerAdapter.CategoriesButtons>() {
    private var selectedPos: Int? = null
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoriesButtons =
        CategoriesButtons(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_categories_button, parent, false)
        )

    override fun onBindViewHolder(
        holder: CategoriesButtons,
        position: Int
    ) {
        if (selectedPos != null)
            holder.itemView.isSelected = selectedPos == position;
        return holder.bind(position)
    }

    override fun getItemCount(): Int = listCategories!!.size

    inner class CategoriesButtons(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val categoryButton: Button = itemView.findViewById(R.id.buttonCategories)
            fun bind(position: Int) {
            categoryButton.text = listCategories!![position]
            itemView.setOnClickListener {
                selectedPos?.let { position -> notifyItemChanged(position) }
                selectedPos = layoutPosition
                notifyItemChanged(selectedPos!!)
            }
            if (itemView.isSelected){
                (itemView as Button).setTextColor(Color.WHITE)
            }else{
                (itemView as Button).setTextColor(Color.BLACK)
            }
        }
    }

}