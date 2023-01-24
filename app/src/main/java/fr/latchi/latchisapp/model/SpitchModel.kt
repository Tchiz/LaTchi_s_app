package fr.latchi.latchisapp.model

import androidx.annotation.StringRes

data class SpitchModel(
    @StringRes val stringResourceIntroductionId: Int,
    @StringRes val stringResourceBtnId: Int,
    val galleryItemList: List<GalleryItemModel>,
    val navigationRoute: String
)
