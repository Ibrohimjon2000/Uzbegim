package uz.devapp.uzbegimdemo.screen.main.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import uz.devapp.uzbegimdemo.R
import uz.devapp.uzbegimdemo.adapter.ProductAdapter
import uz.devapp.uzbegimdemo.adapter.ProductAdapterCallback
import uz.devapp.uzbegimdemo.data.model.ProductModel
import uz.devapp.uzbegimdemo.databinding.FragmentProductsBinding

class ProductsFragment : Fragment() {
    lateinit var binding: FragmentProductsBinding
    var productList= listOf(
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
        binding= FragmentProductsBinding.inflate(inflater,container,false)
        (requireActivity() as AppCompatActivity).findViewById<BottomNavigationView>(R.id.bottomNavigationView).visibility =
            View.GONE

        binding.rv.adapter=ProductAdapter(productList,object :ProductAdapterCallback{
            override fun onSelectCategory(item: ProductModel) {
                requireActivity().findNavController(R.id.mainFragmentContainerView)
                    .navigate(R.id.productDetailFragment)
            }
        })

        binding.back.setOnClickListener {
            requireActivity().findNavController(R.id.mainFragmentContainerView).popBackStack()
        }

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = ProductsFragment()
    }
}