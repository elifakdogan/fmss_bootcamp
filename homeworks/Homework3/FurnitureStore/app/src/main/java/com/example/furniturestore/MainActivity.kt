package com.example.furniturestore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.furniturestore.ui.theme.FurnitureStoreTheme
import com.example.furniturestore.ui.theme.Gray
import com.example.furniturestore.ui.theme.Yellow
import com.example.furniturestore.ui.theme.monda
import com.example.furniturestore.R
import com.example.furniturestore.ui.theme.Green
import kotlin.math.round

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FurnitureStoreTheme {
                Anasayfa()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Anasayfa (darkTheme: Boolean = isSystemInDarkTheme()) {
    Scaffold (topBar = {
        CenterAlignedTopAppBar(
            title = { Text(text = stringResource(R.string.title_furniture), fontWeight = FontWeight.Bold, fontFamily = monda, fontSize = 25.sp) },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = if (darkTheme) Green else Yellow,
                titleContentColor = Gray
            )
        )
    }) {paddingValues ->
        Column (modifier = Modifier
            .fillMaxSize()
            .padding(
                start = 22.dp,
                end = 22.dp,
                top = paddingValues.calculateTopPadding(),
                bottom = paddingValues.calculateBottomPadding()
            ),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.Start
        ) {
            Image(
                painter = painterResource(R.drawable.chair),
                contentDescription = "Chair Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally)
            )
            Text(
                text = stringResource(R.string.product_name),
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
            )
            Text(text = stringResource(R.string.product_description),
                fontSize = 18.sp,
                color = Gray)
            Row (modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            )
            {
                Text(text = stringResource(R.string.price_original),
                    textDecoration = TextDecoration.LineThrough,
                    fontWeight = FontWeight.Bold,
                    color = Yellow,
                    fontSize = 25.sp
                )
                Text(text = stringResource(R.string.price_discounted),
                    fontWeight = FontWeight.Bold,
                    color = Gray,
                    fontSize = 25.sp
                )
                Text(text = stringResource(R.string.price_savings),
                    color = Gray,
                    fontSize = 15.sp
                )

            }
            Text(text =stringResource(R.string.dimensions),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
            )
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = stringResource(R.string.overall),
                    fontSize = 16.sp,
                    color = Gray,
                )
                Text(text = stringResource(R.string.overall_size),
                    fontSize = 16.sp,
                    color = Gray,
                )
            }
            Divider(
                color = Gray,
                thickness = 1.dp,
            )
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = stringResource(R.string.seating),
                    fontSize = 16.sp,
                    color = Gray,
                )
                Text(text = stringResource(R.string.seating_size),
                    fontSize = 16.sp,
                    color = Gray,
                )
            }
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Yellow,
                ),
                shape = RoundedCornerShape(25),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = stringResource(R.string.add_to_bag), fontWeight = FontWeight.Bold, fontSize = 22.sp)
            }
        }
    }
}

@Preview(showBackground = true, locale = "tr")
@Composable
fun GreetingPreview() {
    FurnitureStoreTheme {
        Anasayfa()
    }
}