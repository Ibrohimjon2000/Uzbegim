package uz.devapp.uzbegimdemo.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import uz.devapp.uzbegimdemo.R
import uz.devapp.uzbegimdemo.databinding.FragmentPhoneConfirmationBinding

class PhoneConfirmationFragment : Fragment() {
    lateinit var binding: FragmentPhoneConfirmationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPhoneConfirmationBinding.inflate(inflater, container, false)
        binding.toolbar.setNavigationOnClickListener {
            requireActivity().findNavController(R.id.fragmentContainerView).popBackStack()
        }
        binding.btnConfirm.setOnClickListener {
            if (binding.otpView.text!!.isNotEmpty()) {
                binding.btnConfirm.visibility = View.GONE
                binding.btnLoad.visibility = View.VISIBLE
                requireActivity().findNavController(R.id.fragmentContainerView)
                    .navigate(R.id.action_phoneConfirmationFragment_to_mainFragment)
            }
        }


        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = PhoneConfirmationFragment()
    }
}