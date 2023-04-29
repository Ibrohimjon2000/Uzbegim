package uz.devapp.uzbegimdemo.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.devapp.uzbegimdemo.data.model.ProductModel
import uz.devapp.uzbegimdemo.databinding.ItemProduct2Binding

interface ProductAdapter2Callback {
    fun onSelectCategory(item: ProductModel)
}

class ProductAdapter2(val items: List<ProductModel>, val callback: ProductAdapter2Callback) :
    RecyclerView.Adapter<ProductAdapter2.Vh>() {

    inner class Vh(val binding: ItemProduct2Binding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemProduct2Binding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        val item = items[position]
        holder.binding.oldPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        holder.itemView.setOnClickListener {
            callback.onSelectCategory(item)
        }
    }
}