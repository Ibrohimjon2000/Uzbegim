package uz.devapp.uzbegimdemo.screen.main.cart.checkout.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import uz.devapp.uzbegimdemo.R
import uz.devapp.uzbegimdemo.adapter.OrderInformationAdapter
import uz.devapp.uzbegimdemo.data.model.ProductModel
import uz.devapp.uzbegimdemo.databinding.FragmentOrderInformationBinding

class OrderInformationFragment : Fragment() {
    lateinit var binding: FragmentOrderInformationBinding
    val itemList = listOf(
        ProductModel(1),
        ProductModel(1),
        ProductModel(1),
        ProductModel(1),
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOrderInformationBinding.inflate(inflater, container, false)
        binding.back.setOnClickListener {
            requireActivity().findNavController(R.id.mainFragmentContainerView).popBackStack()
        }
        binding.rv.adapter = OrderInformationAdapter(itemList)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = OrderInformationFragment()
    }
}