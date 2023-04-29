package uz.devapp.uzbegimdemo.screen.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import uz.devapp.uzbegimdemo.R
import uz.devapp.uzbegimdemo.adapter.CartAdapter
import uz.devapp.uzbegimdemo.data.model.ProductModel
import uz.devapp.uzbegimdemo.databinding.FragmentCartBinding

class CartFragment : Fragment() {
    lateinit var binding: FragmentCartBinding
    lateinit var cartAdapter: CartAdapter
    var bool = true
    val cartList = listOf(
        ProductModel(1),
        ProductModel(1),
        ProductModel(1),
        ProductModel(1),
        ProductModel(1),
        ProductModel(1),
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCartBinding.inflate(inflater, container, false)
        (requireActivity() as AppCompatActivity).findViewById<BottomNavigationView>(R.id.bottomNavigationView).visibility =
            View.GONE

        binding.back.setOnClickListener {
            requireActivity().findNavController(R.id.mainFragmentContainerView).popBackStack()
        }

        cartAdapter = CartAdapter(cartList, !bool)
        binding.rv.adapter = cartAdapter

        binding.trash.setOnClickListener {
            if (bool) {
                cartAdapter = CartAdapter(cartList, bool)
                cartAdapter.notifyDataSetChanged()
                binding.rv.adapter = cartAdapter
                binding.trash.setImageResource(R.drawable.close_circle)
                binding.lyPrice.visibility = View.GONE
                binding.lyKeshbek.visibility = View.GONE
                binding.placeOrder.visibility = View.GONE
                binding.deleteCart.visibility = View.VISIBLE
                bool = false
            } else {
                cartAdapter = CartAdapter(cartList, bool)
                cartAdapter.notifyDataSetChanged()
                binding.rv.adapter = cartAdapter
                binding.trash.setImageResource(R.drawable.trash)
                binding.lyPrice.visibility = View.VISIBLE
                binding.lyKeshbek.visibility = View.VISIBLE
                binding.placeOrder.visibility = View.VISIBLE
                binding.deleteCart.visibility = View.GONE
                bool = true
            }
        }

        binding.placeOrder.setOnClickListener {
            requireActivity().findNavController(R.id.mainFragmentContainerView)
                .navigate(R.id.checkoutFragment)
        }

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = CartFragment()
    }
}