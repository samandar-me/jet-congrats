package com.sdk.jetcongrats.domain.use_case

import com.sdk.jetcongrats.domain.repository.JetRepository
import javax.inject.Inject

class SaveBackColorUseCase @Inject constructor(
    private val repository: JetRepository
) {
    suspend operator fun invoke(isDark: Boolean) = repository.saveBackColor(isDark)
}