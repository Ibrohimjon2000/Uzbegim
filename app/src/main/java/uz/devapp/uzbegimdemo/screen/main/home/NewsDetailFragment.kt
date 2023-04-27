package uz.devapp.uzbegimdemo.screen.main.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import uz.devapp.uzbegimdemo.R
import uz.devapp.uzbegimdemo.databinding.FragmentNewsDetailBinding

class NewsDetailFragment : Fragment() {
    lateinit var binding:FragmentNewsDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentNewsDetailBinding.inflate(inflater,container,false)
        binding.back.setOnClickListener {
            requireActivity().findNavController(R.id.mainFragmentContainerView).popBackStack()
        }
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = NewsDetailFragment()
    }
}