package com.budgetbee.app.presentation.auth

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.ui.text.font.FontWeight
import com.budgetbee.app.data.database.DatabaseHelper


@Composable
fun LoginScreen(navController: NavController, db: DatabaseHelper) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }

    val colorScheme = MaterialTheme.colorScheme
    val textStyle = MaterialTheme.typography.bodyLarge.copy(color = colorScheme.onSurface)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = colorScheme.onBackground
                )
            }
        }

        Spacer(modifier = Modifier.height(screenHeight * 0.05f))

        Text(
            "Login",
            fontSize = (screenWidth.value * 0.07f).sp,
            fontWeight = FontWeight.Bold,
            color = colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
                showError = false
            },
            label = { Text("Email", color = colorScheme.onSurface) },
            modifier = Modifier.fillMaxWidth(),
            textStyle = textStyle,
            isError = showError
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                showError = false
            },
            label = { Text("Password", color = colorScheme.onSurface) },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth(),
            textStyle = textStyle,
            isError = showError
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                if (db.checkLogin(email.trim(), password)) {
                    val userId = db.getUserIdByEmailPassword(email, password)
                    if (userId != null) {
                        navController.navigate("dashboard/$userId")
                    } else {
                        message = "Terjadi kesalahan saat mengambil ID pengguna"
                        showError = true
                    }
                } else {
                    message = "Email atau password salah"
                    showError = true
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorScheme.primary,
                contentColor = colorScheme.onPrimary
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Log in")
        }

        if (showError) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(message, color = colorScheme.error)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Don't have an account?", color = colorScheme.onBackground)
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                "Register",
                color = colorScheme.primary,
                modifier = Modifier.clickable {
                    navController.navigate("register")
                }
            )
        }
    }
}