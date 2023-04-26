package uz.devapp.uzbegimdemo.screen.main.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import uz.devapp.uzbegimdemo.R
import uz.devapp.uzbegimdemo.adapter.CategoryAdapter
import uz.devapp.uzbegimdemo.adapter.CategoryAdapterCallback
import uz.devapp.uzbegimdemo.adapter.ProductAdapter
import uz.devapp.uzbegimdemo.data.ProductModel
import uz.devapp.uzbegimdemo.databinding.FragmentCategoryBinding

private const val ARG_PARAM1 = "param1"

class CategoryFragment : Fragment() {
    private var param1: String? = null
    lateinit var binding: FragmentCategoryBinding

    var itemList= listOf(
        ProductModel(1),
        ProductModel(1),
        ProductModel(1),
        ProductModel(1),
        ProductModel(1),
        ProductModel(1),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentCategoryBinding.inflate(inflater,container,false)

            binding.rv.adapter=CategoryAdapter(itemList,object :CategoryAdapterCallback{
                override fun onSelectCategory(item: ProductModel) {
                    requireActivity().findNavController(R.id.mainFragmentContainerView)
                        .navigate(R.id.productsFragment)
                }
            })

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String) =
            CategoryFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }
}