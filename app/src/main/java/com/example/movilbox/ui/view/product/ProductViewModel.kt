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

    fun getCategories() {
        viewModelScope.launch(ioDispatcher) {
            when (val result = getCategoryUseCase.invoke()) {
                is Either.Left -> {
                    val a = ""
                }

                is Either.Right -> {
                    val product = result.value

                }
            }

        }
    }

    fun getProduct() {
        viewModelScope.launch(ioDispatcher) {
            when (val result = getCategoryUseCase.invoke()) {
                is Either.Left -> showProduct()
                is Either.Right -> showProduct()
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

}