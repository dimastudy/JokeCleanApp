package com.justadroiddev.wordscleanarchapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.justadroiddev.wordscleanarchapp.R
import com.justadroiddev.wordscleanarchapp.databinding.ItemCategoryBinding

class CategoriesListAdapter(
    private val categoryClickListener: CategoryClickListener
) : RecyclerView.Adapter<CategoriesListAdapter.CategoriesListViewHolder>() {

    private var _listCategories = ArrayList<String>()
    private val listCategories: List<String>
        get() = _listCategories


    class CategoriesListViewHolder(private val binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(category: String){
            binding.apply {
                textCategory.text = category
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesListViewHolder {
        val binding = ItemCategoryBinding.bind(LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false))
        return CategoriesListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoriesListViewHolder, position: Int) {
        val category = listCategories[position]
        holder.bind(category)
        holder.itemView.setOnClickListener {
            categoryClickListener.onClick(category)
        }
    }

    fun updateList(list: List<String>){
        _listCategories.clear()
        _listCategories.addAll(list)
        this.notifyDataSetChanged()
    }

    override fun getItemCount(): Int =
        listCategories.size


    class CategoryClickListener(private val clickListener: (category: String) -> Unit){
        fun onClick(category: String) = clickListener.invoke(category)
    }


}