package uz.devapp.uzbegimdemo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.devapp.uzbegimdemo.data.ProductModel
import uz.devapp.uzbegimdemo.databinding.ItemAddressBinding

interface MyAddressAdapterCallback {
    fun onSelectCategory(item: ProductModel)
}

class MyAddressAdapter(val items: List<ProductModel>, val callback: MyAddressAdapterCallback) :
    RecyclerView.Adapter<MyAddressAdapter.Vh>() {

    inner class Vh(val binding: ItemAddressBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemAddressBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        val item = items[position]
        holder.itemView.setOnClickListener {
            callback.onSelectCategory(item)
        }
    }
}