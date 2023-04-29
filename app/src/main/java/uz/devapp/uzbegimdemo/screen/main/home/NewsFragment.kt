package uz.devapp.uzbegimdemo.screen.main.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import uz.devapp.uzbegimdemo.R
import uz.devapp.uzbegimdemo.adapter.NewsAdapter
import uz.devapp.uzbegimdemo.adapter.NewsAdapterCallback
import uz.devapp.uzbegimdemo.data.model.ProductModel
import uz.devapp.uzbegimdemo.databinding.FragmentNewsBinding

class NewsFragment : Fragment() {
    lateinit var binding: FragmentNewsBinding
    val newsList = listOf(
        ProductModel(1),
        ProductModel(1),
        ProductModel(1),
        ProductModel(1),
        ProductModel(1),
        ProductModel(1),
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsBinding.inflate(inflater, container, false)
        (requireActivity() as AppCompatActivity).findViewById<BottomNavigationView>(R.id.bottomNavigationView).visibility =
            View.GONE

        binding.back.setOnClickListener {
            requireActivity().findNavController(R.id.mainFragmentContainerView).popBackStack()
        }

        binding.rv.adapter = NewsAdapter(newsList, object : NewsAdapterCallback {
            override fun onSelectCategory(item: ProductModel) {
                requireActivity().findNavController(R.id.mainFragmentContainerView)
                    .navigate(R.id.newsDetailFragment)
            }
        })
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = NewsFragment()
    }
}