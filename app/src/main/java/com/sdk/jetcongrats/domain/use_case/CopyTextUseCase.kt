package com.sdk.jetcongrats.domain.use_case

import com.sdk.jetcongrats.domain.repository.JetRepository
import javax.inject.Inject

class CopyTextUseCase @Inject constructor(
    private val repository: JetRepository
) {
    operator fun invoke(text: String) = repository.copyText(text)
}