package uz.devapp.uzbegimdemo.adapter

import android.graphics.Color
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.devapp.uzbegimdemo.R
import uz.devapp.uzbegimdemo.data.model.ProductListModel
import uz.devapp.uzbegimdemo.data.model.ProductModel
import uz.devapp.uzbegimdemo.databinding.ItemProductBinding
import uz.devapp.uzbegimdemo.utils.PrefUtils
import uz.devapp.uzbegimdemo.utils.loadImage

interface ProductListAdapterCallback {
    fun onSelectCategory(item: ProductListModel)
    fun onSelectFavorite(item: ProductListModel, holder: ProductListAdapter.Vh)
    fun onSelectCart(item: ProductListModel,holder: ProductListAdapter.Vh)
}

class ProductListAdapter(
    val items: List<ProductListModel>,
    val callback: ProductListAdapterCallback
) : RecyclerView.Adapter<ProductListAdapter.Vh>() {

    inner class Vh(val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        val item = items[position]
        holder.binding.oldPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        holder.binding.tvKeshbek.text = item.cashback.toString()
        holder.binding.oldPrice.text = item.oldPrice.toString() + " " + item.currency.nameUz
        holder.binding.price.text = item.price.toString() + " " + item.currency.nameUz
        holder.binding.tvTitle.text = item.nameUz
        holder.binding.unity.text = item.unity.nameUz
        holder.binding.image.loadImage(item.mainImage)

        if (PrefUtils.checkFavorite(item)) {
            holder.binding.favorite.setImageResource(R.drawable.baseline_favorite_24)
        } else {
            holder.binding.favorite.setImageResource(R.drawable.heart)
        }

        if (item.inStock) {
            holder.binding.inStock.visibility = View.GONE
            holder.binding.addCart.setBackgroundColor(Color.parseColor("#C30019"))
            holder.binding.addCart.isClickable=true
            holder.itemView.setOnClickListener {
                callback.onSelectCategory(item)
            }
            holder.binding.favorite.setOnClickListener {
                callback.onSelectFavorite(item,holder)
            }
            holder.binding.addCart.setOnClickListener {
                callback.onSelectCart(item,holder)
            }
        } else {
            holder.binding.inStock.visibility = View.VISIBLE
            holder.binding.addCart.setBackgroundColor(Color.parseColor("#C8C8C8"))
            holder.binding.addCart.isClickable=false
        }

        if (item.oldPrice.toString() == "0") {
            holder.binding.oldPrice.visibility = View.INVISIBLE
        } else {
            holder.binding.oldPrice.visibility = View.VISIBLE
        }

        if (item.isNew) {
            holder.binding.tvSale.text = "New"
        }

        if (item.oldPrice > 0) {
            holder.binding.tvSale.text =
                "SALE " + (100 - (item.price * 100) / item.oldPrice).toString() + "%"
        }

        if (item.isNew || item.oldPrice > 0) {
            holder.binding.lySale.visibility = View.VISIBLE
        } else {
            holder.binding.lySale.visibility = View.GONE
        }
    }
}