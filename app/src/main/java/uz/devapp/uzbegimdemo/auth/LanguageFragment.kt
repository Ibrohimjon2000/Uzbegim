package uz.devapp.uzbegimdemo.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import uz.devapp.uzbegimdemo.R
import uz.devapp.uzbegimdemo.databinding.FragmentLanguageBinding

@AndroidEntryPoint
class LanguageFragment : Fragment() {
    lateinit var binding: FragmentLanguageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentLanguageBinding.inflate(inflater,container,false)
        binding.btnUz.setOnClickListener {
            requireActivity().findNavController(R.id.fragmentContainerView)
                .navigate(R.id.action_languageFragment_to_entranceNumberFragment)
        }
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = LanguageFragment()
    }
}