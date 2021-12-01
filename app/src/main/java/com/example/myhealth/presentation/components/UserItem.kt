package com.example.myhealth.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myhealth.model.Result
import com.example.myhealth.model.User

@ExperimentalMaterialApi
@Composable
fun UserItem(
    user: User
) {
    Card(
        elevation = 0.dp,
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Blue)
                .clickable {
                    //TODO
                }.padding(8.dp)
        ){
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                )   {
                    Text(
                        text = "Nombre: ",
                        style = TextStyle(
                            fontWeight = FontWeight.Medium,
                            fontSize = 18.sp,
                        )
                    )
                    Text(
                        text = user.nombre,
                        style = TextStyle(
                            fontWeight = FontWeight.Medium,
                            fontSize = 18.sp,
                            color = Color.DarkGray
                        )
                    )
                    Spacer(modifier = Modifier.size(12.dp))
                    Text(
                        text = "Correo: ",
                        style = TextStyle(
                            fontWeight = FontWeight.Medium,
                            fontSize = 18.sp,
                        )
                    )
                    Text(
                        text = user.correo,
                        style = TextStyle(
                            fontWeight = FontWeight.Medium,
                            fontSize = 18.sp,
                            color = Color.DarkGray
                        )
                    )
                }
            }
        }
    }
}