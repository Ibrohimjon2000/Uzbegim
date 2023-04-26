package uz.devapp.uzbegimdemo.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.devapp.uzbegimdemo.data.ProductModel
import uz.devapp.uzbegimdemo.databinding.ItemProductBinding

interface ProductAdapterCallback {
    fun onSelectCategory(item: ProductModel)
}
class ProductAdapter(val items:List<ProductModel>,val callback: ProductAdapterCallback):RecyclerView.Adapter<ProductAdapter.Vh>() {

    inner class Vh(val binding: ItemProductBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemProductBinding.inflate(LayoutInflater.from(parent.context),parent,false))
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