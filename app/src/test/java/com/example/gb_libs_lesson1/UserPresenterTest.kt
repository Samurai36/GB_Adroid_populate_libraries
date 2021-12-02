package com.example.gb_libs_lesson1

import com.example.gb_libs_lesson1.mvp.model.GithubRepositoriesRepo
import com.example.gb_libs_lesson1.mvp.model.dataclasses.GithubRepository
import com.example.gb_libs_lesson1.mvp.model.dataclasses.GithubUser
import com.example.gb_libs_lesson1.mvp.presenter.MainPresenter
import com.example.gb_libs_lesson1.mvp.presenter.RepositoryPresenter
import com.example.gb_libs_lesson1.mvp.presenter.UserPresenter
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.rxjava3.core.Scheduler
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import ru.terrakok.cicerone.Router

class UserPresenterTest {

    private lateinit var presenter: UserPresenter

    @InjectMocks
    private lateinit var router: Router

    @Mock
    private lateinit var repository: GithubRepositoriesRepo

    @Mock
    private lateinit var scheduler: Scheduler

    @Mock
    private lateinit var user: GithubUser

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = UserPresenter(repository, router, scheduler, user)
    }

    @Test
    fun userPresenterBackClick_Test() {
        presenter.backClick()
        verify(repository, times(1))
    }

}