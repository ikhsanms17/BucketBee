package com.budgetbee.app.presentation.dashboard

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.budgetbee.app.presentation.account.content.AccountContent
import com.budgetbee.app.presentation.dashboard.content.DashboardContent
import com.budgetbee.app.presentation.input.content.AddContent
import com.budgetbee.app.presentation.report.content.ReportContent
import com.budgetbee.app.presentation.history.content.HistoryContent
import com.budgetbee.app.presentation.input.content.AutoVoiceInputScreen
import com.budgetbee.app.presentation.input.content.ManualInputScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen() {
    val navController = rememberNavController()
    val items = listOf(
        BottomNavItem.Dashboard,
        BottomNavItem.Add,
        BottomNavItem.History,
        BottomNavItem.Report,
        BottomNavItem.Account
    )

    Scaffold(
        topBar = {
            val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route ?: ""
            val currentItem = items.find { it.route == currentRoute } ?: BottomNavItem.Dashboard

            TopAppBar(
                title = { Text(text = currentItem.label) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF00C88F))
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = Color.White,
                contentColor = Color.Gray
            ) {
                val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
                items.forEach { item ->
                    NavigationBarItem(
                        icon = { Icon(imageVector = item.icon, contentDescription = item.label) },
                        label = { Text(item.label) },
                        selected = currentRoute == item.route,
                        onClick = {
                            if (currentRoute != item.route) {
                                navController.navigate(item.route) {
                                    popUpTo(navController.graph.startDestinationId)
                                    launchSingleTop = true
                                }
                            }
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color(0xFF00C88F),
                            selectedTextColor = Color(0xFF00C88F),
                            indicatorColor = Color.Transparent,
                            unselectedIconColor = Color.Gray,
                            unselectedTextColor = Color.Gray
                        )
                    )
                }
            }
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.Dashboard.route,
            modifier = Modifier.padding(padding)
        ) {
            composable(BottomNavItem.Dashboard.route) { DashboardContent() }
            composable(BottomNavItem.Add.route) { AddContent(navController) }
            composable(BottomNavItem.History.route) { HistoryContent() }
            composable(BottomNavItem.Report.route) { ReportContent() }
            composable(BottomNavItem.Account.route) { AccountContent() }

            // ✅ Tambahkan route baru
            composable("auto_voice_input") { AutoVoiceInputScreen() }
            composable("manual_input") { ManualInputScreen() } // Jika ada manual input
        }
    }
}
