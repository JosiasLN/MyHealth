package com.example.myhealth.presentation.screens

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myhealth.R
import com.example.myhealth.presentation.screens.perfil.PerfilState
import java.util.*


@Composable
fun ResultImc(
    result: String,
    addNewResult: (String, String, String) -> Unit,
    state: PerfilState,
) {
    var fecha by remember { mutableStateOf("00/00/000") }
    val scrollState = rememberScrollState()
    val calendario = Calendar.getInstance()

    val context = LocalContext.current


    fecha = "${calendario.get(Calendar.DAY_OF_MONTH)}/${calendario.get(Calendar.MONTH)+1}/${calendario.get(
        Calendar.YEAR)}"


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
                    text = "Su IMC es de",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 42.sp,
                        fontWeight = FontWeight.Black,
                        textAlign = TextAlign.Center
                    )
                )
                Text(
                    text = result,
                    style = TextStyle(color = Color.Cyan, fontSize = 42.sp)
                )
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    text = "SITUACIÓN",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 42.sp,
                        fontWeight = FontWeight.Black
                    )

                )
                mostrarSituacion(result.toDouble())

                if (state.error.isNotBlank()) {
                    Log.i("Entro al state.error", "Si")
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp),
                        text = state.error,
                        style = TextStyle(color = Color.Red, textAlign = TextAlign.Center)
                    )
                }

                if (state.isLoading) {
                    Log.i("Entro al loading", "" + state.isLoading)
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                } else {
                    Log.i("Entro al addNewResult", "Si")
                    Button(
                        shape = RoundedCornerShape(25),
                        onClick = {
                            addNewResult("Resultado IMC", result, fecha)
                            Toast.makeText(context, "Resultado Guardado", Toast.LENGTH_LONG).show()
                        }
                    ) {
                        Text("Guardar Resultado")
                    }
                }
            }
        }
    }
}


@SuppressLint("ComposableNaming")
@Composable
fun mostrarSituacion(situacion: Double){
    val context = LocalContext.current
    when(situacion){
        in 0.0..18.5 -> {
            Text(
                text = "Bajo Peso",
                style = TextStyle(color = Color.Yellow, fontSize = 42.sp, fontWeight = FontWeight.Black)
            )
            Image(
                painter = painterResource(id = R.drawable.peso_bajo),
                contentDescription = "Peso Bajo Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(150.dp,250.dp)
            )
        }
        in 18.5..24.9 -> {
            Text(
                text = "Peso Normal",
                style = TextStyle(color = Color.Green, fontSize = 42.sp)
            )
            Image(
                painter = painterResource(id = R.drawable.peso_ideal),
                contentDescription = "Peso Ideal Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(150.dp,250.dp)
            )

        }
        in 25.0..29.9 -> {
            Text(
                text = "Sobrepeso",
                style = TextStyle(color = Color.Yellow, fontSize = 42.sp, fontWeight = FontWeight.Black)
            )
            Image(
                painter = painterResource(id = R.drawable.sobrepeso),
                contentDescription = "Sobrepeso Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(150.dp,250.dp)
            )
        }
        in 30.0..34.9 -> {
            Text(
                text = "Obesidad",
                style = TextStyle(color = Color.Red, fontSize = 42.sp, fontWeight = FontWeight.Black)
            )
            Image(
                painter = painterResource(id = R.drawable.obesidad),
                contentDescription = "Obesidad Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(150.dp,250.dp)
            )
        }
        in 35.0..39.9 -> {
            Text(
                text = "Obesidad Severa",
                style = TextStyle(color = Color.Red, fontSize = 42.sp, fontWeight = FontWeight.Black)
            )
            Image(
                painter = painterResource(id = R.drawable.obesidad_severa),
                contentDescription = "Obesidad Severa Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(150.dp,250.dp)
            )
        }
        in 40.0..200.0 -> {
            Text(
                text = "Obesidad Morbida",
                style = TextStyle(color = Color.Red, fontSize = 42.sp, fontWeight = FontWeight.Black)
            )
            Image(
                painter = painterResource(id = R.drawable.obesidad_morbida),
                contentDescription = "Obesidad Morbida Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(150.dp,250.dp)
            )
        }
        else -> {
            Toast.makeText(context,"Error al calcular situación", Toast.LENGTH_LONG).show()
        }
    }
}