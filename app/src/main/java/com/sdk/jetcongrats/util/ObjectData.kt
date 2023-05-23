package com.sdk.jetcongrats.util

import com.sdk.jetcongrats.R
import com.sdk.jetcongrats.data.BoxColor
import com.sdk.jetcongrats.data.BoxFeature
import com.sdk.jetcongrats.ui.theme.*
import com.sdk.jetcongrats.presentation.component.MenuItem

object ColorObject {
    const val TAG = "@@@Jet"
    val colorList = listOf(
        BlueViolet3,
        OrangeYellow3,
        Blue3,
        Beige3,
        LightGreen3,
        Grey95
    )

    fun items() = listOf(
        BoxFeature(
            id = "weddings",
            title = "To'y tabriklari"
        ),
        BoxFeature(
            id = "proverbs",
            title = "Maqollar"
        ),
        BoxFeature(
            id = "boy",
            title = "Tug'ilgan kun tabrigi\n(O'g'il bola uchun)"
        ),
        BoxFeature(
            id = "girl",
            title = "Tug'ilgan kun tabrigi\n(Qiz bola uchun)"
        ),
        BoxFeature(
            id = "riddle",
            title = "Topishmoqlar"
        ),
        BoxFeature(
            id = "quickly",
            title = "Tez aytishlar"
        )
    )

    fun boxColorList() = listOf(
        BoxColor(
            lightColor = BlueViolet1,
            mediumColor = BlueViolet2,
            darkColor = BlueViolet3,
        ),
        BoxColor(
            lightColor = OrangeYellow1,
            mediumColor = OrangeYellow2,
            darkColor = OrangeYellow3
        ),
        BoxColor(
            lightColor = Blue1,
            mediumColor = Blue2,
            darkColor = Blue3
        ),
        BoxColor(
            lightColor = Beige1,
            mediumColor = Beige2,
            darkColor = Beige3
        ),
        BoxColor(
            lightColor = LightGreen1,
            mediumColor = LightGreen2,
            darkColor = LightGreen3
        ),
        BoxColor(
            lightColor = Grey99,
            mediumColor = Grey95,
            darkColor = Grey90
        )
    )
    fun menuList() = listOf(
        MenuItem(
            id = 7,
            title = R.string.other,
            icon = R.drawable.ic_baseline_widgets
        ),
        MenuItem(
            id = 0,
            title = R.string.rate,
            icon = R.drawable.ic_baseline_thumb_up
        ),
        MenuItem(
            id = 1,
            title = R.string.dev,
            icon = R.drawable.ic_baseline_code
        ),
        MenuItem(
            id = 2,
            title = R.string.share,
            icon = R.drawable.ic_baseline_share
        )
    )
}
object Constants {
    const val MY_APPS = "https://play.google.com/store/search?q=pub:Samandar+Sdk&c=apps" // good
    const val MY_APPS2 = "https://play.google.com/store/apps/developer?id=Samandar+Sdk"
    const val USER_NAME = "https://t.me/Samandar_sdk"
    const val URL = "https://play.google.com/store/apps/details?id="
    const val PROVERBS = "${URL}com.sdk.proverbsapp"
    const val AGATHA = "${URL}com.sdk.audiobook"
    const val DRAW = "${URL}com.sdk.drawshape"
    const val ASTRA = "${URL}com.sdk.planetnew"
}