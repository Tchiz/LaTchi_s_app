import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import fr.latchi.latchisapp.R
import fr.latchi.latchisapp.ui.theme.Screen


@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.BusinessCardScreen.route) {
        composable(route = Screen.BusinessCardScreen.route) {
            BusinessCardScreen(navController = navController)
        }
        composable(route = Screen.GreetingLaTchiScreen.route) {
            LaTchiScreen(navController = navController)
        }
        composable(route = Screen.Exercise1Screen.route) {
            Exo1Screen(navController = navController)
        }
        composable(route = Screen.Exercise2Screen.route) {
            Exo2Screen(navController = navController)
        }
        composable(route = Screen.Exercise3Screen.route) {
            Exo3Screen(navController = navController)
        }
    }
}

@Composable
fun BusinessCardScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .background(color = Color(0xFF003A49))
            .fillMaxSize()
            .padding(
                top = 230.dp
            )
    ) {
        Image(
            painter = painterResource(id = R.drawable.therefore_wedonotloseheart_v0_bandeau),
            contentDescription = "Therefore, we do not lose heart LaTchi"
        )
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
                    painter = painterResource(id = R.drawable.android_logo),
                    contentDescription = "logo Android",
                    modifier = Modifier
                        .width(80.dp)
                )
            }

            Text(
                text = "Cibee KAMARA",
                fontSize =  56.sp,
                fontWeight = FontWeight.Light,
                color = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally)
                    .clickable(
                        onClick = {
                            navController.navigate(Screen.GreetingLaTchiScreen.route)
                        })
            )
            Text(
                text = "Extraordinary Android Kotlin Compose Developer",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF3ddc84),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally)
            )
        }
        Spacer(
            modifier = Modifier
                .height(180.dp)
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
                labelDetail = "06 40 14 40 25",
                imageVector = Icons.Rounded.Phone,
                iconDescription = "my number phone",
                onClickFunction = {
                    val i = Intent(Intent.ACTION_DIAL).apply {
                        data = Uri.parse("tel:" + "0640144025")
                    }
                    ctx.startActivity(i)
                }
            )
            BusinessCardDetails(
                labelDetail = "@la.tchi.kiff.sa.life",
                imageVector = Icons.Rounded.Share,
                iconDescription = "my instagram",
                onClickFunction = {
                    val i = Intent(Intent.ACTION_VIEW).apply {
                        data = Uri.parse("http://instagram.com/_u/la.tchi.kiff.sa.life")
                        setPackage("com.instagram.android")
                    }
                    try {
                        ctx.startActivity(i)
                    } catch (e: ActivityNotFoundException) {
                        ctx.startActivity(
                            Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("http://instagram/la.tchi.kiff.sa.life")
                            )
                        )
                    }
                }
            )
            BusinessCardDetails(
                labelDetail = "jeparle@latchi.fr",
                imageVector = Icons.Rounded.Email,
                iconDescription = "my email address",
                onClickFunction = {
                    val i = Intent(Intent.ACTION_SEND).apply {
                        //data = Uri.parse("mailto:") // only email apps should handle this
                        putExtra(Intent.EXTRA_EMAIL, arrayOf("jeparle@latchi.fr"))
                        putExtra(Intent.EXTRA_SUBJECT, "(Your company) I want you in my company !")
                        putExtra(Intent.EXTRA_TEXT, "Hello Cibee KAMARA, \n\nI'm interested about your profil and your energy, can we plan a call to talk about business together ? \n\n Thank you for your time, see you soon ! \n\nWith all due respect,\nYour name and your function.")
                        // on below line we are
                        // setting type of intent
                        type = "message/rfc822"
                    }
                    // on the below line we are starting our activity to open email application.
                    ctx.startActivity(Intent.createChooser(i,"Choose an e-mail client : "))
                }
            )
        }
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
                    navController.navigate(Screen.BusinessCardScreen.route)
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
                colorBackground = Color(0xFFFF8900),
                0.5f
            )
            Exo3ScreenQuadrant(
                title = "Jetpack Compose",
                text = "At the start, i was not really enthusiastic about Jetpack Compose cause of this weirdo way to use functions to develop screens. Surely because i’m come from the world of web development ? Don’t know. But after some talks with LouisCAD, i calmed my prejudices, and trust Louis about this tool. For the moment, i love it ;) Let’s see more",
                colorBackground = Color(0xFFCE5CA9),
                0.5f
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
                colorBackground = Color(0xFF00AFFF),
                0.5f
            )
            Exo3ScreenQuadrant(
                title = "Android Studio and co.",
                text = "For the moment, i’m at the very beginning, so it’s hard to say that Android Studio is fabulous but it’s a good IDE for me, and I love Jetbrains Toolbox to help me with different installations. My nemesis 10 years ago. I have some difficulty understanding all things about Gradle but all aids help me to manage it. And I try Refresh Versions too. See also.",
                colorBackground = Color(0xFF8267FE),
                0.5f
            )
        }
    }
}

@Composable
fun RowScope.Exo3ScreenQuadrant(
    title: String,
    text: String,
    colorBackground: Color,
    fraction: Float
) {
    Column(
        modifier = Modifier
            .background(
                color = colorBackground,
                shape = androidx.compose.ui.graphics.RectangleShape
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
            painter = painterResource(id = fr.latchi.latchisapp.R.drawable.ic_task_completed),
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
fun Exo1Screen(navController: NavController){
    Column() {
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
            painter = painterResource(id = fr.latchi.latchisapp.R.drawable.bg_compose_background),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
        )
        Text(
            text = "Cibee KAMARA",
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
}


@Composable
fun LaTchiScreen(navController: NavController) {
    GreetingWithImage(
        navController = navController,
        brand = "LaTchi",
        firstname = "Cibee"
    ) {
        navController.navigate(Screen.Exercise1Screen.route)
    }
}

@Composable
fun Greeting(brand: String, firstname: String, onClickFunction: () -> Unit) {
    Column {
        Text(
            text = "If you love $brand",
            color = Color.White,
            fontWeight = FontWeight.Black,
            fontSize = 46.sp, // pk .dp ne marche pas ??
            modifier = Modifier
                .padding(
                    top = 46.dp,
                    bottom = 0.dp
                )
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
        )
        Text(
            text = "Eat your frog $firstname !",
            color = Color.White,
            fontSize = 24.sp,
            modifier = Modifier
                .padding(
                    bottom = 24.dp
                )
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
        )
        Text(
            text = stringResource(R.string.cheesake),
            fontSize = 24.sp,
            modifier = Modifier
                .padding(
                    top = 420.dp
                )
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
        )
        Text(
            text = "(click on the muffin to see animation)",
            fontSize = 12.sp,
            modifier = Modifier
                .padding(
                    bottom = 50.dp
                )
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
        )
        Button(onClick = { onClickFunction() }) {
            Text(
                text = "Ok, i swallow it !",
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally)
            )
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun GreetingWithImage(
    navController:  NavController,
    brand: String,
    firstname: String,
    onClickFunction: () -> Unit
    ) {
    val imageResIds = remember {
        listOf(R.drawable.androidparty, R.drawable.androidparty_onclick)
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
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .clickable(
                        onClick = {
                            if (imageIndex == imageResIds.lastIndex) imageIndex = 0
                            else imageIndex++
                        }
                    ),
                contentScale = ContentScale.Crop
            )
        }
        Greeting(
            brand = brand,
            firstname = firstname
        ) { onClickFunction() }
    }
}