package com.example.myhealth.presentation.components

import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.DismissDirection.*
import androidx.compose.material.DismissValue.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myhealth.presentation.screens.perfil.ResultListState
import com.example.myhealth.presentation.screens.perfil.UserState
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.google.android.gms.common.internal.ResourceUtils

@ExperimentalMaterialApi
@Composable
fun UserList(
    state: UserState,
    isRefreshing: Boolean,
    refreshUser: () -> Unit,
){
    Box(
        modifier = Modifier.fillMaxWidth()
    ){
        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing = isRefreshing),
            onRefresh = refreshUser
        ){
            Column(
                modifier = Modifier.fillMaxWidth().background(Color.DarkGray), verticalArrangement = Arrangement.spacedBy(16.dp),
            )   {
                Log.e("item key User", "")
                //UserItem()
                refreshUser()

                }
            }

        if(state.error.isNotBlank()){
            Text(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp).align(Alignment.Center),
                text = state.error,
                color = Color.Red,
                textAlign = TextAlign.Center
            )
        }


        if(state.isLoading){
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }

    }
}