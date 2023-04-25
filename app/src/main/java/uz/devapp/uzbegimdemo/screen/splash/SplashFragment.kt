package uz.devapp.uzbegimdemo.screen.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import uz.devapp.uzbegimdemo.R
import uz.devapp.uzbegimdemo.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {
    lateinit var binding: FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        binding.root.postDelayed({
            requireActivity().findNavController(R.id.fragmentContainerView)
                .navigate(R.id.action_splashFragment_to_languageFragment)
        }, 2000)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = SplashFragment()
    }
}