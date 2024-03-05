package com.example.movilbox.ui.view.product

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import com.example.movilbox.domain.model.ProductList
import com.example.movilbox.domain.usecase.GetProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class ProductViewModel @Inject constructor(
    @Named("IO") private val ioDispatcher: CoroutineDispatcher,
    private val getCategoryUseCase: GetProductUseCase,
) : ViewModel() {

    private val _product = MutableLiveData<List<ProductList>?>()
    val product: LiveData<List<ProductList>?> get() = _product

    private val _notProductVisibility = MutableLiveData(false)
    val notProductVisibility: LiveData<Boolean?> get() = _notProductVisibility

    private val _showLoader = MutableLiveData(true)
    val showLoader: LiveData<Boolean?> get() = _showLoader

    private val _showFilter = MutableLiveData(false)
    val showFilter: LiveData<Boolean?> get() = _showFilter

    fun getCategories() {
        viewModelScope.launch(ioDispatcher) {
            when (getCategoryUseCase.invoke()) {
                is Either.Left -> {
                    showProduct()
                    _showLoader.postValue(false)
                }

                is Either.Right -> getProduct()
            }
        }
    }

    private fun getProduct() {
        viewModelScope.launch(ioDispatcher) {
            when (getCategoryUseCase.invoke()) {
                is Either.Left ->
                    showProduct()

                is Either.Right ->
                    showProduct()
            }
            _showLoader.postValue(false)
        }
    }

    private suspend fun showProduct() {
        _product.postValue(getCategoryUseCase.getAllProduct())
    }

    fun searchProduct(name: String) {
        viewModelScope.launch(ioDispatcher) {
            val response = getCategoryUseCase.getProductName(name)
            _product.postValue(getCategoryUseCase.getProductName(name))
            _notProductVisibility.postValue(response.isEmpty())
        }
    }

    fun getProductFilter(item: String, type: String) {
        viewModelScope.launch(ioDispatcher) {
            when {
                item == "Precio" && type == "Asc" -> _product.postValue(getCategoryUseCase.getPriceAsc())
                item == "Precio" && type == "Desc" -> _product.postValue(getCategoryUseCase.getPriceDesc())
                item == "Descuento" && type == "Asc" -> _product.postValue(getCategoryUseCase.getDiscountAsc())
                item == "Descuento" && type == "Desc" -> _product.postValue(getCategoryUseCase.getDiscountDesc())
                item == "Categoria" && type == "Asc" -> _product.postValue(getCategoryUseCase.getCategoryAsc())
                item == "Categoria" && type == "Desc" -> _product.postValue(getCategoryUseCase.getCategoryDesc())
                item == "Rating" && type == "Asc" -> _product.postValue(getCategoryUseCase.getRatingAsc())
                item == "Rating" && type == "Desc" -> _product.postValue(getCategoryUseCase.getRatingDesc())
                item == "Stock" && type == "Asc" -> _product.postValue(getCategoryUseCase.getStockAsc())
                item == "Stock" && type == "Desc" -> _product.postValue(getCategoryUseCase.getStockDesc())
                item == "Marca" && type == "Asc" -> _product.postValue(getCategoryUseCase.getBrandAsc())
                item == "Marca" && type == "Desc" -> _product.postValue(getCategoryUseCase.getBrandDesc())
            }
        }
    }

    fun showFilter(state: Boolean) {
        _showFilter.postValue(state)
    }

}