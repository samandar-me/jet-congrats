package com.sdk.jetcongrats.domain.use_case

import com.sdk.jetcongrats.domain.model.FavoriteData
import com.sdk.jetcongrats.domain.repository.JetRepository
import javax.inject.Inject

class DeleteFavoriteUseCase @Inject constructor(
    private val repository: JetRepository
) {
    suspend operator fun invoke(favoriteData: FavoriteData) = repository.deleteFavorite(favoriteData)
}