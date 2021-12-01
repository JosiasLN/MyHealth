package com.example.myhealth.presentation.screens.noticias

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.myhealth.R
import com.example.myhealth.model.News

@ExperimentalCoilApi
@Composable
fun DetailsNoticias(
    newTitle: String,
    navController: NavController,
    viewModel: DetailsNoticiasViewModel = hiltViewModel()
) {
    val new by viewModel.getNewsByTitle(newTitle).observeAsState(initial = null)
    DetailsNoticias(newTitle, navController, new)
}

@ExperimentalCoilApi
@Composable
fun DetailsNoticias(
    newTitle: String?,
    navController: NavController,
    new: News?,
) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(newTitle ?: "", maxLines = 1) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                        )
                    }
                }
            )
        }
    ) {
        Log.e("Detail Noticias", "entro al new?.let")
        new?.let {
            Card(
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.padding(8.dp).fillMaxWidth()
            ) {
                Column {

                    if (new.urlToImage == null || new.url == null || new.author == null || new.content == null
                        || new.title == null
                    ) {
                        Toast.makeText(
                            context,
                            "Ocurrio un error al cargar una noticia",
                            Toast.LENGTH_LONG
                        ).show()
                    } else {

                        Image(
                            modifier = Modifier.fillMaxWidth().aspectRatio(16f / 9f),
                            painter = rememberImagePainter(
                                data = new.urlToImage,
                                builder = {
                                    placeholder(R.drawable.placeholder)
                                    error(R.drawable.placeholder)
                                }
                            ),
                            contentDescription = null,
                            contentScale = ContentScale.FillWidth
                        )
                        Log.e("news url", "" + new.url)
                        Log.e("news url", "" + new.url)
                        Column(Modifier.padding(8.dp)) {

                            Text(new.title, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                            Text(new.content ?: "")
                            Log.e("content details", "" + new.content)
                            Box(Modifier.size(8.dp))
                            Button(
                                modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                                onClick = {
                                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(new.url))
                                    context.startActivity(intent)
                                }
                            ) {
                                Text("Ver mas...")
                            }
                            Log.e("news details url", "" + new.url)
                        }

                    }

                }
            }
        } ?: run {
            Log.e("entro al run", "run")
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator()
            }
        }
    }
}

