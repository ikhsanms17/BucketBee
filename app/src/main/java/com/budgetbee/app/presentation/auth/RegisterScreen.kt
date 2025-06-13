package com.budgetbee.app.presentation.auth

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.budgetbee.app.data.database.DatabaseHelper

@Composable
fun RegisterScreen(navController: NavController, db: DatabaseHelper) {
    val colorScheme = MaterialTheme.colorScheme
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var nameError by remember { mutableStateOf(false) }
    var emailError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }

    var showError by remember { mutableStateOf(false) }
    var message by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column (
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
            "Register",
            fontSize = (screenWidth.value * 0.07f).sp,
            fontWeight = FontWeight.Bold,
            color = colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = name,
            onValueChange = {
                name = it
                nameError = false
            },
            label = { Text("Name", color = colorScheme.onSurface) },
            modifier = Modifier.fillMaxWidth(),
            textStyle = MaterialTheme.typography.bodyLarge.copy(color = colorScheme.onSurface),
            isError = nameError,
            supportingText = {
                if (nameError) Text("Nama tidak boleh kosong", color = MaterialTheme.colorScheme.error)
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
                emailError = false
            },
            label = { Text("Email", color = colorScheme.onSurface) },
            modifier = Modifier.fillMaxWidth(),
            textStyle = MaterialTheme.typography.bodyLarge.copy(color = colorScheme.onSurface),
            isError = emailError,
            supportingText = {
                if (emailError) Text("Email harus diakhiri dengan @gmail.com", color = MaterialTheme.colorScheme.error)
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                passwordError = false
            },
            label = { Text("Password", color = colorScheme.onSurface) },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth(),
            textStyle = MaterialTheme.typography.bodyLarge.copy(color = colorScheme.onSurface),
            isError = passwordError,
            supportingText = {
                if (passwordError) Text("Password tidak boleh kosong", color = MaterialTheme.colorScheme.error)
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = {
            nameError = name.isBlank()
            emailError = email.isBlank() || !email.endsWith("@gmail.com")
            passwordError = password.isBlank()

            if (!nameError && !emailError && !passwordError) {
                if (db.registerUser(name, email, password)) {
                    message = "Register success"
                    navController.navigate("login")
                } else {
                    message = "Register failed"
                }

                Log.d("RegisterForm", "Nama: $name, Email: $email, Password: $password")
            }
        },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorScheme.primary,
                contentColor = colorScheme.onPrimary
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Register")
        }
        TextButton(onClick = { navController.navigate("login") }) {
            Text(
                "Already have an account? Login",
                color = colorScheme.primary
            )
        }
    }
}