package uz.devapp.uzbegimdemo.screen.main.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import uz.devapp.uzbegimdemo.R
import uz.devapp.uzbegimdemo.databinding.FragmentCheckoutBinding

class CheckoutFragment : Fragment() {
    lateinit var binding:FragmentCheckoutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentCheckoutBinding.inflate(inflater,container,false)
        binding.back.setOnClickListener {
            requireActivity().findNavController(R.id.mainFragmentContainerView).popBackStack()
        }
        binding.selectAddress.setOnClickListener {
            requireActivity().findNavController(R.id.mainFragmentContainerView)
                .navigate(R.id.myAddressFragment)
        }
        binding.btnConfirm.setOnClickListener {
            requireActivity().findNavController(R.id.mainFragmentContainerView)
                .navigate(R.id.orderDetailFragment)
        }
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = CheckoutFragment()
    }
}