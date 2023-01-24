package fr.latchi.latchisapp.data
import fr.latchi.latchisapp.R
import fr.latchi.latchisapp.model.GalleryItemModel
import fr.latchi.latchisapp.model.PartnerModel
import fr.latchi.latchisapp.model.SpitchModel
import fr.latchi.latchisapp.ui.theme.Screen

class DataSource {
    companion object {
        fun loadAffirmations(): List<GalleryItemModel> {
            return listOf(
                GalleryItemModel(R.string.affirmation1, R.drawable.image1),
                GalleryItemModel(R.string.affirmation2, R.drawable.image2),
                GalleryItemModel(R.string.affirmation3, R.drawable.image3),
                GalleryItemModel(R.string.affirmation4, R.drawable.image4),
                GalleryItemModel(R.string.affirmation5, R.drawable.image5),
                GalleryItemModel(R.string.affirmation6, R.drawable.image6),
                GalleryItemModel(R.string.affirmation7, R.drawable.image7),
                GalleryItemModel(R.string.affirmation8, R.drawable.image8),
                GalleryItemModel(R.string.affirmation9, R.drawable.image9),
                GalleryItemModel(R.string.affirmation10, R.drawable.image10))
        }
        fun loadEntrepreneurialConcepts(): List<GalleryItemModel> {
            return listOf(
                GalleryItemModel(R.string.crapaud_concept, R.drawable.crapaud)
            )
        }
        fun loadProjects(): List<GalleryItemModel> {
            return listOf(
                GalleryItemModel(R.string.lady_h_society_project, R.drawable.ville_humaine_line_hd_rendu_avectout),
                GalleryItemModel(R.string.jam_dessine_oct21_project, R.drawable.flyer_jamdessine_oct21),
                GalleryItemModel(R.string.moncherwatson_mascotte_project, R.drawable.moncherwatson_visuel_galerie_latchi_agence)
            )
        }

        fun loadPartners(): List<PartnerModel> {
            return listOf(
                PartnerModel(
                    "Cibee",
                    "Kamara",
                    "Its Developer, Coach, Drawer, Singer and Founder",
                    "06 40 14 40 25",
                    "la.tchi.kiff.sa.life",
                    "jeparle@latchi.fr",
                    "create an Android Kotlin Compose application",
                    R.drawable.cibee_kamara),
                PartnerModel(
                    "Slighty",
                    "Maitrel",
                    "Large format Painter, Illustrator, Graphist, Singer and friend-partner",
                    "06 71 51 17 45",
                    "slighty_maitrel",
                    "slightymaitrel@hotmail.fr",
                    "create lovely realistic portraits and deep gospel events for intense Human moments",
                    R.drawable.slighty_maitrel),
                PartnerModel(
                    "Yohanna",
                    "Mentzel",
                    "Coach, Graphist, Illustrator, Webflow Designer and Friend-Partner",
                    "06 20 15 39 36",
                    "impact.ta.vie",
                    "contact@yohanna.fr",
                    "create an aligned visual identity with a team who can challenge us",
                    R.drawable.yohanna_mentzel),
                PartnerModel(
                    "Carine",
                    "Gouriadec",
                    "Consultant, Trainer, Brand Identity Creator and Partner",
                    "06 73 06 68 88",
                    "carine.grdc",
                    "carine_gouriadec@hotmail.com",
                    "create an impactful brand identity with a team able to listen to our needs",
                    R.drawable.carine_gouriadec),
                PartnerModel(
                    "Marjolaine",
                    "Gonet",
                    "Graphist, Illustrator, Screen Printer, Love Fan and Friend-Partner",
                    "07 66 35 87 66",
                    "desigraphe",
                    "ledesigraphe@gmail.com",
                    "create a visual identity with communication media made in France like T-shirt, towel, furoshiki and Wedding products",
                    R.drawable.marjolaine_gonet)
            )
        }
        fun loadSpitchs(): List<SpitchModel> {
            return listOf(
                SpitchModel(
                    R.string.spitch_intro_agence,
                    R.string.btn_intro_agence,
                    loadProjects(),
                    Screen.ProjectsScreen.route
                ),
                SpitchModel(
                    R.string.spitch_team_agence,
                    R.string.btn_team_agence,
                    loadEntrepreneurialConcepts(),
                    Screen.EntrepreunarialConceptsScreen.route
                ),
                SpitchModel(
                    R.string.spitch_dream_agence,
                    R.string.btn_dream_agence,
                    loadAffirmations(), // TODO: à changer par loadValues()
                    Screen.AffirmationCardScreen.route //TODO: changer les affirmations par mes valeurs/mantra
                )
            )
        }
    }
}