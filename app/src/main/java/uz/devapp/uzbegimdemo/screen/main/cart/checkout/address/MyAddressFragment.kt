package uz.devapp.uzbegimdemo.screen.main.cart.checkout.address

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import uz.devapp.uzbegimdemo.R
import uz.devapp.uzbegimdemo.adapter.MyAddressAdapter
import uz.devapp.uzbegimdemo.adapter.MyAddressAdapterCallback
import uz.devapp.uzbegimdemo.data.model.ProductModel
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
        (requireActivity() as AppCompatActivity).findViewById<BottomNavigationView>(R.id.bottomNavigationView).visibility =
            View.GONE
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