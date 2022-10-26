package com.juanduzac.vendorlust.presentation.usecases.vendorlist

import com.juanduzac.vendorlust.domain.model.Vendor
import com.juanduzac.vendorlust.presentation.util.vendorExample
import com.juanduzac.vendorlust.util.FakeVendorRepository
import com.juanduzac.vendorlust.util.MainDispatcherRule
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class VendorListViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: VendorListViewModel

    @Before
    fun setUp() {
        viewModel = VendorListViewModel(FakeVendorRepository())
    }

    @Test
    fun getVendorsOnInitWhenCreatingTheViewModel() {
        runTest {
            advanceUntilIdle()
            assertEquals(listOf(vendorExample), viewModel.vendorsResponse.vendors)
            assertEquals(false, viewModel.isLoading)
        }
    }

    @Test
    fun getVendorDetail() {
        runTest {
            assertEquals(Vendor(), viewModel.selectedVendor)
            viewModel.getVendorDetail(1)
            advanceUntilIdle()
            assertEquals(vendorExample, viewModel.selectedVendor)
        }
    }
}
