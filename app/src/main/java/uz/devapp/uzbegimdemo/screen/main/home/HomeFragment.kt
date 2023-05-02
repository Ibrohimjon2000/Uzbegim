package uz.devapp.uzbegimdemo.screen.main.home

import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import org.imaginativeworld.whynotimagecarousel.listener.CarouselListener
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem
import org.imaginativeworld.whynotimagecarousel.utils.setImage
import uz.devapp.uzbegimdemo.R
import uz.devapp.uzbegimdemo.adapter.ProductListAdapter
import uz.devapp.uzbegimdemo.adapter.ProductListAdapterCallback
import uz.devapp.uzbegimdemo.adapter.ViewPager2FragmentAdapter
import uz.devapp.uzbegimdemo.data.model.ProductListModel
import uz.devapp.uzbegimdemo.data.model.UserModel
import uz.devapp.uzbegimdemo.data.model.request.FavoriteRequest
import uz.devapp.uzbegimdemo.data.repository.sealed.DataResult
import uz.devapp.uzbegimdemo.databinding.FragmentHomeBinding
import uz.devapp.uzbegimdemo.databinding.ItemCustomFixedBinding
import uz.devapp.uzbegimdemo.utils.Constants
import uz.devapp.uzbegimdemo.utils.PrefUtils
import uz.devapp.uzbegimdemo.utils.SelectButton

@AndroidEntryPoint
class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    var request = FavoriteRequest()

    val listCategory = mutableListOf(
        "1",
        "2",
        "3"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.apply {
            (requireActivity() as AppCompatActivity).findViewById<BottomNavigationView>(R.id.bottomNavigationView).visibility =
                View.VISIBLE

            menu.setOnClickListener {
                openCloseNavigationDrawer()
            }

            viewModel.progressLiveData.observe(requireActivity()) {
                swipe.isRefreshing = it
            }

            swipe.setOnRefreshListener {
                loadData()
            }

            viewModel.offersListLiveData.observe(requireActivity()) {
                when (it) {
                    is DataResult.Error -> {
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    }
                    is DataResult.LoadingHide -> {

                    }
                    is DataResult.LoadingShow -> {

                    }
                    is DataResult.Success -> {
                        carouselView.registerLifecycle(lifecycle)
                        carouselView.carouselListener = object : CarouselListener {
                            override fun onCreateViewHolder(
                                layoutInflater: LayoutInflater,
                                parent: ViewGroup
                            ): ViewBinding {
                                return ItemCustomFixedBinding.inflate(
                                    layoutInflater,
                                    parent,
                                    false
                                )
                            }

                            override fun onBindViewHolder(
                                binding: ViewBinding,
                                item: CarouselItem,
                                position: Int
                            ) {
                                val currentBinding = binding as ItemCustomFixedBinding

                                currentBinding.imageView.apply {
                                    scaleType = ImageView.ScaleType.CENTER_CROP
                                    setImage(item, R.drawable.placeholder)
                                }
                            }
                        }
                        val listFour = mutableListOf<CarouselItem>()
                        for (item in it.result) {
                            listFour.add(
                                CarouselItem(
                                    imageUrl = Constants.BASE_URL + item.imageUz
                                )
                            )
                        }
                        carouselView.setData(listFour)
                        carouselView.setIndicator(customIndicator)
                    }
                }
            }

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
                        rvTop.adapter =
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
                                    SelectButton.onClickCartButton(item, holder, requireContext())
                                }
                            })
                    }
                }
            }

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
                        rvNew.adapter =
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
                                    SelectButton.onClickCartButton(item, holder, requireContext())
                                }
                            })

                    }
                }
            }

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
                        rvSale.adapter =
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
                                    SelectButton.onClickCartButton(item, holder, requireContext())
                                }
                            })

                    }
                }
            }

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
                        rvOptom.adapter =
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
                                    SelectButton.onClickCartButton(item, holder, requireContext())
                                }
                            })
                    }
                }
            }

            top.setOnClickListener {
                top.background = context?.getDrawable(R.drawable.item_tab)
                topIcon.setColorFilter(resources.getColor(R.color.white))
                topText.setTextColor(resources.getColor(R.color.white))
                news.background = resources.getDrawable(R.color.white)
                newsIcon.setColorFilter(resources.getColor(R.color.black))
                newsText.setTextColor(resources.getColor(R.color.black))
                sale.background = resources.getDrawable(R.color.white)
                saleIcon.setColorFilter(resources.getColor(R.color.black))
                saleText.setTextColor(resources.getColor(R.color.black))
                optom.background = resources.getDrawable(R.color.white)
                optomIcon.setColorFilter(resources.getColor(R.color.black))
                optomText.setTextColor(resources.getColor(R.color.black))
                scrollToView(scrollView, topCategory)
            }

            news.setOnClickListener {
                news.background = context?.getDrawable(R.drawable.item_tab)
                newsIcon.setColorFilter(resources.getColor(R.color.white))
                newsText.setTextColor(resources.getColor(R.color.white))
                top.background = resources.getDrawable(R.color.white)
                topIcon.setColorFilter(resources.getColor(R.color.black))
                topText.setTextColor(resources.getColor(R.color.black))
                sale.background = resources.getDrawable(R.color.white)
                saleIcon.setColorFilter(resources.getColor(R.color.black))
                saleText.setTextColor(resources.getColor(R.color.black))
                optom.background = resources.getDrawable(R.color.white)
                optomIcon.setColorFilter(resources.getColor(R.color.black))
                optomText.setTextColor(resources.getColor(R.color.black))
                scrollToView(scrollView, newCategory)
            }

            sale.setOnClickListener {
                sale.background = context?.getDrawable(R.drawable.item_tab)
                saleIcon.setColorFilter(resources.getColor(R.color.white))
                saleText.setTextColor(resources.getColor(R.color.white))
                top.background = resources.getDrawable(R.color.white)
                topIcon.setColorFilter(resources.getColor(R.color.black))
                topText.setTextColor(resources.getColor(R.color.black))
                news.background = resources.getDrawable(R.color.white)
                newsIcon.setColorFilter(resources.getColor(R.color.black))
                newsText.setTextColor(resources.getColor(R.color.black))
                optom.background = resources.getDrawable(R.color.white)
                optomIcon.setColorFilter(resources.getColor(R.color.black))
                optomText.setTextColor(resources.getColor(R.color.black))
                scrollToView(scrollView, saleCategory)
            }

            optom.setOnClickListener {
                optom.background = context?.getDrawable(R.drawable.item_tab)
                optomIcon.setColorFilter(resources.getColor(R.color.white))
                optomText.setTextColor(resources.getColor(R.color.white))
                top.background = resources.getDrawable(R.color.white)
                topIcon.setColorFilter(resources.getColor(R.color.black))
                topText.setTextColor(resources.getColor(R.color.black))
                news.background = resources.getDrawable(R.color.white)
                newsIcon.setColorFilter(resources.getColor(R.color.black))
                newsText.setTextColor(resources.getColor(R.color.black))
                sale.background = resources.getDrawable(R.color.white)
                saleIcon.setColorFilter(resources.getColor(R.color.black))
                saleText.setTextColor(resources.getColor(R.color.black))
                scrollToView(scrollView, optomCategory)
            }

            navView.setNavigationItemSelectedListener { item ->
                if (item.itemId == R.id.news) {
                    requireActivity().findNavController(R.id.mainFragmentContainerView)
                        .navigate(R.id.newsFragment)
                } else if (item.itemId == R.id.language) {
                    requireActivity().findNavController(R.id.fragmentContainerView)
                        .navigate(R.id.languageFragment)
                } else if (item.itemId == R.id.help) {
                    requireActivity().findNavController(R.id.mainFragmentContainerView)
                        .navigate(R.id.helpFragment)
                } else if (item.itemId == R.id.share) {
                    var share = Intent()
                    share.action = Intent.ACTION_SEND
                    share.putExtra(Intent.EXTRA_SUBJECT, "Subject Here")
                    share.putExtra(Intent.EXTRA_TEXT, "Test uchun")
                    share.type = "text/plain"
                    share = Intent.createChooser(share, "Share Via: ")
                    requireContext().startActivity(share)
                } else if (item.itemId == R.id.documentText) {

                }
                false
            }

            viewModel.categoryListLiveData.observe(requireActivity()) {
                when (it) {
                    is DataResult.Error -> {
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    }
                    is DataResult.LoadingHide -> {

                    }
                    is DataResult.LoadingShow -> {

                    }
                    is DataResult.Success -> {
                        viewPager.adapter =
                            ViewPager2FragmentAdapter(this@HomeFragment, listCategory)
                        viewPagerIndicator.setViewPager(viewPager)
                    }
                }
            }

            cart.setOnClickListener {
                requireActivity().findNavController(R.id.mainFragmentContainerView)
                    .navigate(R.id.cartFragment)
            }

            topCategory.setOnClickListener {
                val bundle = Bundle()
                bundle.putString(Constants.EXTRA_DATA, "Top")
                requireActivity().findNavController(R.id.mainFragmentContainerView)
                    .navigate(R.id.productsFragment, bundle)
            }

            newCategory.setOnClickListener {
                val bundle = Bundle()
                bundle.putString(Constants.EXTRA_DATA, "New")
                requireActivity().findNavController(R.id.mainFragmentContainerView)
                    .navigate(R.id.productsFragment, bundle)
            }

            saleCategory.setOnClickListener {
                val bundle = Bundle()
                bundle.putString(Constants.EXTRA_DATA, "Sale")
                requireActivity().findNavController(R.id.mainFragmentContainerView)
                    .navigate(R.id.productsFragment, bundle)
            }

            optomCategory.setOnClickListener {
                val bundle = Bundle()
                bundle.putString(Constants.EXTRA_DATA, "Wholesale")
                requireActivity().findNavController(R.id.mainFragmentContainerView)
                    .navigate(R.id.productsFragment, bundle)
            }
            loadData()
        }
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }

    fun openCloseNavigationDrawer() {
        val registration: LinearLayout = requireView().findViewById(R.id.lyRegistration)
        val userInfo: LinearLayout = requireView().findViewById(R.id.lyUserInfo)
        val name: TextView = requireView().findViewById(R.id.navViewName)
        val number: TextView = requireView().findViewById(R.id.navViewNumber)
        val keshbek: TextView = requireView().findViewById(R.id.navViewKeshbek)

        if (PrefUtils.getToken().isEmpty()) {
            registration.visibility = View.VISIBLE
            userInfo.visibility = View.GONE
        } else {
            name.text = PrefUtils.getUser().fullname
            number.text = PrefUtils.getUser().phone
            keshbek.text = PrefUtils.getUser().cashback.toString()
            registration.visibility = View.GONE
            userInfo.visibility = View.VISIBLE
        }

        registration.setOnClickListener {
            requireActivity().findNavController(R.id.fragmentContainerView)
                .navigate(R.id.entranceNumberFragment)
        }

        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }
    }

    fun scrollToView(scrollView: NestedScrollView, view: View) {
        view.requestFocus()

        val scrollBounds = Rect()
        scrollView.getHitRect(scrollBounds)
        if (!view.getLocalVisibleRect(scrollBounds)) {
            Handler().post(Runnable { scrollView.smoothScrollTo(0, view.top) })
        }
    }

    fun loadData() {
        viewModel.getOffers()
        viewModel.getCategory()
        viewModel.productNewList()
        viewModel.productTopList()
        viewModel.productWholesaleList()
        viewModel.productSaleList()
    }
}