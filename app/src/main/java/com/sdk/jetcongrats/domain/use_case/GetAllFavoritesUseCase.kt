package com.sdk.jetcongrats.domain.use_case

import com.sdk.jetcongrats.domain.repository.JetRepository
import javax.inject.Inject

class GetAllFavoritesUseCase @Inject constructor(
    private val repository: JetRepository
) {
    operator fun invoke() = repository.getAllFavorites()
}