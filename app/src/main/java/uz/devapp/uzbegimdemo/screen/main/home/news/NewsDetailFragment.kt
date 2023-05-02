package uz.devapp.uzbegimdemo.screen.main.home.news

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import uz.devapp.uzbegimdemo.R
import uz.devapp.uzbegimdemo.data.model.request.NewsRequest
import uz.devapp.uzbegimdemo.data.model.request.ProductDetailRequest
import uz.devapp.uzbegimdemo.data.repository.sealed.DataResult
import uz.devapp.uzbegimdemo.databinding.FragmentNewsDetailBinding
import uz.devapp.uzbegimdemo.screen.main.home.HomeViewModel
import uz.devapp.uzbegimdemo.utils.Constants
import uz.devapp.uzbegimdemo.utils.loadImage

private const val ARG_PARAM1 = Constants.EXTRA_DATA

@AndroidEntryPoint
class NewsDetailFragment : Fragment() {
    lateinit var binding: FragmentNewsDetailBinding
    private val viewModel: HomeViewModel by viewModels()
    var request = NewsRequest()
    private var param1: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getInt(ARG_PARAM1)
        }
        request = NewsRequest(id = param1!!)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsDetailBinding.inflate(inflater, container, false)
        binding.back.setOnClickListener {
            requireActivity().findNavController(R.id.mainFragmentContainerView).popBackStack()
        }

        viewModel.newsDetailLiveData.observe(requireActivity()) {
            when (it) {
                is DataResult.Error -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
                is DataResult.LoadingHide -> {

                }
                is DataResult.LoadingShow -> {

                }
                is DataResult.Success -> {
                    val result = it.result
                    binding.toolbarTitle.text = result.titleUz
                    binding.tvTitle.text = result.titleUz
                    binding.tvDescription.text = Html.fromHtml(result.contentUz)
                    binding.image.loadImage(result.image)
                }
            }
        }

        viewModel.getNewsDetail(request)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: Int) = NewsDetailFragment().apply {
            arguments = Bundle().apply {
                putInt(ARG_PARAM1, param1)
            }
        }
    }
}