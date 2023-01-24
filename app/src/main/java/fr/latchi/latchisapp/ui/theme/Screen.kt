package fr.latchi.latchisapp.ui.theme

sealed class Screen(val route: String) {
    object HomePageLaTchisApp : Screen( "homepage_latchiapp_screen")
    object LaTchiLAgenceDeComScreen : Screen("latchi_l_agence_de_com")
    object EntrepreunarialConceptsScreen : Screen("entrepreunarial_concepts_screen")
    object FrogScreen : Screen("frog_screen")
    object SettingFrogScreen : Screen("setting_frog_screen")
    object CibeePresentationScreen : Screen("cibee_presentation_screen")
    object Exercise2Screen : Screen("exercise2_screen")
    object Exercise3Screen : Screen("exercise3_screen")
    object BusinessCardScreen : Screen("business_card_screen")
    object AffirmationCardScreen : Screen("affirmation_list_screen")
    object ProjectsScreen : Screen("projects_screen")
}
