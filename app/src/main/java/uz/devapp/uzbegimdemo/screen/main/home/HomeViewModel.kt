package uz.devapp.uzbegimdemo.screen.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import uz.devapp.uzbegimdemo.data.model.*
import uz.devapp.uzbegimdemo.data.model.request.FavoriteRequest
import uz.devapp.uzbegimdemo.data.model.request.NewsRequest
import uz.devapp.uzbegimdemo.data.model.request.ProductDetailRequest
import uz.devapp.uzbegimdemo.data.model.request.ProductRequest
import uz.devapp.uzbegimdemo.data.repository.MainRepository
import uz.devapp.uzbegimdemo.data.repository.sealed.DataResult
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {

    private var _progressLiveData = MutableLiveData<Boolean>()
    var progressLiveData: LiveData<Boolean> = _progressLiveData

    private var _offersListLiveData = MutableLiveData<DataResult<List<OffersModel>>>()
    var offersListLiveData: LiveData<DataResult<List<OffersModel>>> = _offersListLiveData

    private var _categoryListLiveData = MutableLiveData<DataResult<List<CategoryModel>>>()
    var categoryListLiveData: LiveData<DataResult<List<CategoryModel>>> = _categoryListLiveData

    private var _productListLiveData = MutableLiveData<DataResult<List<ProductListModel>>>()
    var productListLiveData: LiveData<DataResult<List<ProductListModel>>> = _productListLiveData

    private var _productNewListLiveData = MutableLiveData<DataResult<List<ProductListModel>>>()
    var productNewListLiveData: LiveData<DataResult<List<ProductListModel>>> =
        _productNewListLiveData

    private var _productTopListLiveData = MutableLiveData<DataResult<List<ProductListModel>>>()
    var productTopListLiveData: LiveData<DataResult<List<ProductListModel>>> =
        _productTopListLiveData

    private var _productWholesaleListLiveData =
        MutableLiveData<DataResult<List<ProductListModel>>>()
    var productWholesaleListLiveData: LiveData<DataResult<List<ProductListModel>>> =
        _productWholesaleListLiveData

    private var _productSaleListLiveData = MutableLiveData<DataResult<List<ProductListModel>>>()
    var productSaleListLiveData: LiveData<DataResult<List<ProductListModel>>> =
        _productSaleListLiveData

    private var _favoriteLiveData = MutableLiveData<DataResult<Any?>>()
    var favoriteLiveData: LiveData<DataResult<Any?>> = _favoriteLiveData

    private var _productDetailLiveData = MutableLiveData<DataResult<ProductDetailModel>>()
    var productDetailLiveData: LiveData<DataResult<ProductDetailModel>> = _productDetailLiveData

    private var _productSimilarListLiveData = MutableLiveData<DataResult<List<ProductListModel>>>()
    var productSimilarListLiveData: LiveData<DataResult<List<ProductListModel>>> =
        _productSimilarListLiveData

    private var _newsListLiveData = MutableLiveData<DataResult<List<NewsModel>>>()
    var newsListLiveData: LiveData<DataResult<List<NewsModel>>> = _newsListLiveData

    private var _newsDetailLiveData = MutableLiveData<DataResult<NewsModel>>()
    var newsDetailLiveData: LiveData<DataResult<NewsModel>> = _newsDetailLiveData

    fun getOffers() {
        _progressLiveData.value = true
        viewModelScope.launch {
            repository.getOffers().collect {
                _offersListLiveData.value = it
            }
        }
        _progressLiveData.value = false
    }

    fun getCategory() {
        viewModelScope.launch {
            repository.getCategory().collect {
                _categoryListLiveData.value = it
            }
        }
    }

    fun productList() {
        viewModelScope.launch {
            repository.productList(ProductRequest()).collect {
                _productListLiveData.value = it
            }
        }
    }

    fun productNewList() {
        viewModelScope.launch {
            repository.productList(ProductRequest(isNew = true)).collect {
                _productNewListLiveData.value = it
            }
        }
    }

    fun productTopList() {
        viewModelScope.launch {
            repository.productList(ProductRequest(isTop = true)).collect {
                _productTopListLiveData.value = it
            }
        }
    }

    fun productWholesaleList() {
        viewModelScope.launch {
            repository.productList(ProductRequest(isWholesale = true)).collect {
                _productWholesaleListLiveData.value = it
            }
        }
    }

    fun productSaleList() {
        viewModelScope.launch {
            repository.productList(ProductRequest(isSale = true)).collect {
                _productSaleListLiveData.value = it
            }
        }
    }

    fun setFavorite(request: FavoriteRequest) {
        viewModelScope.launch {
            repository.setFavorite(request.id).collect {
                _favoriteLiveData.value = it
            }
        }
    }

    fun productFavoriteList() {
        _progressLiveData.value = true
        viewModelScope.launch {
            repository.getFavorite().collect {
                _productListLiveData.value = it
            }
        }
        _progressLiveData.value = false
    }

    fun productSearchList(value: String) {
        viewModelScope.launch {
            repository.productList(ProductRequest(keyword = value)).collect {
                _productListLiveData.value = it
            }
        }
    }

    fun getProductDetail(request: ProductDetailRequest) {
        viewModelScope.launch {
            repository.getProductDetail(request.id).collect {
                _productDetailLiveData.value = it
            }
        }
    }

    fun productSimilarList(request: String) {
        viewModelScope.launch {
            repository.productList(ProductRequest(tag = request)).collect {
                _productSimilarListLiveData.value = it
            }
        }
    }

    fun getNews() {
        _progressLiveData.value = true
        viewModelScope.launch {
            repository.getNews().collect {
                _newsListLiveData.value = it
            }
        }
        _progressLiveData.value = false
    }

    fun getNewsDetail(request: NewsRequest) {
        viewModelScope.launch {
            repository.getNewsDetail(request.id).collect {
                _newsDetailLiveData.value = it
            }
        }
    }
}