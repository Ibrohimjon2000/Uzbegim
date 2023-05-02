package uz.devapp.uzbegimdemo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.devapp.uzbegimdemo.data.model.NewsModel
import uz.devapp.uzbegimdemo.databinding.ItemNewsBinding
import uz.devapp.uzbegimdemo.utils.loadImage
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

interface NewsAdapterCallback {
    fun onSelectCategory(item: NewsModel)
}

class NewsAdapter(val items: List<NewsModel>, val callback: NewsAdapterCallback) :
    RecyclerView.Adapter<NewsAdapter.Vh>() {

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
        holder.binding.image.loadImage(item.image)
        holder.binding.tvTitle.text = item.titleUz
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSSSSS")
        var convertedDate: Date
        try {
            convertedDate = dateFormat.parse(item.createdAt)

            val formateDate = SimpleDateFormat("dd.MM.YYYY HH:mm").format(convertedDate)
            holder.binding.tvTime.text = formateDate
        } catch (e: ParseException) {
            e.printStackTrace()
        }
    }
}