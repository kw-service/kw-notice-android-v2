package dev.yjyoon.kwnotice.presentation.ui.main

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import dev.yjyoon.kwnotice.presentation.R

enum class MainDestination(
    val route: String,
    @DrawableRes val iconOutlinedResId: Int,
    @DrawableRes val iconFilledResId: Int,
    @StringRes val labelResId: Int
) {
    Notice(
        route = ROUTE_NOTICE,
        iconOutlinedResId = R.drawable.ic_list_outlined,
        iconFilledResId = R.drawable.ic_list_filled,
        labelResId = R.string.navigation_notice
    ),
    Favorite(
        route = ROUTE_FAVORITE,
        iconOutlinedResId = R.drawable.ic_fav_outlined,
        iconFilledResId = R.drawable.ic_fav_filled,
        labelResId = R.string.navigation_favorite
    ),
    Settings(
        route = ROUTE_SETTING,
        iconOutlinedResId = R.drawable.ic_settings_outlined,
        iconFilledResId = R.drawable.ic_settings_filled,
        labelResId = R.string.navigation_settings
    )
}

private const val ROUTE_NOTICE = "notice"
private const val ROUTE_FAVORITE = "favorite"
private const val ROUTE_SETTING = "settings"
