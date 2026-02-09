package com.example.happybirthday.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun HomeScreen(modifier: Modifier = Modifier, navController: NavController) {
    Column (
        modifier = modifier.fillMaxSize()
            .verticalScroll(rememberScrollState(),),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        ) {
        Button(onClick = {navController.navigate("birthday")}){
            Text(text = "Birthday Screen \uD83C\uDF82")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navController.navigate("compose-article") }) {
            Text("Compose Article Screen")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navController.navigate("task-success") }) {
            Text("Task Success Screen")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navController.navigate("compose-quadrant") }) {
            Text("Compose Quadrant Screen")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navController.navigate("business-card") }) {
            Text("Business Card Screen")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navController.navigate("dice-roller") }) {
            Text("Dice Roller Screen")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navController.navigate("lemonade") }) {
            Text("Lemonade Screen")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navController.navigate("tip-calculator") }) {
            Text("Tip Calculator Screen")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navController.navigate("art-space") }) {
            Text("Art Space Screen")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navController.navigate("affirmations") }) {
            Text("Affirmations Screen")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navController.navigate("courses") }) {
            Text("Courses Screen")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navController.navigate("woof") }) {
            Text("Woof Screen")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navController.navigate("super-heroes") }) {
            Text("Super Heroes Screen")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navController.navigate("wellness-goals") }) {
            Text("30 Days of Wellness Screen")
        }
    }
}
