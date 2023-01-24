package fr.latchi.latchisapp.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class GalleryItemModel(
    @StringRes val stringResourceId: Int,
    @DrawableRes val imageResourceId: Int
)

