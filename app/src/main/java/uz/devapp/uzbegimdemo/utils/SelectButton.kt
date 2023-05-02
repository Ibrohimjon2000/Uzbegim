package uz.devapp.uzbegimdemo.utils

import android.content.Context
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import com.google.android.material.button.MaterialButton
import uz.devapp.uzbegimdemo.R
import uz.devapp.uzbegimdemo.adapter.ProductAdapterVertical
import uz.devapp.uzbegimdemo.adapter.ProductListAdapter
import uz.devapp.uzbegimdemo.data.model.ProductListModel

object SelectButton {
    fun onClickItem(item: ProductListModel, context: FragmentActivity) {
        val bundle = Bundle()
        bundle.putSerializable(Constants.EXTRA_DATA, item)
        context.findNavController(R.id.mainFragmentContainerView)
            .navigate(R.id.productDetailFragment, bundle)
    }

    fun onClickFavorite(item: ProductListModel, holder: ProductListAdapter.Vh) {
        PrefUtils.setFavorites(item)

        if (PrefUtils.checkFavorite(item)) {
            holder.binding.favorite.setImageResource(R.drawable.baseline_favorite_24)
        } else {
            holder.binding.favorite.setImageResource(R.drawable.heart)
        }
    }

    fun onClickCartButton(item: ProductListModel, holder: ProductListAdapter.Vh, context: Context) {
        if (item.bool) {
            holder.binding.addCart.text = "Savatga"
            holder.binding.addCart.icon =
                ContextCompat.getDrawable(
                    context,
                    R.drawable.shopping_cart
                )
            holder.binding.addCart.iconGravity =
                MaterialButton.ICON_GRAVITY_TEXT_START
            item.bool = false
        } else {
            holder.binding.addCart.text = "Qo'shilgan"
            holder.binding.addCart.icon =
                ContextCompat.getDrawable(
                    context,
                    R.drawable.done
                )
            holder.binding.addCart.iconGravity =
                MaterialButton.ICON_GRAVITY_TEXT_END
            item.bool = true
        }
    }

    fun onClickFavoriteVertical(item: ProductListModel, holder: ProductAdapterVertical.Vh) {
        PrefUtils.setFavorites(item)

        if (PrefUtils.checkFavorite(item)) {
            holder.binding.favorite.setImageResource(R.drawable.baseline_favorite_24)
        } else {
            holder.binding.favorite.setImageResource(R.drawable.heart)
        }
    }

    fun onClickCartButtonVertical(item: ProductListModel, holder: ProductAdapterVertical.Vh, context: Context) {
        if (item.bool) {
            holder.binding.addCart.text = "Savatga"
            holder.binding.addCart.icon =
                ContextCompat.getDrawable(
                    context,
                    R.drawable.shopping_cart
                )
            holder.binding.addCart.iconGravity =
                MaterialButton.ICON_GRAVITY_TEXT_START
            item.bool = false
        } else {
            holder.binding.addCart.text = "Qo'shilgan"
            holder.binding.addCart.icon =
                ContextCompat.getDrawable(
                    context,
                    R.drawable.done
                )
            holder.binding.addCart.iconGravity =
                MaterialButton.ICON_GRAVITY_TEXT_END
            item.bool = true
        }
    }
}