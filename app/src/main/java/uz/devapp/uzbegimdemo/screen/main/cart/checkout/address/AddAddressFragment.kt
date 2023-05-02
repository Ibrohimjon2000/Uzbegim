package uz.devapp.uzbegimdemo.screen.main.cart.checkout.address

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import uz.devapp.uzbegimdemo.R
import uz.devapp.uzbegimdemo.databinding.FragmentAddAddressBinding

class AddAddressFragment : Fragment() {
    lateinit var binding:FragmentAddAddressBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentAddAddressBinding.inflate(inflater,container,false)
        binding.back.setOnClickListener {
            requireActivity().findNavController(R.id.mainFragmentContainerView).popBackStack()
        }
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = AddAddressFragment()
    }
}