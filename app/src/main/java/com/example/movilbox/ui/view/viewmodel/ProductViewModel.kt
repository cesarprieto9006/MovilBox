package com.example.movilbox.ui.view.viewmodel

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
                is Either.Left -> {
                    val a = ""
                }

                is Either.Right -> {
                    val product = result.value
                    _product.postValue(product.products)
                }
            }

        }
    }



}