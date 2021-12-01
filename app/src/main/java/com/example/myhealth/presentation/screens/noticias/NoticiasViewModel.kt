package com.example.myhealth.presentation.screens.noticias

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myhealth.model.News
import com.example.myhealth.model.NewsApiResponse
import com.example.myhealth.presentation.screens.perfil.ResultListState
import com.example.myhealth.repositories.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltViewModel
class NoticiasViewModel
@Inject
constructor(
    private val repository: NewsRepository
) : ViewModel() {

    private val _news = MutableLiveData<List<News>>()

    fun getNews(): LiveData<List<News>> {
        try {
            viewModelScope.launch(Dispatchers.IO) {
                val news = repository.getNews("mx")
                _news.postValue(news)
                Log.e("NVM", "" + _news)
            }
        }catch (e: Exception){
            Log.e("NVM", "Exception")
        }

        return _news
    }
}