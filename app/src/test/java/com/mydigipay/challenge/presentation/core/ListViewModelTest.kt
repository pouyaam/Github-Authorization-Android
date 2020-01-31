package com.mydigipay.challenge.presentation.core

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.github.mohammadsianaki.core.model.ResourcesState
import com.mydigipay.challenge.presentation.core.stub.StubListViewModel
import com.mydigipay.challenge.presentation.core.stub.StubParamters
import com.mydigipay.challenge.presentation.core.stub.Type
import io.mockk.clearAllMocks
import io.mockk.spyk
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldNotBe
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class ListViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val viewModel = spyk<StubListViewModel>()

    @Before
    fun setUp() {
        clearAllMocks()
    }

    @Test
    fun `loading with no data and no error`() {
        viewModel.makeData(StubParamters(Type.LOAD))
        val value = viewModel.getLiveData().value!!
        value.resourcesState shouldBe ResourcesState.Loading
        value.data shouldBe null
        value.failure shouldBe null
    }

    @Test
    fun `success with data and no error`() {
        viewModel.makeData(StubParamters(Type.SUCCESS))
        val value = viewModel.getLiveData().value!!
        value.resourcesState shouldBe ResourcesState.Success
        value.data shouldNotBe null
        value.failure shouldBe null
    }

    @Test
    fun `failure with no data`() {
        viewModel.makeData(StubParamters(Type.ERROR))
        val value = viewModel.getLiveData().value!!
        value.resourcesState shouldBe ResourcesState.Failure
        value.data shouldBe null
        value.failure!!.message shouldBe "stub error"
    }
}