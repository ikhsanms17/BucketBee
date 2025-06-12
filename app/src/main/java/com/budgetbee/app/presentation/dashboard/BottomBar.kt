package com.budgetbee.app.presentation.dashboard

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val route: String, val label: String, val icon: ImageVector) {
    object Dashboard : BottomNavItem("dashboard", "Dashboard", Icons.Default.Home)
    object Add : BottomNavItem("add", "Add", Icons.Default.AddCircle)
    object History : BottomNavItem("history", "History", Icons.Default.History)
    object Report : BottomNavItem("report", "Report", Icons.Default.BarChart)
    object Account : BottomNavItem("account", "Account", Icons.Default.Person)
}
