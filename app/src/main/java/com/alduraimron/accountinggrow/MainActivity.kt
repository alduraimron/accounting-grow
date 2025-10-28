package com.alduraimron.accountinggrow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alduraimron.accountinggrow.ui.screens.DashboardScreen
import com.alduraimron.accountinggrow.ui.screens.LoginScreen
import com.alduraimron.accountinggrow.ui.screens.OnboardingScreen
import com.alduraimron.accountinggrow.ui.screens.PinScreen
import com.alduraimron.accountinggrow.ui.screens.ReportScreen
import com.alduraimron.accountinggrow.ui.screens.SplashScreen
import com.alduraimron.accountinggrow.ui.screens.TransactionScreen
import com.alduraimron.accountinggrow.ui.theme.AccountingGrowTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AccountingGrowTheme {
                Surface (
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {
        composable("splash") {
            SplashScreen(navController)
        }
        composable("onboarding") {
            OnboardingScreen(navController)
        }
        composable("login") {
            LoginScreen(navController)
        }
        composable("pin") {
            PinScreen(navController)
        }
        composable("dashboard") {
            DashboardScreen(navController)
        }
        composable("transaction") {
            TransactionScreen(navController)
        }
        composable("report") {
            ReportScreen(navController)
        }
    }
}
