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
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.myhealth.R
import kotlin.math.log10

//Calcular resultado correcto y diseñar pantalla
object Gender {
    const val male = "Hombre"
    const val female = "Mujer"
}

@Composable
fun Pgc(
    navegarResultPgc: (String) -> Unit,
) {

    var cintura by rememberSaveable { mutableStateOf("") }
    var cuello by rememberSaveable { mutableStateOf("") }
    var altura by rememberSaveable { mutableStateOf("") }
    var cadera by rememberSaveable { mutableStateOf("") }
    val selectedGender = rememberSaveable { mutableStateOf("")}
    var resultPgc by rememberSaveable { mutableStateOf(0.0) }

    var resultText by rememberSaveable { mutableStateOf("")}

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
            ConstraintLayout {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                        .verticalScroll(state = scrollState),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Porcentage de Grasa Corporal",
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Black,
                            textAlign = TextAlign.Center
                        )

                    )

                    TextField(
                        value = cintura,
                        onValueChange = { if (it.length <= 5) cintura = it },
                        label = { Text(text = "Cintura (cm)") },
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
                        value = cuello,
                        onValueChange = { if (it.length <= 5) cuello = it },
                        label = { Text(text = "Cuello (cm)") },
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
                        value = altura,
                        onValueChange = { if (it.length <= 3) altura = it },
                        label = { Text(text = "Estatura (cm)") },
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

                    if (selectedGender.value == Gender.female) {
                        TextField(
                            value = cadera,
                            onValueChange = { if (it.length <= 5) cadera = it },
                            label = { Text(text = "Cadera (cm)") },
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
                    }

                    Spacer(modifier = Modifier.size(18.dp))
                    Button(
                        shape = RoundedCornerShape(25),
                        onClick = {

                            try {
                                if (cintura.isEmpty() || cuello.isEmpty() || altura.isEmpty() || selectedGender.value.isEmpty()) {
                                    Toast.makeText(context, "Favor de llenar todo los campos", Toast.LENGTH_LONG).show()
                                }else if(cintura.toDouble() <= 0){
                                    Toast.makeText(context, "Favor de usar otro valor para la cintura", Toast.LENGTH_LONG).show()
                                }else if(cuello.toDouble() <= 0){
                                    Toast.makeText(context, "Favor de usar otro valor para el cuello", Toast.LENGTH_LONG).show()
                                }else if(altura.toDouble() <= 0){
                                    Toast.makeText(context, "Favor de usar otro valor para la estatura", Toast.LENGTH_LONG).show()
                                }else if (selectedGender.value == Gender.male) {
                                    resultPgc = 495 / (1.0324 - 0.19077 * (log10(cintura.toDouble() - cuello.toDouble())) + 0.15456 * (log10(
                                        altura.toDouble()))) - 450

                                    if (resultPgc <= 0.0) {
                                        Toast.makeText(
                                            context,
                                            "Parece que este PGC no es correcto \n " +
                                                    "Asegúrese de que los valores agregados sean correctos",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    } else {
                                        resultText = (String.format("%.2f", resultPgc))
                                        navegarResultPgc(resultText)
                                    }

                                } else if (selectedGender.value == Gender.female) {
                                     if(cadera.isEmpty()){
                                         Toast.makeText(context, "Favor de llenar el campo de cadera", Toast.LENGTH_LONG).show()
                                     }else if (cadera.toDouble() <= 0) {
                                        Toast.makeText(
                                            context,
                                            "Favor de usar otro valor para la cadera",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    }else {
                                         resultPgc =
                                             495 / (1.29579 - 0.35004 * (log10(cintura.toDouble() + cadera.toDouble() - cuello.toDouble())) + 0.22100 * (log10(
                                                 altura.toDouble()
                                             ))) - 450

                                         if (resultPgc <= 0.0) {
                                             Toast.makeText(
                                                 context,
                                                 "Parece que este PGC no es correcto \n " +
                                                         "Asegúrese de que los valores agregados sean correctos",
                                                 Toast.LENGTH_LONG
                                             ).show()
                                         } else {
                                             resultText = (String.format("%.2f", resultPgc))
                                             navegarResultPgc(resultText)
                                         }
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
                    Spacer(modifier = Modifier.size(52.dp))
                }
            }
        }
    }
}