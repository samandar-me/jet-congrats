package com.sdk.jetcongrats.domain.use_case

import com.sdk.jetcongrats.domain.repository.JetRepository
import javax.inject.Inject

class SaveColorUseCase @Inject constructor(
    private val repository: JetRepository
){
    suspend operator fun invoke(int: Int) = repository.saveColor(int)
}