package com.example.movilbox.ui.view.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movilbox.domain.model.ProductList
import com.example.movilbox.domain.usecase.GetProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class DetailProductViewModel @Inject constructor(
    @Named("IO") private val ioDispatcher: CoroutineDispatcher,
    private val productUseCase: GetProductUseCase

) : ViewModel() {

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _product = MutableLiveData<ProductList?>()
    val product: LiveData<ProductList?> get() = _product

    private val _searchProductVisibility = MutableLiveData(false)
    val searchProductVisibility: LiveData<Boolean?> get() = _searchProductVisibility

    fun getProductDetails(id: String) {
        viewModelScope.launch(ioDispatcher) {
            _product.postValue(productUseCase.getProductId(id))
        }
    }

}