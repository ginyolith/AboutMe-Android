package ginyolith.aboutme.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import ginyolith.aboutme.databinding.ViewDetailBinding
import ginyolith.aboutme.model.Detail

class DetailListAdapter(
        var dataList : MutableList<Detail>)
        : RecyclerView.Adapter<DetailListAdapter.BindingHolder>() {

    lateinit var onClick : (Detail) -> Unit
    lateinit var onDeleteButtonClick : (Detail, Int) -> Unit
    override fun getItemCount(): Int = dataList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingHolder {
        val inflater : LayoutInflater = LayoutInflater.from(parent.context)
        val binding = ViewDetailBinding.inflate(inflater, parent, false)
        return BindingHolder(binding)
    }

    override fun onBindViewHolder(holder: BindingHolder, position: Int) {
        val data = dataList[position]
        holder.binding.detail = data
        holder.binding.listDetailLayout.setOnClickListener {
            onClick(data)
        }

        holder.binding.buttonDetailDelete.setOnClickListener {
            onDeleteButtonClick(data, holder.layoutPosition)
        }
    }

    class BindingHolder(var binding: ViewDetailBinding) : RecyclerView.ViewHolder(binding.root)

}