package fr.latchi.latchisapp.ui.theme

sealed class Screen(val route: String) {
    object GreetingLaTchiScreen : Screen("greeting_latchi_screen")
    object Exercise1Screen : Screen("exercise1_screen")
    object Exercise2Screen : Screen("exercise2_screen")
    object Exercise3Screen : Screen("exercise3_screen")
    object BusinessCardScreen : Screen("business_card_screen")
}
