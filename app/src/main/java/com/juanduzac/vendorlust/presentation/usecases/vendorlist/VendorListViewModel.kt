package com.juanduzac.vendorlust.presentation.usecases.vendorlist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juanduzac.vendorlust.domain.model.VendorsResponse
import com.juanduzac.vendorlust.domain.repository.VendorsRepository
import com.juanduzac.vendorlust.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@HiltViewModel
class VendorListViewModel @Inject constructor(
    private val repository: VendorsRepository
) : ViewModel() {

    var vendorsResponse by mutableStateOf(VendorsResponse())
    var isLoading by mutableStateOf(false)
    var isRefreshing by mutableStateOf(false)
    var searchQuery by mutableStateOf("")

    private var searchJob: Job? = null

    fun getVendorsList(fetchFromRemote: Boolean = false, query: String = searchQuery) {
        viewModelScope.launch {
            repository
                .getVendors(fetchFromRemote, query)
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            result.data?.let { response ->
                                vendorsResponse = response
                            }
                        }
                        is Resource.Error -> Unit // TODO Feedback
                        is Resource.Loading -> {
                            isLoading = true
                        }
                    }
                }
        }
    }

    fun searchVendor() {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500L)
            getVendorsList()
        }
    }
}
