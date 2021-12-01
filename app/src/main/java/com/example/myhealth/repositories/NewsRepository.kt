package com.example.myhealth.repositories

import android.util.Log
import com.example.myhealth.model.News
import com.example.myhealth.provider.NewsProvider
import javax.inject.Inject

interface NewsRepository {
    suspend fun getNews(country: String): List<News>
    fun getNew(title: String): News
}

class NewsRepositoryImp
@Inject
constructor(
    private val newsProvider: NewsProvider
): NewsRepository{

    private var news: List<News> = emptyList()

    override suspend fun getNews(country: String): List<News> {
        val apiResponse = newsProvider.topHeadLines(country).body()


            if (apiResponse?.status == "error") {
                Log.e("apiResponse?.stat=error", "entro al status" )
                when (apiResponse.code) {
                    "apiKeyMissing" -> throw MissingApiKeyException()
                    "apiKeyInvalid" -> throw ApiKeyInvalidException()
                    else -> throw Exception()
                }
            }
            Log.e("no entro al status", "" + apiResponse?.status)
            news = apiResponse?.articles ?: emptyList()
            return news

    }

    override fun getNew(title: String): News = news.first { it.title == title }

}

class MissingApiKeyException : java.lang.Exception()
class ApiKeyInvalidException : java.lang.Exception()