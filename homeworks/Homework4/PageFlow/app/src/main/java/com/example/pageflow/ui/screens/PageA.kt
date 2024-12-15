package com.example.pageflow.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun PageA(navController: NavController) {
    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Page A", fontSize = 30.sp)
        Button(
        modifier = Modifier.padding(8.dp),
        shape = RoundedCornerShape(16.dp),
        onClick = {
            navController.navigate("PageB"){
                popUpTo("PageB") {inclusive = true}
            }
        },
        colors = ButtonDefaults.buttonColors(
            contentColorFor(backgroundColor = Color.Blue)
        )
        ) {
            Text(text = "Page B")

        }
    }
}