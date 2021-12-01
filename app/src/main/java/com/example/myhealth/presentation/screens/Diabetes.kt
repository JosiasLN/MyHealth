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
import androidx.compose.material.*
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myhealth.R


object TrueFalse {
    const val si = "Si"
    const val no = "No"
    const val si2 = "Si (padres, hermanos, hijos)"
}

@Composable
fun Diabetes(
    navegarImc: () -> Unit,
    navegarResultDiab: (Int) -> Unit
) {

    var edad by rememberSaveable { mutableStateOf("") }
    var imc by rememberSaveable { mutableStateOf("") }
    var cintura by rememberSaveable { mutableStateOf("") }
    var puntos by rememberSaveable { mutableStateOf(0) }
    val selectedGender = rememberSaveable { mutableStateOf("") }
    val selectedQ1 = rememberSaveable { mutableStateOf("") }
    val selectedQ2 = rememberSaveable { mutableStateOf("") }
    val selectedQ3 = rememberSaveable { mutableStateOf("") }
    val selectedQ4 = rememberSaveable { mutableStateOf("") }
    val selectedQ5 = rememberSaveable { mutableStateOf("") }

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
                verticalArrangement = Arrangement.spacedBy(21.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Riesgo de desarrollar Diabetes",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Black,
                        textAlign = TextAlign.Center
                    )
                )

                TextField(
                    value = edad,
                    onValueChange = { if (it.length <= 3) edad = it },
                    label = { Text(text = "Edad") },
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

                Row {
                    RadioButton(
                        selected = selectedGender.value == Gender.male,
                        onClick = { selectedGender.value = Gender.male },
                        colors = RadioButtonDefaults.colors(Color.Red)
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                    Text(Gender.male)
                    Spacer(modifier = Modifier.size(16.dp))
                    RadioButton(
                        selected = selectedGender.value == Gender.female,
                        onClick = { selectedGender.value = Gender.female },
                        colors = RadioButtonDefaults.colors(Color.Red)
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                    Text(Gender.female)
                }

                Row {
                    TextField(
                        modifier = Modifier
                            .width(150.dp),
                        value = imc,
                        onValueChange = { if (it.length <= 5) imc = it },
                        label = { Text(text = "IMC") },
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
                    Spacer(modifier = Modifier.size(14.dp))
                    Button(
                        shape = RoundedCornerShape(25),
                        onClick = {
                            navegarImc()
                        }
                    ) {

                        Text(
                            "Calcular mi IMC",
                            style = TextStyle(
                                color = Color.Black,
                                fontSize = 12.sp
                            )
                        )
                    }
                }

                TextField(
                    value = cintura,
                    onValueChange = { if (it.length <= 5) cintura = it },
                    label = { Text(text = "Cintura (cm)") },
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

                Text(
                    text = "¿Realiza ejercicio habitualmente?",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Black,
                        textAlign = TextAlign.Center
                    )
                )
                Row {
                    RadioButton(
                        selected = selectedQ1.value == TrueFalse.si,
                        onClick = { selectedQ1.value = TrueFalse.si },
                        colors = RadioButtonDefaults.colors(Color.Red)
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                    Text(TrueFalse.si)
                    Spacer(modifier = Modifier.size(16.dp))
                    RadioButton(
                        selected = selectedQ1.value == TrueFalse.no,
                        onClick = { selectedQ1.value = TrueFalse.no },
                        colors = RadioButtonDefaults.colors(Color.Red)
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                    Text(TrueFalse.no)
                }


                Text(
                    text = "¿Come a diario frutas o verduras?",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Black,
                        textAlign = TextAlign.Center
                    )
                )
                Row {
                    RadioButton(
                        selected = selectedQ2.value == TrueFalse.si,
                        onClick = { selectedQ2.value = TrueFalse.si },
                        colors = RadioButtonDefaults.colors(Color.Red)
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                    Text(TrueFalse.si)
                    Spacer(modifier = Modifier.size(16.dp))
                    RadioButton(
                        selected = selectedQ2.value == TrueFalse.no,
                        onClick = { selectedQ2.value = TrueFalse.no },
                        colors = RadioButtonDefaults.colors(Color.Red)
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                    Text(TrueFalse.no)
                }

                Text(
                    text = "¿Toma medicación para la hipertensión regularmente?",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Black,
                        textAlign = TextAlign.Center
                    )
                )
                Row {
                    RadioButton(
                        selected = selectedQ3.value == TrueFalse.si,
                        onClick = { selectedQ3.value = TrueFalse.si },
                        colors = RadioButtonDefaults.colors(Color.Red)
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                    Text(TrueFalse.si)
                    Spacer(modifier = Modifier.size(16.dp))
                    RadioButton(
                        selected = selectedQ3.value == TrueFalse.no,
                        onClick = { selectedQ3.value = TrueFalse.no },
                        colors = RadioButtonDefaults.colors(Color.Red)
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                    Text(TrueFalse.no)
                }

                Text(
                    text = "¿Le han encontrado alguna vez valores de glucemia altos?",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Black,
                        textAlign = TextAlign.Center
                    )
                )
                Row {
                    RadioButton(
                        selected = selectedQ4.value == TrueFalse.si,
                        onClick = { selectedQ4.value = TrueFalse.si },
                        colors = RadioButtonDefaults.colors(Color.Red)
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                    Text(TrueFalse.si)
                    Spacer(modifier = Modifier.size(16.dp))
                    RadioButton(
                        selected = selectedQ4.value == TrueFalse.no,
                        onClick = { selectedQ4.value = TrueFalse.no },
                        colors = RadioButtonDefaults.colors(Color.Red)
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                    Text(TrueFalse.no)
                }

                Text(
                    text = "¿Algún familiar está diagnosticado de diabetes (tipo I o tipo II)?",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Black,
                        textAlign = TextAlign.Center
                    )
                )

                Row {
                    RadioButton(
                        selected = selectedQ5.value == TrueFalse.no,
                        onClick = { selectedQ5.value = TrueFalse.no },
                        colors = RadioButtonDefaults.colors(Color.Red)
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                    Text(TrueFalse.no)
                    Spacer(modifier = Modifier.size(16.dp))

                    RadioButton(
                        selected = selectedQ5.value == TrueFalse.si,
                        onClick = { selectedQ5.value = TrueFalse.si },
                        colors = RadioButtonDefaults.colors(Color.Red)
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                    Text(TrueFalse.si + "(abuelos, tios, primos)")
                    Spacer(modifier = Modifier.size(16.dp))

                    RadioButton(
                        selected = selectedQ5.value == TrueFalse.si2,
                        onClick = { selectedQ5.value = TrueFalse.si2 },
                        colors = RadioButtonDefaults.colors(Color.Red)
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                    Text(TrueFalse.si2)
                }

                Log.i("Else de ResultDialog", "Si")

                Button(
                    shape = RoundedCornerShape(25),
                    onClick = {
                        try {
                            if (edad.isNotEmpty() && imc.isNotEmpty() && selectedGender.value.isNotEmpty() && cintura.isNotEmpty()
                                && selectedQ1.value.isNotEmpty() && selectedQ2.value.isNotEmpty()
                                && selectedQ3.value.isNotEmpty() && selectedQ4.value.isNotEmpty()
                                && selectedQ5.value.isNotEmpty()
                            ) {

                                puntos = 0
                                when (edad.toInt()) {
                                    in 0..44 -> {
                                        puntos += 0
                                    }
                                    in 45..54 -> {
                                        puntos += 2
                                    }
                                    in 55..64 -> {
                                        puntos += 3
                                    }
                                    in 65..150 -> {
                                        puntos += 4
                                    }
                                }

                                when (imc.toDouble()) {
                                    in 0.0..24.0 -> {
                                        puntos += 0
                                    }
                                    in 25.0..30.0 -> {
                                        puntos += 1
                                    }
                                    in 31.0..150.0 -> {
                                        puntos += 3
                                    }
                                }

                                if (selectedGender.value == Gender.male) {
                                    when (cintura.toDouble()) {
                                        in 0.0..93.9 -> {
                                            puntos += 0
                                        }
                                        in 94.0..102.0 -> {
                                            puntos += 1
                                        }
                                        in 102.1..150.0 -> {
                                            puntos += 3
                                        }
                                    }
                                } else {
                                    when (cintura.toDouble()) {
                                        in 0.0..79.0 -> {
                                            puntos += 0
                                        }
                                        in 80.0..88.0 -> {
                                            puntos += 1
                                        }
                                        in 88.1..150.0 -> {
                                            puntos += 3
                                        }
                                    }
                                }

                                if (selectedQ1.value == TrueFalse.no) {
                                    puntos += 2
                                }
                                if (selectedQ2.value == TrueFalse.no) {
                                    puntos += 2
                                }
                                if (selectedQ3.value == TrueFalse.si) {
                                    puntos += 2
                                }
                                if (selectedQ4.value == TrueFalse.si) {
                                    puntos += 2
                                }
                                if (selectedQ5.value == TrueFalse.si2) {
                                    puntos += 5
                                }
                                if (selectedQ5.value == TrueFalse.si) {
                                    puntos += 3
                                }

                                Log.i("Puntos Finales", "" + puntos)

                                navegarResultDiab(puntos)
                            } else {
                                Toast.makeText(
                                    context,
                                    "Favor de llenar todo los campos",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }catch(e: Exception){
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
                Spacer(modifier = Modifier.size(52.dp))
            }
        }
    }
}




