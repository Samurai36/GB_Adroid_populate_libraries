package com.example.gb_libs_lesson1

import com.example.gb_libs_lesson1.mvp.model.dataclasses.GithubRepository
import com.example.gb_libs_lesson1.mvp.presenter.MainPresenter
import com.example.gb_libs_lesson1.mvp.presenter.RepositoryPresenter
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import ru.terrakok.cicerone.Router

class RepositoryPresenterTest {

    private lateinit var presenter: RepositoryPresenter

    @InjectMocks
    private lateinit var router: Router

    @Mock
    private lateinit var repository: GithubRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = RepositoryPresenter(router, repository)
    }

    @Test
    fun repositoryPresenterBackClick_Test() {
        presenter.backClick()
        verify(repository, times(1))
    }

}