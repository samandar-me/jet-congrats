package com.sdk.jetcongrats.domain.use_case

data class UseCases(
    val getDataUseCase: GetDataUseCase,
    val uploadFavoriteUseCase: UploadFavoriteUseCase,
    val copyTextUseCase: CopyTextUseCase,
    val getBackColorUseCase: GetBackColorUseCase,
    val getColorUseCase: GetColorUseCase,
    val saveBackColorUseCase: SaveBackColorUseCase,
    val saveColorUseCase: SaveColorUseCase,
    val getAllFavoritesUseCase: GetAllFavoritesUseCase,
    val deleteFavoriteUseCase: DeleteFavoriteUseCase
)