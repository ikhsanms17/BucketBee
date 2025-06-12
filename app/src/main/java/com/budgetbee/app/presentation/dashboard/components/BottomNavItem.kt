package com.budgetbee.app.presentation.dashboard.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val route: String,
    val icon: ImageVector,
    val label: String
) {
    object Dashboard : BottomNavItem(
        route = "dashboard",
        icon = Icons.Default.Home,
        label = "Dashboard"
    )

    object Add : BottomNavItem(
        route = "add",
        icon = Icons.Default.Add,
        label = "Add"
    )

    object History : BottomNavItem(
        route = "history",
        icon = Icons.Default.Home,
        label = "History"
    )

    object Report : BottomNavItem(
        route = "report",
        icon = Icons.Default.Home,
        label = "Report"
    )

    object Account : BottomNavItem(
        route = "account",
        icon = Icons.Default.Person,
        label = "Account"
    )
}
