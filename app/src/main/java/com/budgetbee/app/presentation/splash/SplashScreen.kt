package com.budgetbee.app.presentation.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.budgetbee.app.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    // Responsive screen size
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp

    // Theme colors
    val colorScheme = MaterialTheme.colorScheme
    val primaryColor = colorScheme.primary
    val onPrimaryColor = colorScheme.onPrimary

    // Delay for splash transition
    LaunchedEffect(true) {
        delay(2000)
        navController.navigate("login") {
            popUpTo("splash") { inclusive = true }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(primaryColor),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = screenWidth * 0.1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.lebah),
                contentDescription = "BudgetBee Logo",
                modifier = Modifier.size(screenWidth * 0.3f)
            )

            Spacer(modifier = Modifier.height(screenHeight * 0.03f))

            Text(
                text = "BudgetBee",
                fontSize = (screenWidth.value * 0.08f).sp,
                fontWeight = FontWeight.Bold,
                color = onPrimaryColor
            )

            Spacer(modifier = Modifier.height(screenHeight * 0.015f))

            Text(
                text = "Your smart voice-based money tracker",
                fontSize = (screenWidth.value * 0.04f).sp,
                color = onPrimaryColor
            )
        }
    }
}
