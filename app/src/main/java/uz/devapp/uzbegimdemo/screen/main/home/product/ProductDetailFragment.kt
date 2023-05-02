package uz.devapp.uzbegimdemo.screen.main.home.product

import android.graphics.Paint
import android.os.Build
import android.os.Bundle
import android.text.Html
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
import uz.devapp.uzbegimdemo.adapter.ProductAdapterVertical
import uz.devapp.uzbegimdemo.adapter.ProductAdapterVerticalCallback
import uz.devapp.uzbegimdemo.adapter.ProductListAdapter
import uz.devapp.uzbegimdemo.adapter.ProductListAdapterCallback
import uz.devapp.uzbegimdemo.data.model.ProductListModel
import uz.devapp.uzbegimdemo.data.model.request.FavoriteRequest
import uz.devapp.uzbegimdemo.data.model.request.ProductDetailRequest
import uz.devapp.uzbegimdemo.data.repository.sealed.DataResult
import uz.devapp.uzbegimdemo.databinding.FragmentProductDetailBinding
import uz.devapp.uzbegimdemo.screen.main.home.HomeViewModel
import uz.devapp.uzbegimdemo.utils.Constants
import uz.devapp.uzbegimdemo.utils.SelectButton
import uz.devapp.uzbegimdemo.utils.loadImage

private const val ARG_PARAM1 = Constants.EXTRA_DATA

@AndroidEntryPoint
class ProductDetailFragment : Fragment() {
    lateinit var binding: FragmentProductDetailBinding
    private val viewModel: HomeViewModel by viewModels()
    var tagRequest = ProductDetailRequest()
    var request = FavoriteRequest()
    private var param1: ProductListModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            if (Build.VERSION.SDK_INT < 33) {
                param1 = it.getSerializable(ARG_PARAM1) as ProductListModel?
            } else {
                param1 = it.getSerializable(ARG_PARAM1, ProductListModel::class.java)
            }
        }
        tagRequest = ProductDetailRequest(id = param1!!.id)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        (requireActivity() as AppCompatActivity).findViewById<BottomNavigationView>(R.id.bottomNavigationView).visibility =
            View.GONE

        binding.back.setOnClickListener {
            requireActivity().findNavController(R.id.mainFragmentContainerView).popBackStack()
        }

        viewModel.productSimilarListLiveData.observe(requireActivity()) {
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
                        ProductAdapterVertical(it.result, object : ProductAdapterVerticalCallback {
                            override fun onSelectCategory(item: ProductListModel) {
                                SelectButton.onClickItem(item, requireActivity())
                            }

                            override fun onSelectFavorite(
                                item: ProductListModel,
                                holder: ProductAdapterVertical.Vh
                            ) {
                                SelectButton.onClickFavoriteVertical(item, holder)

                                request = FavoriteRequest(id = item.id)
                                viewModel.setFavorite(request)
                            }

                            override fun onSelectCart(
                                item: ProductListModel,
                                holder: ProductAdapterVertical.Vh
                            ) {
                                SelectButton.onClickCartButtonVertical(item, holder, requireContext())
                            }
                        })
                }
            }
        }

        viewModel.productDetailLiveData.observe(requireActivity()) {
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
                    binding.oldPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                    binding.image.loadImage(result.mainImage)
                    binding.tvTitle.text = result.nameUz
                    binding.oldPrice.text = result.oldPrice.toString() + " " + result.currency.nameUz
                    binding.price.text = result.price.toString() + " " + result.currency.nameUz
                    binding.tvKeshbek.text = result.cashback.toString()
                    binding.tvProductAbout.text = Html.fromHtml(result.descriptionUz)
                    if (result.oldPrice.toString() == "0") {
                        binding.oldPrice.visibility = View.INVISIBLE
                    } else {
                        binding.oldPrice.visibility = View.VISIBLE
                    }
                }
            }
        }

        binding.trash.setOnClickListener {
            requireActivity().findNavController(R.id.mainFragmentContainerView)
                .navigate(R.id.cartFragment)
        }
        viewModel.productSimilarList(param1?.tag ?: "")
        viewModel.getProductDetail(tagRequest)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: ProductListModel) = ProductDetailFragment().apply {
            arguments = Bundle().apply {
                putSerializable(ARG_PARAM1, param1)
            }
        }
    }
}