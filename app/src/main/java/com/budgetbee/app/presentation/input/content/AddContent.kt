package com.budgetbee.app.presentation.input.content

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController

@Composable
fun AddContent(navController: NavController) {
    // Theme colors
    val colorScheme = MaterialTheme.colorScheme
    val primaryColor = colorScheme.primary
    val onBackgroundColor = colorScheme.onBackground
    val buttonColor = colorScheme.primary
    val textOnButton = colorScheme.onPrimary

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Add Transaction",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = onBackgroundColor // ganti dari Color(0xFF00C88F) ke warna yang sesuai tema
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { navController.navigate("auto_voice_input") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = buttonColor,
                contentColor = textOnButton
            )
        ) {
            Text("Start Auto With Your Voice")
        }

        Button(
            onClick = { navController.navigate("manual_input") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = buttonColor,
                contentColor = textOnButton
            )
        ) {
            Text("Start Manual With Your Text")
        }
    }
}
