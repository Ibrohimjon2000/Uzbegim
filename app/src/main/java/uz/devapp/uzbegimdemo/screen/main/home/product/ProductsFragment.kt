package uz.devapp.uzbegimdemo.screen.main.home.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
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
import uz.devapp.uzbegimdemo.databinding.FragmentProductsBinding
import uz.devapp.uzbegimdemo.screen.main.home.HomeViewModel
import uz.devapp.uzbegimdemo.utils.Constants
import uz.devapp.uzbegimdemo.utils.SelectButton

private const val ARG_PARAM1 = Constants.EXTRA_DATA

@AndroidEntryPoint
class ProductsFragment : Fragment() {
    lateinit var binding: FragmentProductsBinding
    private val viewModel: HomeViewModel by viewModels()
    private var param1: String? = null
    var request = FavoriteRequest()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductsBinding.inflate(inflater, container, false)
        (requireActivity() as AppCompatActivity).findViewById<BottomNavigationView>(R.id.bottomNavigationView).visibility =
            View.GONE

        when (param1.toString()) {
            "Top" -> {
                viewModel.productTopListLiveData.observe(requireActivity()) {
                    when (it) {
                        is DataResult.Error -> {
                            Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                        }
                        is DataResult.LoadingHide -> {

                        }
                        is DataResult.LoadingShow -> {

                        }
                        is DataResult.Success -> {
                            binding.rv.adapter =
                                ProductListAdapter(it.result, object : ProductListAdapterCallback {
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
                                        SelectButton.onClickCartButton(
                                            item,
                                            holder,
                                            requireContext()
                                        )
                                    }
                                })
                        }
                    }
                }
                viewModel.productTopList()
                viewModel.setFavorite(request)
            }
            "New" -> {
                viewModel.productNewListLiveData.observe(requireActivity()) {
                    when (it) {
                        is DataResult.Error -> {
                            Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                        }
                        is DataResult.LoadingHide -> {

                        }
                        is DataResult.LoadingShow -> {

                        }
                        is DataResult.Success -> {
                            binding.rv.adapter =
                                ProductListAdapter(it.result, object : ProductListAdapterCallback {
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
                                        SelectButton.onClickCartButton(
                                            item,
                                            holder,
                                            requireContext()
                                        )
                                    }
                                })
                        }
                    }
                }
                viewModel.productNewList()
                viewModel.setFavorite(request)
            }
            "Sale" -> {
                viewModel.productSaleListLiveData.observe(requireActivity()) {
                    when (it) {
                        is DataResult.Error -> {
                            Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                        }
                        is DataResult.LoadingHide -> {

                        }
                        is DataResult.LoadingShow -> {

                        }
                        is DataResult.Success -> {
                            binding.rv.adapter =
                                ProductListAdapter(it.result, object : ProductListAdapterCallback {
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
                                        SelectButton.onClickCartButton(
                                            item,
                                            holder,
                                            requireContext()
                                        )
                                    }
                                })
                        }
                    }
                }
                viewModel.productSaleList()
                viewModel.setFavorite(request)
            }
            "Wholesale" -> {
                viewModel.productWholesaleListLiveData.observe(requireActivity()) {
                    when (it) {
                        is DataResult.Error -> {
                            Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                        }
                        is DataResult.LoadingHide -> {

                        }
                        is DataResult.LoadingShow -> {

                        }
                        is DataResult.Success -> {
                            binding.rv.adapter =
                                ProductListAdapter(it.result, object : ProductListAdapterCallback {
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
                                        SelectButton.onClickCartButton(
                                            item,
                                            holder,
                                            requireContext()
                                        )
                                    }
                                })
                        }
                    }
                }
                viewModel.productWholesaleList()
                viewModel.setFavorite(request)
            }
            else -> {
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
                            binding.rv.adapter =
                                ProductListAdapter(it.result, object : ProductListAdapterCallback {
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
                                        SelectButton.onClickCartButton(
                                            item,
                                            holder,
                                            requireContext()
                                        )
                                    }
                                })
                        }
                    }
                }
                viewModel.productList()
            }
        }

        binding.back.setOnClickListener {
            requireActivity().findNavController(R.id.mainFragmentContainerView).popBackStack()
        }

        binding.trash.setOnClickListener {
            requireActivity().findNavController(R.id.mainFragmentContainerView)
                .navigate(R.id.cartFragment)
        }
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String) = ProductsFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_PARAM1, param1)
            }
        }
    }
}