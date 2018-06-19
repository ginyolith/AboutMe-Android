package ginyolith.aboutme.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ginyolith.aboutme.databinding.ViewCategoryBinding
import ginyolith.aboutme.model.Category

class CategoryListAdapter(
        var dataList : MutableList<Category>)
        : RecyclerView.Adapter<CategoryListAdapter.BindingHolder>() {

    lateinit var onClick : (Category) -> Unit
    lateinit var onDeleteButtonClick : (Category) -> Unit
    override fun getItemCount(): Int = dataList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingHolder {
        val inflater : LayoutInflater = LayoutInflater.from(parent.context)
        val binding = ViewCategoryBinding.inflate(inflater, parent, false)
        return BindingHolder(binding)
    }

    override fun onBindViewHolder(holder: BindingHolder, position: Int) {
        val data =dataList[position]
        holder.binding.category = data
        holder.binding.listCategoryLayout.setOnClickListener {
            onClick(data)
        }

        holder.binding.buttonCategoryDelete.setOnClickListener {
            onDeleteButtonClick(data)
        }
    }

    class BindingHolder(var binding: ViewCategoryBinding) : RecyclerView.ViewHolder(binding.root)
}