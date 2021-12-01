package com.example.myhealth.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

//Eliminal componente
@Composable
fun ResultDialog(
    titulo: String,
    resultado: String,
    color: Color,
    isDialogOpen: MutableState<Boolean>,
    guardarResult: MutableState<Boolean>,
){

    Dialog(onDismissRequest = { isDialogOpen.value = false }) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = titulo,
                style = TextStyle(
                    color = MaterialTheme.colors.onSurface,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            )
            Spacer(modifier = Modifier.size(16.dp))
            Text(
                text = resultado,
                style = TextStyle(
                    color = color,
                    fontSize = 25.sp,
                )
            )
            Spacer(modifier = Modifier.size(21.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {
                        isDialogOpen.value = false
                    }
                ) {
                    Text(text = "Cerrar", fontSize = 16.sp)
                }
                Spacer(modifier = Modifier.size(8.dp))
                Button(
                    onClick = {
                        guardarResult.value = true
                    }
                ) {
                    Text(text = "Guardad Resultado", fontSize = 16.sp)
                }

            }
        }
    }
}

