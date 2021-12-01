package com.example.myhealth.presentation.screens.perfil

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.example.myhealth.presentation.components.ResultList
import com.google.firebase.firestore.FirebaseFirestore

@ExperimentalMaterialApi
@Composable
fun Perfil(
    state: ResultListState,
    isRefreshing: Boolean,
    refreshData: () -> Unit,
    deleteResult: (String) -> Unit,
    email: String
) {
    var name by remember{ mutableStateOf("")}
    var correo by remember{ mutableStateOf("")}
    var edad by remember{ mutableStateOf("")}
    val modificar = remember{ mutableStateOf(false)}
    val mostrar = remember{ mutableStateOf(true)}
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current

    if(mostrar.value) {
        FirebaseFirestore.getInstance().collection("usuarios").document(email)
            .get().addOnSuccessListener {
                name = it.get("nombre") as String
                correo = it.get("correo") as String
                edad = it.get("edad") as String
            }
    }


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.background),
    ) {
        Image(
            painter = painterResource(id = R.drawable.fondo_app),
            contentDescription = "Fondo App",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.padding(8.dp, 8.dp),
                    text = "Mi Perfil",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Black,
                        textAlign = TextAlign.Center
                    )
                )

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp, 0.dp), Arrangement.Start)
                {
                    Text(
                        modifier = Modifier
                            .height(24.dp)
                            .background(Color.Transparent),
                        text = "Nombre: ",
                        style = TextStyle(
                            fontWeight = FontWeight.Medium,
                            fontSize = 18.sp,
                            )
                    )
                    //Spacer(modifier = Modifier.size(12.dp))
                    TextField(
                        modifier = Modifier
                            .width(200.dp)
                            .height(50.dp),
                        enabled = modificar.value,
                        value = name,
                        onValueChange = { if (it.length <= 30) name = it },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                focusManager.clearFocus()
                            }
                        ),
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.Transparent,
                            textColor = Color.Black
                        )
                    )
                }
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp, 0.dp), Arrangement.Start) {
                    Text(
                        modifier = Modifier
                            .height(24.dp),
                        text = "Edad: ",
                        style = TextStyle(
                            fontWeight = FontWeight.Medium,
                            fontSize = 18.sp,
                        )
                    )
                    //Spacer(modifier = Modifier.size(12.dp))
                    TextField(
                        modifier = Modifier
                            .height(50.dp)
                            .width(100.dp),

                        enabled = modificar.value,
                        value = edad,
                        onValueChange = { if (it.length <= 3) edad = it },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Done
                        ),
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.Transparent,
                            textColor = Color.Black
                        )
                    )

                    //Spacer(modifier = Modifier.size(100.dp))
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp, 0.dp), Arrangement.End) {
                        Button(
                            modifier = Modifier
                                .width(100.dp)
                                .height(34.dp),
                            onClick = {
                                if (modificar.value) {
                                    modificar.value = false
                                    mostrar.value = true
                                    db(email, name, correo, edad)
                                } else {
                                    modificar.value = true
                                    mostrar.value = false
                                }
                            }
                        ) {
                            if (modificar.value) {
                                Text("Guardar")
                            } else {
                                Text("Modificar")
                            }
                        }
                    }
                }

                //Spacer(modifier = Modifier.size(12.dp))
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp, 0.dp), Arrangement.Start) {
                    Text(
                        modifier = Modifier.height(24.dp),
                        text = "Correo: ",
                        style = TextStyle(
                            fontWeight = FontWeight.Medium,
                            fontSize = 18.sp,
                        )
                    )
                    //Spacer(modifier = Modifier.size(12.dp))
                    TextField(
                        modifier = Modifier
                            .width(200.dp),
                        enabled = false,
                        value = correo,
                        onValueChange = { correo = it },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email,
                            imeAction = ImeAction.Done
                        ),
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.Transparent,
                            textColor = Color.Black
                        )
                    )
                }


                Text(
                    text = "Resultados",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Black,
                        textAlign = TextAlign.Center
                    )
                )
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Scaffold(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        ResultList(
                            state = state,
                            isRefreshing = isRefreshing,
                            refreshData = refreshData,
                            deleteResult = deleteResult
                        )
                    }
                }
            }
        }
    }
}

fun db(email: String, name: String, correo: String, edad: String){
    FirebaseFirestore.getInstance().collection("usuarios").document(email).set(
        hashMapOf(
            "nombre" to name,
            "correo" to correo,
            "edad" to edad,
        )
    )
}
