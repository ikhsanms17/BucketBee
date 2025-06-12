package com.budgetbee.app.presentation.input.content

import android.Manifest
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.budgetbee.app.utils.SpeechRecognizerManager

@Composable
fun AutoVoiceInputScreen() {
        val context = LocalContext.current
        val activity = context as ComponentActivity
        var isListening by remember { mutableStateOf(false) }
        var textResult by remember { mutableStateOf("") }
        var editable by remember { mutableStateOf(false) }

        val speechRecognizerManager = remember {
            SpeechRecognizerManager(
                context = context,
                onResult = {
                    textResult = it
                    isListening = false
                },
                onError = {
                    Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                    isListening = false
                }
            )
        }

        val launcher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission()
        ) { granted ->
            if (granted) {
                isListening = true
                speechRecognizerManager.startListening()
            } else {
                Toast.makeText(context, "Izin mikrofon ditolak", Toast.LENGTH_SHORT).show()
            }
        }

        DisposableEffect(Unit) {
            onDispose {
                speechRecognizerManager.destroy()
            }
        }

        Column(modifier = Modifier.padding(16.dp)) {
            OutlinedTextField(
                value = textResult,
                onValueChange = { if (editable) textResult = it },
                enabled = editable,
                label = { Text("Hasil Suara") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                // Tombol Mic selalu ada
                Button(
                    onClick = {
                        if (!isListening) {
                            launcher.launch(Manifest.permission.RECORD_AUDIO)
                        } else {
                            speechRecognizerManager.stopListening()
                            isListening = false
                        }
                    }
                ) {
                    Icon(imageVector = Icons.Default.Mic, contentDescription = "Mic")
                    Spacer(Modifier.width(8.dp))
                    Text(if (isListening) "Berhenti" else "Mulai")
                }

                // Hanya tampil jika ada hasil suara
                if (textResult.isNotBlank()) {
                    Button(onClick = { textResult = ""; editable = false }) {
                        Text("Reset")
                    }

                    Button(onClick = { editable = true }) {
                        Text("Edit")
                    }

                    Button(
                        onClick = {
                            Toast.makeText(context, "Terkonfirmasi: $textResult", Toast.LENGTH_SHORT).show()
                        },
                        enabled = textResult.isNotBlank()
                    ) {
                        Text("Konfirmasi")
                    }
                }
            }

        }
    }
