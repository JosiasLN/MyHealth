package com.example.myhealth.presentation.screens.noticias

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myhealth.model.News
import com.example.myhealth.repositories.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltViewModel
class DetailsNoticiasViewModel
@Inject
constructor(
    private val repository: NewsRepository
) : ViewModel() {

    private val _news = MutableLiveData<News>()

    fun getNewsByTitle(title: String): LiveData<News> {
        try {
            viewModelScope.launch(Dispatchers.IO) {
                val news = repository.getNew(title)
                _news.postValue(news)
                Log.e("DNVM", "" + _news)
            }
        }catch (e: Exception){
            Log.e("DNVM", "Exception")
        }
        return _news
    }
}