package fr.latchi.latchisapp.model

import androidx.annotation.DrawableRes

data class PartnerModel(
    val firstname: String,
    val lastname: String,
    var positionInTheTeam: String,
    var numberPhone: String,
    var instagramName: String,
    var emailAddress: String,
    var serviceTeam: String,
    @DrawableRes val presentationPhotoResourceId: Int
)
