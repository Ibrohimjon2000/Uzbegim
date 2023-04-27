package uz.devapp.uzbegimdemo.screen.main.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import uz.devapp.uzbegimdemo.R
import uz.devapp.uzbegimdemo.adapter.MyAddressAdapter
import uz.devapp.uzbegimdemo.adapter.MyAddressAdapterCallback
import uz.devapp.uzbegimdemo.adapter.ProductAdapter
import uz.devapp.uzbegimdemo.adapter.ProductAdapterCallback
import uz.devapp.uzbegimdemo.data.ProductModel
import uz.devapp.uzbegimdemo.databinding.FragmentMyAddressBinding

class MyAddressFragment : Fragment() {
    lateinit var binding: FragmentMyAddressBinding
    var addressList= listOf(
        ProductModel(1),
        ProductModel(1),
        ProductModel(1),
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentMyAddressBinding.inflate(inflater,container,false)
        binding.back.setOnClickListener {
            requireActivity().findNavController(R.id.mainFragmentContainerView).popBackStack()
        }
        binding.rv.adapter=MyAddressAdapter(addressList,object :MyAddressAdapterCallback{
            override fun onSelectCategory(item: ProductModel) {

            }
        })

        binding.addAddress.setOnClickListener {
            requireActivity().findNavController(R.id.mainFragmentContainerView)
                .navigate(R.id.addAddressFragment)
        }

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = MyAddressFragment()
    }
}