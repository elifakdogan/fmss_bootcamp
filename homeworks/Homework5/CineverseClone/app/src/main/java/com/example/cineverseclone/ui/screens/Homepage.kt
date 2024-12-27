package com.example.cineverseclone.ui.screens

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cineverseclone.R
import com.example.cineverseclone.data.entity.Sliders
import com.example.cineverseclone.data.entity.UpComingVisionaries
import com.example.cineverseclone.data.entity.Visionaries
import com.example.cineverseclone.ui.theme.Background

@Composable
fun Homepage() {
    var selectedItem = remember { mutableStateOf(6) }
    val sliderList = remember { mutableStateListOf<Sliders>() }
    val visionariesList = remember { mutableStateListOf<Visionaries>() }
    val upComingVisionariesList = remember { mutableStateListOf<UpComingVisionaries>() }

    LaunchedEffect(key1 = true) {
        val s1 = Sliders(1, "Rehber Ercüment ve Komedi Dolu Tur", "image1")
        val s2 = Sliders(2, "Amansız Bir İntikam Serüveni", "image2")
        val s3 = Sliders(3, "rafadan Tayfa", "image3")
        sliderList.addAll(listOf(s1,s2,s3))
    }

    LaunchedEffect(key1 = true) {
        val v1 = Visionaries(1,"visionaries1")
        val v2 = Visionaries(2,"visionaries2")
        val v3 = Visionaries(3,"visionaries3")
        visionariesList.addAll(listOf(v1,v2,v3))
    }

    LaunchedEffect(key1 = true) {
        val u1 = UpComingVisionaries(1,"up1")
        val u2 = UpComingVisionaries(2,"up2")
        val u3 = UpComingVisionaries(3,"up3")
        upComingVisionariesList.addAll(listOf(u1,u2,u3))
    }

    Scaffold(
        topBar = { HomepageTopBar() },
        bottomBar = { HomepageBottomBar(selectedItem) },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState())
            ) {
                SliderSection(sliderListesi = sliderList)
                //SliderIndicators(sliderCount = sliderList.size)
                Spacer(modifier = Modifier.height(16.dp))

                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(text = "Vizyondakiler", fontSize = 18.sp, fontWeight = FontWeight(500))
                    Text(text = "Tümü", fontSize = 16.sp, textDecoration = TextDecoration.Underline)
                }
                Spacer(modifier = Modifier.height(16.dp))

                VisionariesSection(visionariesList = visionariesList)
                Spacer(modifier = Modifier.height(16.dp))

                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(text = "Yakında Vizyona Girecekler", fontSize = 18.sp, fontWeight = FontWeight(500))
                    Text(text = "Tümü", fontSize = 16.sp, textDecoration = TextDecoration.Underline)
                }
                Spacer(modifier = Modifier.height(16.dp))

                UpComingVisionariesSection(upComingVisionariesList = upComingVisionariesList)
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    )
}

@Composable
fun HomepageTopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.menu_icon),
            contentDescription = "Menu",
            modifier = Modifier.size(28.dp),
        )
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier.size(140.dp)
        )
        Row {
            Icon(
                painter = painterResource(id = R.drawable.ticket_icon),
                contentDescription = "Ticket",
                modifier = Modifier.size(24.dp),
            )
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                painter = painterResource(id = R.drawable.popcorn_icon),
                contentDescription = "Popcorn",
                modifier = Modifier.size(24.dp),
            )
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                painter = painterResource(id = R.drawable.search_icon),
                contentDescription = "Search",
                modifier = Modifier.size(20.dp),
            )
        }
    }
}

@Composable
fun HomepageBottomBar(selectedItem: MutableState<Int>) {
    BottomAppBar (
        containerColor = Color(0xFF2E2E2E)
    ){
        NavigationBarItem(
            selected = selectedItem.value == 0,
            onClick = { selectedItem.value = 0 },
            label = { Text(text = "Anasayfa") },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.homepage_icon),
                    contentDescription = "",
                )
            }
        )
        NavigationBarItem(
            selected = selectedItem.value == 1,
            onClick = { selectedItem.value = 1 },
            label = { Text(text = "Filmler", color = Color.Gray) },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.movies_icon),
                    contentDescription = "",
                    tint = Color.Gray
                )
            },
        )
        NavigationBarItem(
            selected = selectedItem.value == 2,
            onClick = { selectedItem.value = 2 },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.cinema_club),
                    contentDescription = "",
                    tint = Color.Gray
                )
            }
        )
        NavigationBarItem(
            selected = selectedItem.value == 3,
            onClick = { selectedItem.value = 3 },
            label = { Text(text = "Sinemalar",color = Color.Gray) },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.location_icon),
                    contentDescription = "",
                    tint = Color.Gray
                )
            }
        )
        NavigationBarItem(
            selected = selectedItem.value == 4,
            onClick = { selectedItem.value = 4 },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.cgv_pass),
                    contentDescription = "",
                    tint = Color.Gray
                )
            }
        )
    }
}

@Composable
fun SliderIndicators(sliderCount: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(sliderCount) { index ->
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .padding(horizontal = 4.dp)
                    .background(
                        if (index == 0) Color.White else Color.Gray,
                        shape = CircleShape
                    )
            )
        }
    }
}

@Composable
fun SliderSection(sliderListesi: List<Sliders>) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp),
        horizontalArrangement = Arrangement.spacedBy(22.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(sliderListesi) { slider ->
            SliderItem(slider = slider)
        }
    }
}

@Composable
fun SliderItem(slider: Sliders) {
    val activity = LocalContext.current as Activity
    Card(
        modifier = Modifier
            .size(375.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Image(
                bitmap = ImageBitmap.imageResource(
                    id = activity.resources.getIdentifier(slider.image, "drawable", activity.packageName)
                ),
                contentDescription = slider.name,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Background)
                    .padding(top = 15.dp, bottom = 15.dp)
            ) {
                Text(
                    text = slider.name,
                    fontSize = 20.sp,
                    color = Color.White,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun VisionariesSection(visionariesList: List<Visionaries>) {
    LazyRow(
        modifier = Modifier
            .height(250.dp),
        horizontalArrangement = Arrangement.spacedBy(22.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(visionariesList) { vision ->
            VisionItem(vision = vision)
        }
    }
}

@Composable
fun VisionItem(vision: Visionaries) {
    val activity = LocalContext.current as Activity
    Card(
        modifier = Modifier
            .height(250.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Image(
                bitmap = ImageBitmap.imageResource(
                    id = activity.resources.getIdentifier(vision.image, "drawable", activity.packageName)
                ),
                contentDescription = "",
                modifier = Modifier
                    .height(250.dp),
                contentScale = ContentScale.Crop
            )

        }
    }
}

@Composable
fun UpComingVisionariesSection(upComingVisionariesList: List<UpComingVisionaries>) {
    LazyRow(
        modifier = Modifier
            .height(180.dp),
        horizontalArrangement = Arrangement.spacedBy(22.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(upComingVisionariesList) { coming ->
            UpComingVisionariesItem(coming = coming)
        }
    }
}

@Composable
fun UpComingVisionariesItem(coming: UpComingVisionaries) {
    val activity = LocalContext.current as Activity
    Card(
        modifier = Modifier
            .height(180.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Image(
                bitmap = ImageBitmap.imageResource(
                    id = activity.resources.getIdentifier(coming.image, "drawable", activity.packageName)
                ),
                contentDescription = "",
                modifier = Modifier
                    .height(180.dp),
                contentScale = ContentScale.Crop
            )

        }
    }
}

