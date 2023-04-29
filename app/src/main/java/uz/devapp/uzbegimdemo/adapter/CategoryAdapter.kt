package uz.devapp.uzbegimdemo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.devapp.uzbegimdemo.data.model.ProductModel
import uz.devapp.uzbegimdemo.databinding.ItemCategoryBinding

interface CategoryAdapterCallback {
    fun onSelectCategory(item: ProductModel)
}

class CategoryAdapter(val items: List<ProductModel>, val callback: CategoryAdapterCallback) : RecyclerView.Adapter<CategoryAdapter.Vh>() {

    inner class Vh(val binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false))
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