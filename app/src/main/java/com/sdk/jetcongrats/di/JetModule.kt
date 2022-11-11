package com.sdk.jetcongrats.di

import android.content.Context
import com.google.firebase.firestore.FirebaseFirestore
import com.sdk.jetcongrats.data.manager.DataStoreManager
import com.sdk.jetcongrats.data.manager.MyClipBoardManager
import com.sdk.jetcongrats.data.repository.JetRepositoryImpl
import com.sdk.jetcongrats.domain.repository.JetRepository
import com.sdk.jetcongrats.domain.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object JetModule {

    @Provides
    @Singleton
    fun provideJetRepository(
        firestore: FirebaseFirestore,
        dataStoreManager: DataStoreManager,
        myClipBoardManager: MyClipBoardManager
    ): JetRepository {
        return JetRepositoryImpl(firestore, dataStoreManager, myClipBoardManager)
    }

    @Provides
    @Singleton
    fun provideClipBoardManager(@ApplicationContext context: Context): MyClipBoardManager {
        return MyClipBoardManager(context)
    }

    @Provides
    @Singleton
    fun provideDataStoreManager(@ApplicationContext context: Context): DataStoreManager {
        return DataStoreManager(context)
    }

    @Provides
    @Singleton
    fun provideFirebaseFireStore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Provides
    @Singleton
    fun provideAllUseCases(repository: JetRepository): UseCases {
        return UseCases(
            getDataUseCase = GetDataUseCase(repository),
            uploadFavoriteUseCase = UploadFavoriteUseCase(repository),
            copyTextUseCase = CopyTextUseCase(repository),
            getBackColorUseCase = GetBackColorUseCase(repository),
            getColorUseCase = GetColorUseCase(repository),
            saveBackColorUseCase = SaveBackColorUseCase(repository),
            saveColorUseCase = SaveColorUseCase(repository),
            getAllFavoritesUseCase = GetAllFavoritesUseCase(repository),
            deleteFavoriteUseCase = DeleteFavoriteUseCase(repository)
        )
    }
}