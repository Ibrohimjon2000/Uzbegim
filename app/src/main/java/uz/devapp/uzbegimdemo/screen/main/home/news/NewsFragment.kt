package uz.devapp.uzbegimdemo.screen.main.home.news

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import uz.devapp.uzbegimdemo.R
import uz.devapp.uzbegimdemo.adapter.NewsAdapter
import uz.devapp.uzbegimdemo.adapter.NewsAdapterCallback
import uz.devapp.uzbegimdemo.data.model.NewsModel
import uz.devapp.uzbegimdemo.data.model.ProductModel
import uz.devapp.uzbegimdemo.data.repository.sealed.DataResult
import uz.devapp.uzbegimdemo.databinding.FragmentNewsBinding
import uz.devapp.uzbegimdemo.screen.main.home.HomeViewModel
import uz.devapp.uzbegimdemo.utils.Constants

@AndroidEntryPoint
class NewsFragment : Fragment() {
    lateinit var binding: FragmentNewsBinding
    private val viewModel: HomeViewModel by viewModels()

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

        viewModel.progressLiveData.observe(requireActivity()) {
            binding.swipe.isRefreshing = it
        }

        binding.swipe.setOnRefreshListener {
            viewModel.getNews()
        }


        viewModel.newsListLiveData.observe(requireActivity()){
            when(it){
                is DataResult.Error -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
                is DataResult.LoadingHide -> {

                }
                is DataResult.LoadingShow -> {

                }
                is DataResult.Success -> {
                    binding.rv.adapter = NewsAdapter(it.result, object : NewsAdapterCallback {
                        override fun onSelectCategory(item: NewsModel) {
                            val bundle=Bundle()
                            bundle.putInt(Constants.EXTRA_DATA,item.id)
                            requireActivity().findNavController(R.id.mainFragmentContainerView)
                                .navigate(R.id.newsDetailFragment,bundle)
                        }
                    })
                }
            }
        }
        viewModel.getNews()
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = NewsFragment()
    }
}