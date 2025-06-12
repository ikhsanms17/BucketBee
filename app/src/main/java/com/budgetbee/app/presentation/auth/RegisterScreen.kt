package com.budgetbee.app.presentation.auth

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

//@Composable
//fun RegisterScreen(navToLogin: () -> Unit) {
//    val context = LocalContext.current
//    var email by remember { mutableStateOf("") }
//    var password by remember { mutableStateOf("") }
//
//    Column(Modifier.padding(16.dp)) {
//        Text("Register")
//        OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
//        OutlinedTextField(value = password, onValueChange = { password = it }, label = { Text("Password") })
//
//        Button(onClick = {
//            UserPreference(context).saveUser(email, password)
//            Toast.makeText(context, "Berhasil Register", Toast.LENGTH_SHORT).show()
//            navToLogin()
//        }) {
//            Text("Daftar")
//        }
//    }
//}
