package com.example.gb_libs_lesson1

import com.example.gb_libs_lesson1.test.presenter.details.DetailsPresenter
import com.example.gb_libs_lesson1.test.view.details.ViewDetailsContract
import com.nhaarman.mockito_kotlin.atLeastOnce
import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class DetailsPresenterTest {

    private lateinit var presenter: DetailsPresenter
    private lateinit var viewDetailsContractReflection: CharSequence

    @Mock
    private lateinit var viewContract: ViewDetailsContract

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = DetailsPresenter(viewContract)
        viewDetailsContractReflection = viewContract
            .javaClass
            .simpleName
            .subSequence(0, "ViewDetailsContract".length)
    }

    @Test
    fun onIncrementPresenterTest() {
        presenter.onIncrement()
        verify(viewContract, atLeastOnce()).setCount(1)
    }

    @Test
    fun onDecrementPresenterTest() {
        presenter.onDecrement()
        verify(viewContract, atLeastOnce()).setCount(-1)
    }

}