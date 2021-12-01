package com.example.myhealth.presentation.screens

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


//Ya no se usa esta pantalla se cambio por resulDialog
@Composable
fun ResultPgc(
    result: String,
    addNewResult: (String, String, String) -> Unit,
    state: PerfilState
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
                    text = "Su PGC es de",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 42.sp,
                        fontWeight = FontWeight.Black
                    )
                )
                Log.i("resultPGC Pant2", "" + result)
                Text(
                    text = "% $result",
                    style = TextStyle(
                        color = Color.Cyan,
                        fontSize = 42.sp,
                        fontWeight = FontWeight.Black
                    )
                )
                Spacer(modifier = Modifier.size(24.dp))


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
                            addNewResult("Resultado PGC", "%$result", fecha)
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