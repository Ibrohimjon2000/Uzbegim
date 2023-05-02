package uz.devapp.uzbegimdemo.screen.main.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import uz.devapp.uzbegimdemo.R
import uz.devapp.uzbegimdemo.adapter.ProductListAdapter
import uz.devapp.uzbegimdemo.adapter.ProductListAdapterCallback
import uz.devapp.uzbegimdemo.data.model.ProductListModel
import uz.devapp.uzbegimdemo.data.model.request.FavoriteRequest
import uz.devapp.uzbegimdemo.data.repository.sealed.DataResult
import uz.devapp.uzbegimdemo.databinding.FragmentSearchBinding
import uz.devapp.uzbegimdemo.screen.main.home.HomeViewModel
import uz.devapp.uzbegimdemo.utils.SelectButton

@AndroidEntryPoint
class SearchFragment : Fragment() {
    lateinit var binding: FragmentSearchBinding
    private val viewModel: HomeViewModel by viewModels()
    var keyword = ""
    var request = FavoriteRequest()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)

        viewModel.productListLiveData.observe(requireActivity()) {
            when (it) {
                is DataResult.Error -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
                is DataResult.LoadingHide -> {
                    binding.progressBar.visibility = View.GONE
                }
                is DataResult.LoadingShow -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is DataResult.Success -> {
                    var productList = it.result
                    if (keyword.isEmpty()) {
                        productList = listOf()
                    }
                    if (productList.isEmpty()) {
                        binding.lottie.visibility = View.VISIBLE
                    } else {
                        binding.lottie.visibility = View.GONE
                    }
                    binding.rv.adapter =
                        ProductListAdapter(productList, object : ProductListAdapterCallback {
                            override fun onSelectCategory(item: ProductListModel) {
                                SelectButton.onClickItem(item, requireActivity())
                            }

                            override fun onSelectFavorite(
                                item: ProductListModel,
                                holder: ProductListAdapter.Vh
                            ) {
                                SelectButton.onClickFavorite(item, holder)
                                request = FavoriteRequest(id = item.id)
                                viewModel.setFavorite(request)
                            }

                            override fun onSelectCart(
                                item: ProductListModel,
                                holder: ProductListAdapter.Vh
                            ) {
                                SelectButton.onClickCartButton(item, holder, requireContext())
                            }
                        })
                }
            }
        }

        binding.search.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                keyword = p0.toString()
                viewModel.productSearchList(keyword)

            }
        })
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = SearchFragment()
    }
}