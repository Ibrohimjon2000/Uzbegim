package uz.devapp.uzbegimdemo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.devapp.uzbegimdemo.data.model.ProductModel
import uz.devapp.uzbegimdemo.databinding.ItemCartBinding

class CartAdapter(val items: List<ProductModel>, val bool:Boolean) : RecyclerView.Adapter<CartAdapter.Vh>() {

    inner class Vh(val binding: ItemCartBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemCartBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        val item = items[position]
        if (bool){
            holder.binding.checkbox.visibility=View.VISIBLE
            holder.binding.cardImg.visibility=View.GONE
        }else{
            holder.binding.checkbox.visibility=View.GONE
            holder.binding.cardImg.visibility=View.VISIBLE
        }
    }
}