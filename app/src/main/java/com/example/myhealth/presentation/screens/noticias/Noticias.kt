package com.example.myhealth.presentation.screens.noticias


import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.myhealth.R
import com.example.myhealth.model.News
import com.example.myhealth.model.NewsApiResponse
import com.example.myhealth.navigation.Destinations
import com.example.myhealth.ui.theme.NewsAppTheme

@ExperimentalCoilApi
@Composable
fun Noticias(
    navController: NavController,
    viewModel: NoticiasViewModel = hiltViewModel()
) {

    val newsList by viewModel.getNews().observeAsState(initial = emptyList())
    Noticias(navController, newsList)
    Log.e("noticia sin internet", "error internet" )


}

@ExperimentalCoilApi
@Composable
fun Noticias(
    navController: NavController,
    news: List<News>
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Top news") },
            )
        }
    )
    {
        LazyColumn {
            items(
                items = news
            ) { new ->
                Card(
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.padding(8.dp).fillMaxWidth().clickable {
                        navController.navigate("${Destinations.DetailsNoticias.route}/${new.title}")
                        Log.e("error al dar click", "" + new.content)
                    }
                ) {
                    Column {

                        Log.e("news url imagen ", "" + new.urlToImage)
                        Log.e("news url", "" + new.url)

                        if (new.urlToImage == null || new.url == null || new.author == null || new.content == null
                            || new.title == null
                        ) {
                            Log.e("Error Noticias", "Error al cargar una noticia")
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

                            Column(Modifier.padding(8.dp)) {
                                Text(new.title, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                                Text(new.content ?: "", maxLines = 3)

                            }
                        }
                    }
                }
            }
        }
    }
}

