package com.budgetbee.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.budgetbee.app.core.theme.BudgetBeeTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.budgetbee.app.data.database.DatabaseHelper
import com.budgetbee.app.presentation.splash.SplashScreen
import com.budgetbee.app.presentation.auth.LoginScreen
import com.budgetbee.app.presentation.auth.RegisterScreen
import com.budgetbee.app.presentation.dashboard.DashboardScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val db = DatabaseHelper(this)
        enableEdgeToEdge()
        setContent {
            BudgetBeeTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    AppNavigation(db)
                }
            }
        }
    }
}

@Composable
fun AppNavigation(db: DatabaseHelper) {
    val navController: NavHostController = rememberNavController()

    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") { SplashScreen(navController) }
        composable("login") { LoginScreen(navController, db) }
        composable("register") { RegisterScreen(navController, db) }
        composable(
            route = "dashboard/{userId}",
            arguments = listOf(navArgument("userId") { type = NavType.IntType })
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getInt("userId") ?: -1
            DashboardScreen(userId, db)
        }
    }
}