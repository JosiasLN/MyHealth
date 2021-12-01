package com.example.myhealth.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myhealth.model.Result

@ExperimentalMaterialApi
@Composable
fun ResultListItem(
    result: Result
) {
    Card(
        elevation = 0.dp,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.primary)
                .clickable {}
                .padding(8.dp)
        ){
          Column(
              modifier = Modifier
                  .fillMaxWidth()
                  .padding(vertical = 8.dp),
              verticalArrangement = Arrangement.spacedBy(12.dp)
          ) {
                Text(
                     modifier = Modifier.fillMaxWidth(),
                    text = result.tipoResult,
                    style = TextStyle(
                        fontWeight = FontWeight.Medium,
                        fontSize = 18.sp
                    )
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                )   {
                    Text(
                        text = "Fecha: ",
                        style = TextStyle(
                          fontWeight = FontWeight.Medium,
                          fontSize = 18.sp,
                        )
                    )
                    Text(
                      text = result.fecha,
                      style = TextStyle(
                          fontWeight = FontWeight.Medium,
                          fontSize = 18.sp,
                          color = Color.Black
                      )
                    )
                    Spacer(modifier = Modifier.size(12.dp))
                    Text(
                        text = "Resultado: ",
                        style = TextStyle(
                          fontWeight = FontWeight.Medium,
                          fontSize = 18.sp,
                        )
                    )
                    Text(
                        text = result.resultObtenido,
                        style = TextStyle(
                          fontWeight = FontWeight.Medium,
                          fontSize = 18.sp,
                          color = Color.Black
                        )
                    )
                }
            }
        }
    }
}