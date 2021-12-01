package com.example.myhealth.di

import com.example.myhealth.provider.NewsProvider
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import com.example.myhealth.repositories.NewsRepository
import com.example.myhealth.repositories.NewsRepositoryImp

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFirestoreInstance() = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun provideResultList(
        firestore: FirebaseFirestore
    ) = firestore.collection("usuarios")


    @Provides
    @Singleton
    fun providerNewsRepository(provider: NewsProvider): NewsRepository = NewsRepositoryImp(provider)


}