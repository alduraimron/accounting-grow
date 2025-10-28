package com.alduraimron.accountinggrow.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Backspace
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun PinScreen(navController: NavHostController) {
    val pin = remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Masukkan PIN",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(top = 32.dp)
            )

            Text(
                text = "Masukkan PIN untuk lanjut",
                fontSize = 14.sp,
                color = Color.Gray,
                modifier = Modifier.padding(top = 8.dp)
            )

            // PIN display
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 32.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterHorizontally)
            ) {
                repeat(6) { index ->
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(
                                if (index < pin.value.length) Color(0xFF1E88E5) else Color(
                                    0xFFE0E0E0
                                ),
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(4.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        if (index < pin.value.length) {
                            Text(
                                text = "•",
                                fontSize = 24.sp,
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }

            // Numeric keypad
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(12.dp),
                verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(12.dp)
            ) {
                items(12) { index ->
                    when (index) {
                        9 -> {
                            // Empty space
                            Box(modifier = Modifier.size(60.dp))
                        }
                        10 -> {
                            // 0 button
                            PinButton(
                                text = "0",
                                onClick = {
                                    if (pin.value.length < 6) {
                                        pin.value += "0"
                                    }
                                }
                            )
                        }
                        11 -> {
                            // Backspace button
                            Box(
                                modifier = Modifier
                                    .size(60.dp)
                                    .background(Color(0xFFE0E0E0), shape = RoundedCornerShape(8.dp))
                                    .clickable {
                                        if (pin.value.isNotEmpty()) {
                                            pin.value = pin.value.dropLast(1)
                                        }
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Backspace,
                                    contentDescription = "Backspace",
                                    tint = Color.Black
                                )
                            }
                        }
                        else -> {
                            PinButton(
                                text = (index + 1).toString(),
                                onClick = {
                                    if (pin.value.length < 6) {
                                        pin.value += (index + 1).toString()
                                    }
                                }
                            )
                        }
                    }
                }
            }

            Box(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    if (pin.value.length == 6) {
                        navController.navigate("dashboard") {
                            popUpTo("pin") { inclusive = true }
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF1E88E5)
                ),
                shape = RoundedCornerShape(12.dp),
                enabled = pin.value.length == 6
            ) {
                Text(
                    text = "Lanjut",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun PinButton(text: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(60.dp)
            .background(Color(0xFFE0E0E0), shape = RoundedCornerShape(8.dp))
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}