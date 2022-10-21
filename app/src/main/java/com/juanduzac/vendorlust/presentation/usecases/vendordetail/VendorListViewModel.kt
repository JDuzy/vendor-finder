package com.juanduzac.vendorlust.presentation.usecases.vendordetail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juanduzac.vendorlust.domain.model.VendorsResponse
import com.juanduzac.vendorlust.domain.repository.VendorsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VendorListViewModel @Inject constructor(
    private val repository: VendorsRepository
) : ViewModel() {

    val vendorsResponse by mutableStateOf(VendorsResponse())
    val isLoading by mutableStateOf(false)
    val isRefreshing by mutableStateOf(false)
    val searchQuery by mutableStateOf("")

    fun getVendorList(query: String = searchQuery, fetchFromRemote: Boolean = false) {
        viewModelScope.launch {
            repository.getVendors()
        }
    }

}