package fr.latchi.latchisapp.ui.theme

import android.content.ActivityNotFoundException
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.view.animation.OvershootInterpolator
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import fr.latchi.latchisapp.R
import fr.latchi.latchisapp.data.DataSource
import fr.latchi.latchisapp.model.GalleryItemModel
import fr.latchi.latchisapp.model.PartnerModel
import kotlinx.coroutines.delay

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.HomePageLaTchisApp.route) {
        composable(route = Screen.HomePageLaTchisApp.route) {
            HomePageLaTchisAppScreen(navController = navController)
        }
        composable(route = Screen.BusinessCardScreen.route) {
            BusinessCardsLaTchisTeamScreen(navController = navController)
        }
        composable(route = Screen.LaTchiLAgenceDeComScreen.route) {
            LaTchiLAgenceDeComPresentationScreen(navController = navController)
        }
        composable(route = Screen.EntrepreunarialConceptsScreen.route) {
            EntrepreneurialConcepts(navController = navController)
        }
        composable(route = Screen.SettingFrogScreen.route) {
            SettingFrogScreen(navController = navController)
        }
        composable(route = Screen.FrogScreen.route) {
            FrogScreen(navController = navController)
        }
        composable(route = Screen.CibeePresentationScreen.route) {
            CibeePresentationScreen(navController = navController)
        }
        composable(route = Screen.Exercise2Screen.route) {
            Exo2Screen(navController = navController)
        }
        composable(route = Screen.Exercise3Screen.route) {
            Exo3Screen(navController = navController)
        }
        composable(route = Screen.AffirmationCardScreen.route) {
            AffirmationCardScreen(navController)
        }
        composable(route = Screen.ProjectsScreen.route) {
            ProjectsScreen(navController = navController)
        }
    }
}

@Composable
fun HomePageLaTchisAppScreen(navController: NavController) {
    val scale = remember {
        Animatable(0f)
    }
    LaunchedEffect(
        key1 = true, //why true and not Unit ?
        block = {
            scale.animateTo(
                targetValue = 1f,
                animationSpec = tween(
                    durationMillis = 500,
                    easing = {
                        OvershootInterpolator(2f).getInterpolation(it)
                    }
                )
            )
            delay(2400L)
            navController.navigate(Screen.BusinessCardScreen.route)
        }
    )
    Column(
        modifier = Modifier
            .background(color = Color(0xFFE76263))
            .fillMaxSize()
            .padding(top = 230.dp)
            .scale(scale.value)
            ) {
        Image(
            painter = painterResource(id = R.drawable.logo_latchi_agence_de_com),
            contentDescription = "Le logo de LaTchi Agence de COM'"
        )
    }
}

@Composable
fun AffirmationCardScreen(navController: NavController) {
    GalleryItemListScreen(navController = navController, galleryItemList = DataSource.loadAffirmations())
}

@Composable
fun GalleryItemListScreen(
    navController: NavController,
    galleryItemList: List<GalleryItemModel>
) {
    GalleryItemList(
        galleryItemModelList = galleryItemList
    )
    Button(
        onClick = {
            navController.navigate(Screen.HomePageLaTchisApp.route)
        }
    ) {
        Text(
            text = "return to Cibee Kamara's business card"
        )
    }
}

@Composable
private fun GalleryItemList(galleryItemModelList: List<GalleryItemModel>) {
    LazyColumn {
        items(galleryItemModelList.size) { affirmationIndex ->
            GalleryItemCard(galleryItemModel = galleryItemModelList[affirmationIndex])
        }
    }
}
@Composable
fun GalleryItemCard(galleryItemModel: GalleryItemModel, modifier: Modifier = Modifier) {

    var popupControl by remember {
        mutableStateOf(false)
    }

    Card(
        modifier = modifier
            .padding(8.dp)
            .width(400.dp)
            .clickable(
                onClick = {
                    // TODO : add voice over for each positive sentence
                    popupControl = true
                }
            )
    ) {
        Column {
            //TODO: in the popup, possibility of slide left and right to see the entire picture + use a miniature with possibility of default miniature if it's a dynamic add by the app
            Image(
                painter = painterResource(id = galleryItemModel.imageResourceId),
                contentDescription = stringResource(id = galleryItemModel.stringResourceId),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = stringResource(id= galleryItemModel.stringResourceId),
                modifier = Modifier
                    .padding(16.dp)
                    .height(20.dp),
                style = MaterialTheme.typography.titleSmall
                )
            if(popupControl) {
                Popup(
                    onDismissRequest = {
                        popupControl = false
                    }
                ) {
                    Card(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .wrapContentWidth(Alignment.CenterHorizontally)
                    ) {
                        Column {
                            Image(
                                painter = painterResource(id = galleryItemModel.imageResourceId),
                                contentDescription = stringResource(id = galleryItemModel.stringResourceId),
                                modifier = Modifier
                                    .height(400.dp),
                                contentScale = ContentScale.Crop
                            )
                            Text(
                                text = stringResource(id= galleryItemModel.stringResourceId),
                                fontSize =  36.sp,
                                fontWeight = FontWeight.Light,
                                modifier = Modifier
                                    .padding(16.dp),
                                style = MaterialTheme.typography.titleLarge
                            )
                            Button(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentWidth(Alignment.CenterHorizontally),
                                onClick = {
                                    popupControl = false
                                }
                            ) {
                                Text(
                                    text = "Close popup"
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
@Composable
fun BannerLaTchisapp(
    navController: NavController,
    height : Dp = 100.dp, // avec une valeur par défaut à 100
    isOpen : Boolean = false
) {
    Box {
        Image(
            painter = painterResource(id = R.drawable.therefore_wedonotloseheart_forandroid),
            contentDescription = "Therefore, we do not lose heart LaTchi",
            modifier = Modifier
                .height(height)
                .clickable {
                    //isOpen = !isOpen
                    navController.navigate(Screen.LaTchiLAgenceDeComScreen.route)
                },
            contentScale = ContentScale.Crop
        )
        Box (
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
        ){
            if(isOpen) {
                Image(
                    painter = painterResource(id = R.drawable.logo_latchi_agence_de_com),
                    contentDescription = "logo LaTchi l'Agence de com'",
                    alignment = Alignment.Center,
                    modifier = Modifier
                        .width(250.dp)
                        .padding(top = 40.dp)
                )
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.CenterHorizontally)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_latchi_agence_de_com),
                contentDescription = "logo LaTchi L'Agence de com' miniature",
                modifier = Modifier
                    .width(80.dp)
            )
        }
    }
}
@Composable
fun BusinessCardsLaTchisTeamScreen(navController: NavController) {
    var isOpen by remember {
        mutableStateOf(true)
    }

    val listState = rememberLazyListState()
    if(listState.isScrollInProgress) {
        isOpen = false
    }

    val transition = updateTransition(
        listState.firstVisibleItemIndex != 0,
        label = "Image Size Transition"
    )

    val height by transition.animateDp(label = "heightBannerAnimation") { isScrolling ->
        if(isScrolling) 100.dp else 530.dp
    }

    isOpen = height in 200.dp..530.dp

    Column(
        modifier = Modifier
            .background(color = Color(0xFFE76263))
            .fillMaxSize()
            .padding(top = 0.dp)
    ) {
        BannerLaTchisapp(
            navController,
            height,
            isOpen
        )
        // TODO: when isOpen = false, add white title (draw by myself) "L'équipe !"
        //mettre la liste des partenaires ici
        PartnerModelList(
            navController = navController,
            partnerModelList = DataSource.loadPartners(),
            listState
        )
    }
}

@Composable
private fun PartnerModelList(
    navController: NavController,
    partnerModelList: List<PartnerModel>,
    listState: LazyListState) {
    LazyColumn(
        state = listState
    ) {
        items(partnerModelList.size) {partnerModelIndex ->
            BusinessCardTeamPresentation(
                navController = navController,
                businessCardTeam = partnerModelList[partnerModelIndex])
        }
    }
}

@Composable
private fun BusinessCardTeamPresentation(
    navController: NavController,
    businessCardTeam: PartnerModel
) {
    Column {
        Text(
            text = "${businessCardTeam.firstname} ${businessCardTeam.lastname}",
            fontSize = 56.sp,
            fontWeight = FontWeight.Light,
            color = Color(0xFFFFEBA9),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
                .clickable(
                    onClick = {
                        navController.navigate(Screen.EntrepreunarialConceptsScreen.route)
                    })
        )
        Image(
            painter = painterResource(id = businessCardTeam.presentationPhotoResourceId),
            contentDescription = "La photo de ${businessCardTeam.firstname} ${businessCardTeam.lastname}",
            modifier = Modifier
                .height(300.dp)
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
                .border(1.dp, Color.Yellow, RectangleShape)

        )
        Text(
            text = businessCardTeam.positionInTheTeam,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
                .padding(
                    top = 16.dp,
                    start = 16.dp,
                    end = 16.dp
                ),
            style = MaterialTheme.typography.titleSmall,
            textAlign = TextAlign.Center,
        )
    }
    Spacer(
        modifier = Modifier
            .height(20.dp)
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.CenterHorizontally)
    ) {

        // on below line we are creating
        // a variable for a context
        val ctx = LocalContext.current
        BusinessCardDetails(
            labelDetail = businessCardTeam.numberPhone,
            imageVector = Icons.Rounded.Phone,
            iconDescription = "${businessCardTeam.firstname} ${businessCardTeam.lastname}'s number phone",
            onClickFunction = {
                val i = Intent(Intent.ACTION_DIAL).apply {
                    data = Uri.parse("tel:${businessCardTeam.numberPhone}")
                }
                ctx.startActivity(i)
            }
        )
        BusinessCardDetails(
            labelDetail = "@${businessCardTeam.instagramName}",
            imageVector = Icons.Rounded.Share,
            iconDescription = "${businessCardTeam.firstname} ${businessCardTeam.lastname} Instagram",
            onClickFunction = {
                val i = Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse("http://instagram.com/_u/${businessCardTeam.instagramName}")
                    setPackage("com.instagram.android")
                }
                try {
                    ctx.startActivity(i)
                } catch (e: ActivityNotFoundException) {
                    ctx.startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("http://instagram/${businessCardTeam.instagramName}")
                        )
                    )
                }
            }
        )
        BusinessCardDetails(
            labelDetail = businessCardTeam.emailAddress,
            imageVector = Icons.Rounded.Email,
            iconDescription = "my email address",
            onClickFunction = {
                val i = Intent(Intent.ACTION_SEND).apply {
                    //data = Uri.parse("mailto:") // only email apps should handle this
                    //TODO forcer la copie de l'email à jeparle@latchi.fr en plus de emailAddress spécifié pour plus de sécurité et de suivi
                    putExtra(Intent.EXTRA_EMAIL, arrayOf(businessCardTeam.emailAddress))
                    putExtra(Intent.EXTRA_SUBJECT, "(Your company) I want to work with LaTchi L'Agence de com' !")
                    putExtra(
                        Intent.EXTRA_TEXT,
                        "Hello ${businessCardTeam.firstname} ${businessCardTeam.lastname}, \n\nI want to ${businessCardTeam.serviceTeam} and LaTchi's energy seems to be what i need to do it so, can we plan a call to talk about business together ? \n\n Thank you for your time, see you soon ! \n\nWith all due respect,\nYour name and your function."
                    )
                    // on below line we are
                    // setting type of intent
                    type = "message/rfc822"
                }
                // on the below line we are starting our activity to open email application.
                ctx.startActivity(Intent.createChooser(i, "Choose an e-mail client : "))
            }
        )
    }
}

@Composable
private fun BusinessCardDetails(
    labelDetail: String,
    imageVector: ImageVector,
    iconDescription: String,
    onClickFunction: () -> Unit
) {
    Divider(
        color = Color.White,
        thickness = 0.5.dp
    )
    Row(
        modifier = Modifier
            .padding(
                start = 80.dp,
                top = 5.dp,
                bottom = 5.dp,
            )
            .clickable(
                onClick = onClickFunction
            )
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = iconDescription,
            tint = Color(0xFF3ddc84),
        )
        Text(
            text = labelDetail,
            color = Color.White,
            modifier = Modifier
                .padding(
                    start = 25.dp
                )
        )
    }
}

@Composable
fun Exo3Screen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .clickable(
                onClick = {
                    navController.navigate(Screen.AffirmationCardScreen.route)
                }
            )
    ) {
        Row(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(Color.Magenta)
        ) {
            Exo3ScreenQuadrant(
                title = "Kotlin",
                text = "I don’t know a lot of stuff about Kotlin but all the time I’m coding in Kotlin, I said “so awesome !!!!”. It’s so clean and so intuitive ! I love that ! I’m coding in Java too (and other languages, but my heart goes out to Kotlin if you ask me to choose !). In addition, all learning materials are so awesome, thanks to Jetbrains and Google !! It’s a pleasure to resume computer development under these conditions.",
                colorBackground = Color(0xFFFF8900)
            )
            Exo3ScreenQuadrant(
                title = "Jetpack Compose",
                text = "At the start, i was not really enthusiastic about Jetpack Compose cause of this weirdo way to use functions to develop screens. Surely because i’m come from the world of web development ? Don’t know. But after some talks with LouisCAD, i calmed my prejudices, and trust Louis about this tool. For the moment, i love it ;) Let’s see more",
                colorBackground = Color(0xFFCE5CA9)
            )
        }
        Row(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(Color.Red)
        ) {
            Exo3ScreenQuadrant(
                title = "Android Application",
                text = "Why do I want to resume computer development with Android Application ? Don’t know. It’s really stylish, that’s all. More and more, after a long time far away of the world of smartphones, I appreciate the mindset, and i want to learn a lot about this univers. It’s stimulating ! And the people i meet in the community are so kind, so lovely and so passionate. I love that. Can i make my place there ? ",
                colorBackground = Color(0xFF00AFFF)
            )
            Exo3ScreenQuadrant(
                title = "Android Studio and co.",
                text = "For the moment, i’m at the very beginning, so it’s hard to say that Android Studio is fabulous but it’s a good IDE for me, and I love Jetbrains Toolbox to help me with different installations. My nemesis 10 years ago. I have some difficulty understanding all things about Gradle but all aids help me to manage it. And I try Refresh Versions too. See also.",
                colorBackground = Color(0xFF8267FE)
            )
        }
    }
}

@Composable
fun RowScope.Exo3ScreenQuadrant(
    title: String,
    text: String,
    colorBackground: Color
) {
    Column(
        modifier = Modifier
            .background(
                color = colorBackground,
                shape = RectangleShape
            )
            .padding(16.dp)
            .fillMaxHeight()
            .weight(1f)
            .wrapContentWidth(Alignment.CenterHorizontally)
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier
                .padding(bottom = 5.dp)
                .wrapContentHeight(Alignment.Top)
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
        )
        Text(
            text = text,
            textAlign = TextAlign.Justify,
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
        )
    }
}

@Composable
fun Exo2Screen(navController: NavController){
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .wrapContentHeight(Alignment.CenterVertically)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_task_completed),
            contentDescription = "I read LaTchi's stuff",
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
        )
        Text(
            text = "You read my stuff",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(
                    top = 24.dp,
                    bottom = 8.dp
                )
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
        )
        Text(
            text = "! Thank you !",
            fontSize = 16.sp,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
        )
        Button(onClick = {
            navController.navigate(Screen.Exercise3Screen.route)
        }) {
            Text(
                text = "I want to read more",
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally)
            )
        }
    }
}

@Composable
fun CibeePresentationScreen(navController: NavController){

    var isDoubleLaTchisSmile by remember { mutableStateOf(false) }
    val imageLaTchisSmile = if (isDoubleLaTchisSmile) R.drawable.bg_compose_background_doublesmile else R.drawable.bg_compose_background
    val namesLaTchi = if(isDoubleLaTchisSmile) "Lova Rakotoarisoa" else "Cibee KAMARA"

    Column {
        Text(
            text = "Do you know me ?",
            fontSize = 36.sp,
            modifier = Modifier
                .padding(
                    start = 24.dp,
                    top = 24.dp,
                    end = 24.dp,
                    bottom = 0.dp
                )
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
        )
        Image(
            painter = painterResource(id = imageLaTchisSmile),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
        )
        Text(
            text = namesLaTchi,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
        )
        Text(
            text = "I resume computer development after 7 years in art.",
            textAlign = TextAlign.Justify,
            modifier = Modifier
                .padding(
                    start = 16.dp,
                    end = 16.dp
                )
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
        )
        Text(
            text = "My new game is Kotlin, Jetpack Compose, and Android Application.\n" +
                    "\n" +
                    "I love going to Android Kotlin events and meeting a lot of passionate people like Louis CAD, Marion Hayoun and Gérard Paligot to name a few.\n" +
                    "\n" +
                    "I’m rigorous and sensitive. I’m told that I'm full of humanity and that with me, you feel good. That’s not true, just 90 percent of the time ;) For the remaining 10 percent, let me alone. I need to sing and draw something, to laugh a lot, to cry and it will be better…and during this 10 pourcent, please, send me a lot of love. I’m like that. Welcome to my world.\n" +
                    "\n" +
                    "I love people, a lot. May be too much (laughs)\n",
            textAlign = TextAlign.Justify,
            modifier = Modifier.padding(16.dp)
        )
        Button(
            onClick = {
                navController.navigate(Screen.Exercise2Screen.route)
            }) {
            Text(
                text = "Got it !",
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally)
            )
        }
    }

        // changement d'image en boucle (animation)
        LaunchedEffect(key1 = Unit, block = {
            while(true)  {
                delay(2000L)
                isDoubleLaTchisSmile = !isDoubleLaTchisSmile
            }
        } )
}

@Composable
fun LaTchiLAgenceDeComPresentationScreen(navController: NavController) {

    val spitchList = remember {
        DataSource.loadSpitchs()
    }
    var stepsIndex by remember {
        mutableStateOf(0)
    }
    val spitch = spitchList[stepsIndex]
    Column(
        modifier = Modifier
            .background(color = Color(0xFFE76263))
            .fillMaxSize()
            .padding(top = 16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_latchi_agence_de_com),
            contentDescription = "Le logo de LaTchi Agence de COM'"
        )
        
        Text(
            text = buildAnnotatedString {
                withStyle(style =
                    SpanStyle(
                        fontFamily = FontFamily.Cursive,
                        fontWeight = FontWeight.Bold,
                        fontSize = 35.sp
                    )
                ) {
                    append("LaTchi, L’Agence de com’ est une agence atypique.")
                }
                append(stringResource(spitch.stringResourceIntroductionId))
            },
            color = Color.White,
            modifier = Modifier
                .padding(
                    top = 0.dp,
                    start = 46.dp,
                    end = 46.dp
                )
                .pointerInput(Unit) {
                    detectTapGestures(
                        onDoubleTap = {
                            if (stepsIndex == spitchList.lastIndex) stepsIndex = 0
                            else stepsIndex++
                            //TODO: voir comment rendre persistant où en est l'index même quand je change de page et que je retourne en arrière sinon, revenir à l'index 0
                        }
                    )
                }
        )
        // bouton avec le premier élément de la galerie
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
                .padding(
                    top = 26.dp,
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 16.dp
                )
        ) {
            Button(
                contentPadding = PaddingValues(0.dp),
                modifier = Modifier
                    .width(300.dp)
                    .height(50.dp),
                onClick = {
                    navController.navigate(spitch.navigationRoute)
                }
            ) {
                Image(
                    painter = painterResource(id = spitch.galleryItemList.first().imageResourceId),
                    contentDescription = stringResource(id = spitch.galleryItemList.first().stringResourceId),
                    modifier = Modifier
                        .padding(start = 0.dp)
                        .weight(0.30f)
                )
                Text(
                    text = stringResource(spitch.stringResourceBtnId),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(16.dp)
                        .weight(0.70f)
                )
            }
        }
        LazyRowGalleryItemCard(items = spitch.galleryItemList)
    }
}

@Composable
fun LazyRowGalleryItemCard(items: List<GalleryItemModel>) {
    LazyRow {
        items(items.size) {itemsListIndex ->
            GalleryItemCard(galleryItemModel = items[itemsListIndex])
        }
    }
}

@Composable
fun EntrepreneurialConcepts(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color(0xFFE76263))
            .pointerInput(Unit) {
                detectTapGestures(
                    onDoubleTap = {
                        navController.navigate(Screen.FrogScreen.route)
                    }
                )
            }
    ) {
        BannerLaTchisapp(navController = navController)
        Text(
            text = "Concepts entrepreneuriaux",
            lineHeight = 50.sp,
            textAlign = TextAlign.Center,
            fontSize = 36.sp,
            fontWeight = FontWeight.Light,
            color = Color(0xFFFFEBA9),
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.Yellow, RectangleShape)
                .padding(16.dp)
                .weight(0.10f)
        )
        Column(
            modifier = Modifier
                .weight(.90f)
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
        ){
            Text(
                text = "Le concept du crapaud",
                fontSize = 26.sp,
                fontWeight = FontWeight.Light,
                textAlign = TextAlign.Center,
                color = Color.White,
                modifier = Modifier
                    .padding(6.dp)
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally)
            )

            val frogItems = listOf(
                GalleryItemModel(
                    stringResourceId = R.string.crapaud_strip_text_1,
                    imageResourceId = R.drawable.strip_crapaud_1
                ),
                GalleryItemModel(
                    stringResourceId = R.string.crapaud_strip_text_2,
                    imageResourceId = R.drawable.strip_crapaud_2
                )
            )
            LazyRowGalleryItemCard(items = frogItems)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.End)
            ) {
                Button(
                    onClick = {
                        navController.navigate(Screen.SettingFrogScreen.route)
                    }) {
                    Text(text = "Viens manger ton crapaud !")
                }
            }
        }
    }
}
@Composable
fun ConfirmationSettingFrogBlock() {
        Box {
            Image(
                painter = painterResource(id = R.drawable.crapaud__miniature),
                contentDescription = "Ton crapaud",
                modifier = Modifier
                    .clip(RoundedCornerShape(150.dp))
            )
        }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingFrogScreen(navController: NavController) {
    
    var firstname by remember {
        mutableStateOf("Cibee")
    }
    var monCrapaud by remember {
        mutableStateOf("Mon application Android")
    }
    
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color(0xFFE76263))
            .fillMaxHeight()
    ) {
        BannerLaTchisapp(navController = navController)
        //TODO: permettre à l'utilisateur d'entrer son crapaud et son prénom et d'avoir un fr.latchi.latchisapp.ui.theme.FrogScreen personnalisé et que cela s'affiche directement à l'écran de façon "jolie"
        CibeesFrogBlockFrogScreen(firstname = "Cibee") // TODO: revoir la signature et le nom de la fonction
        Column(
            modifier = Modifier
                .width(400.dp)
                //.height(230.dp)
                .padding(
                    top = 0.dp,
                    end = 32.dp,
                    start = 32.dp,
                    bottom = 32.dp
                )
                //.border(1.dp, Color.Yellow, RectangleShape)
                .wrapContentWidth(Alignment.CenterHorizontally),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            Column {
                Text(text = stringResource(R.string.ton_prenom_wordingFrogEntrepreneurialConcept), color = Color.White)
                TextField(
                    value = firstname,
                    label = { Text(text = stringResource(R.string.ton_prenom_wordingFrogEntrepreneurialConcept))},
                    //singleLine = true,
                    onValueChange = {
                        firstname = it
                    },
                    modifier = Modifier
                        .height(50.dp)
                )

                Text(text = stringResource(R.string.ton_crapaud_wordingFrogEntrepreneurialConcept), color = Color.White)
                TextField(
                    value = monCrapaud,
                    label = { Text(text = stringResource(R.string.ton_crapaud_wordingFrogEntrepreneurialConcept))},
                    onValueChange = {
                        monCrapaud = it
                    }
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
        ) {
            val mediaCrapaud = MediaPlayer.create(LocalContext.current, R.raw.crapaud)
            Button(
                modifier = Modifier
                    .border(1.dp, Color.Yellow, RoundedCornerShape(115.dp))
                    .height(130.dp),
                onClick =  {
                    //TODO : un son "croaa" du crapaud
                    mediaCrapaud.start()
                    navController.navigate(Screen.FrogScreen.route)
                }
            ) {
                    ConfirmationSettingFrogBlock()
            }
        }
    }
}

@Composable
fun MusicBlockFrogScreen(modifier : Modifier, modifierContent : Modifier) {
    var isPlaying by remember {
        mutableStateOf(false)
    }

    val contentDescription = if(isPlaying) "PJ Morton's music is playing" else "no music"
    val iconId = if(isPlaying) R.drawable.ic_play else R.drawable.ic_mute

    val mediaSoul = MediaPlayer.create(LocalContext.current, R.raw.pjmorton)

    Column (modifier = modifier){
        Image(
            painter = painterResource(id = iconId),
            contentDescription = contentDescription,
            modifier = modifierContent
                .padding(top = 11.dp)
                .clickable {
                    isPlaying = !isPlaying
                    if(mediaSoul.isPlaying) mediaSoul.pause() else mediaSoul.start()
                }
        )
        Text(
            textAlign = TextAlign.Center,
            color = Color(0xFFC67501),
            fontSize = 12.sp,
            text = "Need some soul music to motivate ya ?",
            modifier = modifierContent
                .padding(6.dp)
        )
    }
}

@Composable
fun FrogScreen(navController: NavController) {
    val modifier = Modifier
        .fillMaxWidth()
        .wrapContentWidth(Alignment.CenterHorizontally)
        .background(color = Color(0xFF54EBE2))
        .border(1.dp, Color(0xFFC67501), RectangleShape)
    val modifierContent = Modifier
        .fillMaxWidth()
        .wrapContentWidth(Alignment.CenterHorizontally)
    val modifierSpacer = Modifier.height(20.dp)

    Column {
        BannerLaTchisapp(navController = navController)
        DirectiveBlockFrogScreen(modifier, modifierContent)
        Spacer(modifier = modifierSpacer)
        CheesecakeAnimationBlockFrogScreen()
        Spacer(modifier = modifierSpacer)
        MusicBlockFrogScreen(modifier, modifierContent)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.End)
        ) {
            Button(
                modifier = Modifier.padding(6.dp),
                onClick =  {
                    //TODO: Présentation de Cibee à mettre au clique de sa photo
                    //navController.navigate(Screen.fr.latchi.latchisapp.ui.theme.CibeePresentationScreen.route)
                    //TODO: faire en sorte que ça arrive sur le bon "onglet" de la présentation LaTchi L'Agence cad "Team Presentation" avec pour bouton Concepts Entrepreneuriaux
                    navController.navigate(Screen.LaTchiLAgenceDeComScreen.route)
                }
            ) {
                Text(text = "Mmh, ça y est, j'ai capté le concept, merci !")
                //Text(text = "J'ai envie d'en savoir plus sur $firstname !")
            }
        }
    }
}

@Composable
fun CibeesFrogBlockFrogScreen(firstname: String) {

    val crapaud = "créer une application dont elle est fière"

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.CenterHorizontally)
            .background(color = Color(0xFFE76263))
    )
    {
        Text(
            //text = "If you love $brand",
            text = "Pour $firstname, son plus gros crapaud (ou cheesecake) est de $crapaud. Et toi ?",
            color = Color(0xFFFFEBA9),
            fontSize = 16.sp, // pk .dp ne marche pas ??
            modifier = Modifier
                .padding(
                    top = 0.dp,
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 16.dp
                )
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.titleLarge
        )
    }

}

@Composable
fun DirectiveBlockFrogScreen(
    modifier : Modifier,
    modifierContent: Modifier
){
    Column(
        modifier = modifier
    ) {
        val textContent = buildAnnotatedString {
            withStyle(style = SpanStyle(fontSize = 16.sp) ) {
                append("Eat your frog !\n")
            }
            withStyle(style = SpanStyle(fontSize = 24.sp) ) {
                append(stringResource(R.string.cheesake) + "\n")
            }
            append("(click on the cheesecake to eat it)")
        }
        Text(
            /**
             * TODO: donner le nom de la personne "connecter", donc créer une possibilité d'entrer son nom pour que ce soit un écran personnalisé au nom de l'utilisateur.
             * TODO: Pour le moment une persistence simple puis une vraie connection sur l'application complète
             * */
            textAlign = TextAlign.Center,
            color = Color(0xFFC67501),
            fontSize = 12.sp,
            text = textContent,
            modifier = modifierContent
                .padding(16.dp)
        )
    }
}
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun CheesecakeAnimationBlockFrogScreen() {
    val imageResIds = remember {
        listOf(
            R.drawable.androidparty,
            R.drawable.androidparty_onclick,
            R.drawable.androidparty_assiette_2quarts_partamanger,
            R.drawable.androidparty_assiette_2quarts_partmangee,
            R.drawable.androidparty_assiette_vide_partamanger,
            R.drawable.androidparty_assiette_vide
        )
    }
    var imageIndex by remember { mutableStateOf(0) }
    val imageResId = imageResIds[imageIndex]
    Box {
        AnimatedContent(targetState = imageResId) { imageResId ->
            val image = painterResource(id = imageResId)
            Image(
                painter = image,
                contentDescription = null,
                modifier = Modifier
                    //.fillMaxHeight()
                    .fillMaxWidth()
                    .clickable(
                        onClick = {
                            if (imageIndex == imageResIds.lastIndex) imageIndex = 0
                            else imageIndex++
                        }
                    ),
            )
        }
    }
}

@Composable
fun ProjectsScreen(navController: NavController){
    GalleryItemListScreen(navController = navController, galleryItemList = DataSource.loadProjects())
}