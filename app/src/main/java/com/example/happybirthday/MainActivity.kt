package com.example.happybirthday

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.happybirthday.ui.screens.BirthdayScreen
import com.example.happybirthday.ui.screens.businesscardscreen.BusinessCardScreen
import com.example.happybirthday.ui.screens.ComposeArticle
import com.example.happybirthday.ui.screens.ComposeQuadrantScreen
import com.example.happybirthday.ui.screens.DiceRollerScreen
import com.example.happybirthday.ui.screens.HomeScreen
import com.example.happybirthday.ui.screens.LemonadeScreen
import com.example.happybirthday.ui.screens.TaskSuccess
import com.example.happybirthday.ui.screens.TipCalculator
import com.example.happybirthday.ui.theme.HappyBirthdayTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            HappyBirthdayTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "home"
                    ) {
                        composable("home") {
                            HomeScreen(modifier = Modifier.padding(innerPadding), navController)
                        }
                        composable("birthday") {
                            BirthdayScreen(navController, modifier = Modifier.padding(innerPadding))
                        }
                        composable("compose-article") {
                            ComposeArticle(modifier = Modifier.padding(innerPadding))
                        }
                        composable("task-success") {
                            TaskSuccess(modifier = Modifier.padding(innerPadding))
                        }
                        composable("compose-quadrant") {
                            ComposeQuadrantScreen(modifier = Modifier.padding(innerPadding))
                        }
                        composable("business-card") {
                            BusinessCardScreen(modifier = Modifier.padding(innerPadding))
                        }
                        composable("dice-roller") {
                            DiceRollerScreen(modifier = Modifier.padding(innerPadding))
                        }
                        composable("lemonade") {
                            LemonadeScreen(modifier = Modifier.padding(innerPadding))
                        }

                        composable("tip-calculator") {
                            TipCalculator(modifier = Modifier.padding(innerPadding))
                        }
                    }
                }
            }
        }
    }
}