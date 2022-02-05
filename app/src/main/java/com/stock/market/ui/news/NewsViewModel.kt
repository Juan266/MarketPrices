package com.stock.market.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.stock.market.BaseViewModel
import com.stock.market.data.remote.response.NewsResponse
import com.stock.market.domain.model.ErrorApp
import com.stock.market.domain.model.NetworkResult
import com.stock.market.domain.model.News
import com.stock.market.domain.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel  @Inject constructor(private val newsRepositoty: NewsRepository): BaseViewModel() {
    val _resultNews: MutableLiveData<NewsResponse> = MutableLiveData()
    val resultNews: LiveData<NewsResponse> = _resultNews
    val _errorNews: MutableLiveData<ErrorApp> = MutableLiveData()
    val errorNews: LiveData<ErrorApp> = _errorNews

    lateinit var news: Array<News>

    init {
        getNews("en")
    }

    fun getNews(language: String) = viewModelScope.launch {
        newsRepositoty.getNews(language).collect { values ->
            when (values) {
                is NetworkResult.Success -> {
                    _resultNews.value = values.data
                }
                is NetworkResult.Error ->  {
                    _errorNews.value = values.error
                }
            }
        }
    }
}