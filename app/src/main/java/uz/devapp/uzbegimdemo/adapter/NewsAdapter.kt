package uz.devapp.uzbegimdemo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import uz.devapp.uzbegimdemo.R
import uz.devapp.uzbegimdemo.data.ProductModel
import uz.devapp.uzbegimdemo.databinding.ItemCartBinding
import uz.devapp.uzbegimdemo.databinding.ItemCategoryBinding
import uz.devapp.uzbegimdemo.databinding.ItemNewsBinding

interface NewsAdapterCallback {
    fun onSelectCategory(item: ProductModel)
}

class NewsAdapter(val items: List<ProductModel>,val callback: NewsAdapterCallback) : RecyclerView.Adapter<NewsAdapter.Vh>() {

    inner class Vh(val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false))
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