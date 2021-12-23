package com.example.gb_libs_lesson1

import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.gb_libs_lesson1.test.model.SearchResponse
import com.example.gb_libs_lesson1.test.repository.FakeGitHubRepository
import com.example.gb_libs_lesson1.test.util.stub.ScheduleProviderStub
import com.example.gb_libs_lesson1.test.view.search.ScreenState
import com.example.gb_libs_lesson1.test.view.search.SearchViewModel
import io.reactivex.Observable
import org.junit.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class SearchViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var searchViewModel: SearchViewModel

    @Mock
    private lateinit var repository: FakeGitHubRepository

    private var observer: Observer<ScreenState>? = null
    private var liveData: LiveData<ScreenState>? = null

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        searchViewModel = SearchViewModel(repository, ScheduleProviderStub())
        observer = Observer<ScreenState> {}
        liveData = searchViewModel.subscribeToLiveData()

    }

    @Test //Проверим вызов метода searchGitHub() у нашей ВьюМодели
    fun search_Test() {
        `when`(repository.searchGithub(SEARCH_QUERY)).thenReturn(
            Observable.just(
                SearchResponse(
                    1,
                    listOf()
                )
            )
        )

        searchViewModel.searchGitHub(SEARCH_QUERY)
        verify(repository, times(1)).searchGithub(SEARCH_QUERY)
    }

    @Test
    fun liveData_TestReturnValueIsNotNull() {

        //При вызове Репозитория возвращаем шаблонные данные
        `when`(repository.searchGithub(SEARCH_QUERY)).thenReturn(
            Observable.just(
                SearchResponse(
                    1,
                    listOf()
                )
            )
        )
        execute { data ->
            searchViewModel.searchGitHub(SEARCH_QUERY)
            //Убеждаемся, что Репозиторий вернул данные и LiveData передала их Наблюдателям
            Assert.assertNotNull(data.value)
        }

    }

    @Test
    fun liveData_TestReturnValueIsError() {
        val error = Throwable(ERROR_TEXT)
        //При вызове Репозитория возвращаем ошибку
        `when`(repository.searchGithub(SEARCH_QUERY)).thenReturn(
            Observable.error(error)
        )
        execute { data ->
            searchViewModel.searchGitHub(SEARCH_QUERY)
            //Убеждаемся, что Репозиторий вернул ошибку и LiveData возвращает ошибку
            val value: ScreenState.Error = data.value as ScreenState.Error
            Assert.assertEquals(value.error.message, error.message)
        }
    }

    private inline fun execute(action: (data: LiveData<ScreenState>) -> Unit) {
        liveData?.let {
            observer?.run {
                it.observeForever(this)
                action.invoke(it)
            }
        }
    }

    @After
    fun removeLiveData() {
        liveData?.let {
            observer?.apply {
                it.removeObserver(this)
            }
        }
    }

    companion object {
        private const val SEARCH_QUERY = "some query"
        private const val ERROR_TEXT = "error"
    }
}