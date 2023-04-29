package uz.devapp.uzbegimdemo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.devapp.uzbegimdemo.data.model.ProductModel
import uz.devapp.uzbegimdemo.databinding.ItemOrderBinding

interface OrderAdapterCallback {
    fun onSelectCategory(item: ProductModel)
    fun onSelectText(item: ProductModel, holder: OrderAdapter.Vh)
}

class OrderAdapter(val items: List<ProductModel>, val callback: OrderAdapterCallback) :
    RecyclerView.Adapter<OrderAdapter.Vh>() {

    inner class Vh(val binding: ItemOrderBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemOrderBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        val item = items[position]
        holder.binding.tvDetail.setOnClickListener {
            callback.onSelectCategory(item)
        }
        holder.binding.copy.setOnClickListener {
            callback.onSelectText(item,holder)
        }
    }
}