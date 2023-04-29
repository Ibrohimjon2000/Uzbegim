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
import uz.devapp.uzbegimdemo.adapter.ProductAdapter2
import uz.devapp.uzbegimdemo.adapter.ProductAdapter2Callback
import uz.devapp.uzbegimdemo.data.model.ProductModel
import uz.devapp.uzbegimdemo.databinding.FragmentProductDetailBinding

class ProductDetailFragment : Fragment() {
    lateinit var binding: FragmentProductDetailBinding
    val productList = mutableListOf(
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
        binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        (requireActivity() as AppCompatActivity).findViewById<BottomNavigationView>(R.id.bottomNavigationView).visibility =
            View.GONE

        binding.back.setOnClickListener {
            requireActivity().findNavController(R.id.mainFragmentContainerView).popBackStack()
        }

        binding.rv.adapter = ProductAdapter2(productList, object : ProductAdapter2Callback {
            override fun onSelectCategory(item: ProductModel) {
                requireActivity().findNavController(R.id.mainFragmentContainerView)
                    .navigate(R.id.productDetailFragment)
            }
        })

        binding.trash.setOnClickListener {
            requireActivity().findNavController(R.id.mainFragmentContainerView)
                .navigate(R.id.cartFragment)
        }

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = ProductDetailFragment()
    }
}