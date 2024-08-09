package com.example.taskmate

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.taskmate.page.dashboard_page.DashboardPage
import com.example.taskmate.page.login_page.LoginPage
import com.example.taskmate.page.registration_page.RegistrationPage
import com.example.taskmate.page.splash_page.SplashPage
import com.example.taskmate.ui.theme.TaskMateTheme
import com.example.taskmate.viewmodel.LoginViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaskMateTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val enterTransition = {
        slideInHorizontally(
            initialOffsetX = { fullWidth -> fullWidth }
        ) + fadeIn(initialAlpha = 0F)
    }
    val exitTransition = {
        slideOutHorizontally(
            targetOffsetX = { fullWidth -> fullWidth }
        ) + fadeOut(targetAlpha = 0F)
    }
    NavHost(
        navController = navController,
        startDestination = "/dashboard",
        exitTransition = { exitTransition() },
        enterTransition = { enterTransition() }) {
        composable(
            "/",
        ) {
            SplashPage(navController)
        }
        composable("/login") {
            LoginPage(navController)
        }
        composable("/registration") {
            RegistrationPage(navController)
        }
        composable("/dashboard") {
            DashboardPage(navController)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TaskMateTheme {
        Greeting("Android")
    }
}