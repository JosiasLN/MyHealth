package com.example.myhealth.presentation.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myhealth.R
import com.google.android.material.color.MaterialColors
import kotlin.math.pow
import androidx.compose.material.TextField as TextField


//Diseñar pantalla
@Composable
fun Imc(
    navegarResultImc: (String) -> Unit
) {

    val estatura = rememberSaveable{ mutableStateOf("") }
    val peso = rememberSaveable{ mutableStateOf("") }
    val resultImc = remember { mutableStateOf(0.0) }

    val focusManager = LocalFocusManager.current
    val scrollState = rememberScrollState()
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        Image(
            painter = painterResource(id = R.drawable.fondo_app),
            contentDescription = "Fondo App",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(state = scrollState),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Indice de Masa Corporal",
                    style = TextStyle(
                        fontSize = 32.sp,
                        textAlign = TextAlign.Center,
                        color = Color.Black
                    )
                )

                Spacer(modifier = Modifier.size(8.dp))
                TextField(
                    value = estatura.value,
                    onValueChange = { if (it.length <= 3) estatura.value = it },
                    label = { Text(text = "Estatura (cm)" )},
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = {
                            Log.i("TAG", "Click")
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    )
                )

                TextField(
                    value = peso.value,
                    onValueChange = { if (it.length <= 4) peso.value = it },
                    label = { Text(text = "Peso (kg)" )},
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            Log.i("TAG", "Click")
                            focusManager.clearFocus()
                        }
                    )
                )

                Spacer(modifier = Modifier.size(8.dp))
                Button(
                    shape = RoundedCornerShape(25),
                    onClick = {
                        try {
                            if (estatura.value.isEmpty() || peso.value.isEmpty()) {
                                Toast.makeText(
                                    context,
                                    "Favor de llenar todo los campos",
                                    Toast.LENGTH_LONG
                                ).show()
                            } else if (peso.value.toDouble() <= 0.0) {
                                Toast.makeText(
                                    context,
                                    "Favor de usar otro valor para el peso",
                                    Toast.LENGTH_LONG
                                ).show()
                            } else if (estatura.value.toDouble() <= 0.0) {
                                Toast.makeText(
                                    context,
                                    "Favor de usar otro valor para la estatura",
                                    Toast.LENGTH_LONG
                                ).show()
                            } else {
                                resultImc.value =
                                    (peso.value.toDouble()) / (estatura.value.toDouble() / 100).pow(
                                        2
                                    )
                                if (resultImc.value >= 50.0) {
                                    Toast.makeText(
                                        context,
                                        "Parece que este IMC no es correcto \n " +
                                                "Asegúrese de que la altura y el peso sean correctos",
                                        Toast.LENGTH_LONG
                                    ).show()
                                } else {
                                    resultImc.value.toString()
                                    Log.i("resultIMC", "" + resultImc.value)
                                    navegarResultImc(String.format("%.2f", resultImc.value))
                                }
                            }
                        }catch (e: Exception){
                            Toast.makeText(
                                context,
                                "Favor de no usar el simbolo de coma (,)",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                ) {
                    Text("CALCULAR")
                }
            }
        }
    }
}