package uz.devapp.uzbegimdemo.screen.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.imaginativeworld.whynotimagecarousel.listener.CarouselListener
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem
import org.imaginativeworld.whynotimagecarousel.utils.setImage
import uz.devapp.uzbegimdemo.R
import uz.devapp.uzbegimdemo.adapter.ProductAdapter
import uz.devapp.uzbegimdemo.adapter.ProductAdapterCallback
import uz.devapp.uzbegimdemo.adapter.ViewPager2FragmentAdapter
import uz.devapp.uzbegimdemo.data.ProductModel
import uz.devapp.uzbegimdemo.databinding.FragmentHomeBinding
import uz.devapp.uzbegimdemo.databinding.ItemCustomFixedBinding


class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding

    var list = listOf(
        "https://img.freepik.com/free-vector/special-offer-modern-sale-banner-template_1017-20667.jpg",
        "https://www.jiffylubesocal.com/wp-content/uploads/2014/01/Offers.jpg",
        "https://ascottbeauty.co.uk/content/3.Special-Offers/2.special-offers.png",
    )
    val listCategory = mutableListOf(
        "1",
        "2",
        "3"
    )
    val productList = mutableListOf(
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
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.apply {
            (requireActivity() as AppCompatActivity).findViewById<BottomNavigationView>(R.id.bottomNavigationView).visibility =
                View.VISIBLE

            menu.setOnClickListener {
                openCloseNavigationDrawer()
            }

            rvTop.adapter = ProductAdapter(productList, object : ProductAdapterCallback {
                override fun onSelectCategory(item: ProductModel) {
                    requireActivity().findNavController(R.id.mainFragmentContainerView)
                        .navigate(R.id.productDetailFragment)
                }
            })
            rvNew.adapter = ProductAdapter(productList, object : ProductAdapterCallback {
                override fun onSelectCategory(item: ProductModel) {
                    requireActivity().findNavController(R.id.mainFragmentContainerView)
                        .navigate(R.id.productDetailFragment)
                }
            })
            rvSale.adapter = ProductAdapter(productList, object : ProductAdapterCallback {
                override fun onSelectCategory(item: ProductModel) {
                    requireActivity().findNavController(R.id.mainFragmentContainerView)
                        .navigate(R.id.productDetailFragment)
                }
            })
            rvOptom.adapter = ProductAdapter(productList, object : ProductAdapterCallback {
                override fun onSelectCategory(item: ProductModel) {
                    requireActivity().findNavController(R.id.mainFragmentContainerView)
                        .navigate(R.id.productDetailFragment)
                }
            })

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
            }

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
            for (item in list) {
                listFour.add(
                    CarouselItem(
                        imageUrl = item
                    )
                )
            }
            carouselView.setData(listFour)
            carouselView.setIndicator(customIndicator)

            navView.setNavigationItemSelectedListener { item ->
                if (item.itemId == R.id.news) {

                } else if (item.itemId == R.id.language) {

                } else if (item.itemId == R.id.help) {

                } else if (item.itemId == R.id.share) {

                } else if (item.itemId == R.id.documentText) {

                }
                false
            }

            viewPager.adapter = ViewPager2FragmentAdapter(this@HomeFragment, listCategory)
            viewPagerIndicator.setViewPager(viewPager)

            cart.setOnClickListener {
                requireActivity().findNavController(R.id.mainFragmentContainerView)
                    .navigate(R.id.cartFragment)
            }

            topCategory.setOnClickListener {
                requireActivity().findNavController(R.id.mainFragmentContainerView)
                    .navigate(R.id.productsFragment)
            }

            newCategory.setOnClickListener {
                requireActivity().findNavController(R.id.mainFragmentContainerView)
                    .navigate(R.id.productsFragment)
            }

            saleCategory.setOnClickListener {
                requireActivity().findNavController(R.id.mainFragmentContainerView)
                    .navigate(R.id.productsFragment)
            }

            optomCategory.setOnClickListener {
                requireActivity().findNavController(R.id.mainFragmentContainerView)
                    .navigate(R.id.productsFragment)
            }
        }
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }

    fun openCloseNavigationDrawer() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }
    }
}