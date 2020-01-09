package com.josemarrima.marvelcomics.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.josemarrima.marvelcomics.data.local.Comic
import com.josemarrima.marvelcomics.model.MarvelResponse
import com.josemarrima.marvelcomics.util.ResponseHandler
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock

@ExperimentalCoroutinesApi
class ComicRepositoryTest {

    val comic1 = Comic(1, "title1", "desc1", "url1")
    val comic2 = Comic(2, "title2", "desc2", "url2")
    val comic3 = Comic(3, "title3", "desc3", "url3")

    val comics = listOf(comic1, comic2, comic3).sortedBy { it.id }

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun whenCacheData_insertListOfComics() = runBlockingTest {
        val comicDao = ComicDaoFake(comics)
        val marvelResponse = mock(MarvelResponse::class.java)
        val responseHandler = mock(ResponseHandler::class.java)
        val subject = ComicRepository(ComicDaoFake(comics),
                                    ComicServiceFake(marvelResponse),
                                    responseHandler)

        assertThat(subject.comicDao.getAllComics().value.toString()).isEqualTo(comics.toString())
    }
}