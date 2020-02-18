package com.mydigipay.challenge.ui.home

import com.mydigipay.challenge.github.BaseTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.koin.test.inject

class HomeViewModelTest: BaseTest() {
    private val viewModel: HomeViewModel by inject()


    @Test
    fun testGotList() {
        val state = HomeViewState()
        viewModel.homeState.observeForever {}
        assertEquals(viewModel.homeState.value, state.copy(isLoading = true))
    }

}