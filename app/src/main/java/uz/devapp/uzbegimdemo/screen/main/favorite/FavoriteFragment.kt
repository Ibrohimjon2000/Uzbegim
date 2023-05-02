package uz.devapp.uzbegimdemo.screen.main.favorite

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
import uz.devapp.uzbegimdemo.adapter.ProductListAdapter
import uz.devapp.uzbegimdemo.adapter.ProductListAdapterCallback
import uz.devapp.uzbegimdemo.data.model.ProductListModel
import uz.devapp.uzbegimdemo.data.model.request.FavoriteRequest
import uz.devapp.uzbegimdemo.data.repository.sealed.DataResult
import uz.devapp.uzbegimdemo.databinding.FragmentFavoriteBinding
import uz.devapp.uzbegimdemo.screen.main.home.HomeViewModel
import uz.devapp.uzbegimdemo.utils.Constants
import uz.devapp.uzbegimdemo.utils.PrefUtils
import uz.devapp.uzbegimdemo.utils.SelectButton

@AndroidEntryPoint
class FavoriteFragment : Fragment() {
    lateinit var binding: FragmentFavoriteBinding
    private val viewModel: HomeViewModel by viewModels()
    var request = FavoriteRequest()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        (requireActivity() as AppCompatActivity).findViewById<BottomNavigationView>(R.id.bottomNavigationView).visibility =
            View.VISIBLE
        binding.apply {
            addFavorite.setOnClickListener {
                requireActivity().findNavController(R.id.mainFragmentContainerView)
                    .navigate(R.id.productsFragment)
            }

            viewModel.progressLiveData.observe(requireActivity()) {
                swipe.isRefreshing = it
            }

            swipe.setOnRefreshListener {
                loadData()
            }

            viewModel.productListLiveData.observe(requireActivity()) {
                when (it) {
                    is DataResult.Error -> {
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    }
                    is DataResult.LoadingHide -> {

                    }
                    is DataResult.LoadingShow -> {

                    }
                    is DataResult.Success -> {
                        if (it.result.isEmpty()) {
                            binding.empty.visibility = View.VISIBLE
                        } else {
                            binding.empty.visibility = View.GONE
                        }
                        binding.rv.adapter =
                            ProductListAdapter(it.result, object : ProductListAdapterCallback {
                                override fun onSelectCategory(item: ProductListModel) {
                                    SelectButton.onClickItem(item, requireActivity())
                                }

                                override fun onSelectFavorite(
                                    item: ProductListModel,
                                    holder: ProductListAdapter.Vh
                                ) {
                                    SelectButton.onClickFavorite(item,holder)

                                    swipe.isRefreshing=true
                                    request = FavoriteRequest(id = item.id)
                                    viewModel.setFavorite(request)
                                    loadData()
                                    swipe.isRefreshing=false
                                }

                                override fun onSelectCart(
                                    item: ProductListModel,
                                    holder: ProductListAdapter.Vh
                                ) {
                                    SelectButton.onClickCartButton(item,holder,requireContext())
                                }
                            })
                    }
                }
            }
        }
        loadData()
        return binding.root
    }

    private fun loadData() {
        viewModel.productFavoriteList()
    }

    companion object {
        @JvmStatic
        fun newInstance() = FavoriteFragment()
    }
}