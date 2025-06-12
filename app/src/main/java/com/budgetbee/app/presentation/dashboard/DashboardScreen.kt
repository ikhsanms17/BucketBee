package com.budgetbee.app.presentation.dashboard

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.budgetbee.app.data.database.DatabaseHelper
import com.budgetbee.app.presentation.account.content.AccountContent
import com.budgetbee.app.presentation.dashboard.content.DashboardContent
import com.budgetbee.app.presentation.input.content.AddContent
import com.budgetbee.app.presentation.report.content.ReportContent
import com.budgetbee.app.presentation.history.content.HistoryContent
import com.budgetbee.app.presentation.input.content.AutoVoiceInputScreen
import com.budgetbee.app.presentation.input.content.ManualInputScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(userId: Int, db: DatabaseHelper) {
    val navController = rememberNavController()
    val items = listOf(
        BottomNavItem.Dashboard,
        BottomNavItem.Add,
        BottomNavItem.History,
        BottomNavItem.Report,
        BottomNavItem.Account
    )
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onBackground
    ) {
        Scaffold(
            topBar = {
                val currentRoute =
                    navController.currentBackStackEntryAsState().value?.destination?.route ?: ""
                val currentItem = items.find { it.route == currentRoute } ?: BottomNavItem.Dashboard

                TopAppBar(
                    title = {
                        Text(
                            text = currentItem.label,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = MaterialTheme.colorScheme.onPrimary
                    )
                )
            },
            bottomBar = {
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.surface,
                    contentColor = MaterialTheme.colorScheme.onSurface
                ) {
                    val currentRoute =
                        navController.currentBackStackEntryAsState().value?.destination?.route
                    items.forEach { item ->
                        NavigationBarItem(
                            icon = {
                                Icon(
                                    imageVector = item.icon,
                                    contentDescription = item.label
                                )
                            },
                            label = {
                                Text(
                                    item.label,
                                    color = if (currentRoute == item.route)
                                        MaterialTheme.colorScheme.primary
                                    else
                                        MaterialTheme.colorScheme.onSurface
                                )
                            },
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
                                selectedIconColor = MaterialTheme.colorScheme.primary,
                                selectedTextColor = MaterialTheme.colorScheme.primary,
                                indicatorColor = Color.Transparent,
                                unselectedIconColor = MaterialTheme.colorScheme.onSurface,
                                unselectedTextColor = MaterialTheme.colorScheme.onSurface
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
                composable(BottomNavItem.Dashboard.route) {
                    DashboardContent(userId, db, navController)
                }
                composable(BottomNavItem.Add.route) { AddContent(navController) }
                composable(BottomNavItem.History.route) { HistoryContent() }
                composable(BottomNavItem.Report.route) { ReportContent() }
                composable(BottomNavItem.Account.route) { AccountContent() }
                composable("auto_voice_input") { AutoVoiceInputScreen() }
                composable("manual_input") { ManualInputScreen() }
            }
        }
    }
}
