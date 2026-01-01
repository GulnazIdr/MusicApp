package com.example.coroutineapp.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.coroutineapp.R
import com.example.coroutineapp.presentation.navigation.BottomNavState
import com.example.coroutineapp.presentation.navigation.DownloadMusicScreen
import com.example.coroutineapp.presentation.navigation.PlaylistScreen
import com.example.coroutineapp.presentation.navigation.ProfileScreen
import com.example.coroutineapp.ui.theme.bluePrimaryColor

@Composable
fun BottomBar(
    navState: BottomNavState,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.Black)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 35.dp, horizontal = 30.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                painter = painterResource(R.drawable.playlist),
                contentDescription = "playlist icon",
                modifier = Modifier
                    .size(30.dp)
                    .clickable(onClick = {navState.navigateTo(PlaylistScreen)}),
                tint = Color.White
            )

            Icon(
                painter = painterResource(R.drawable.music),
                contentDescription = "music icon",
                modifier = Modifier
                    .size(30.dp)
                    .clickable(onClick = {navState.navigateTo(DownloadMusicScreen)}),
                tint = Color.White
            )

            Icon(
                painter = painterResource(R.drawable.profile),
                contentDescription = "profile icon",
                modifier = Modifier
                    .size(30.dp)
                    .clickable(onClick = {navState.navigateTo(ProfileScreen)}),
                tint = Color.White
            )
        }
    }
}

@Preview
@Composable
private fun BottomBarPreview() {
    val navController = rememberNavController()
    BottomBar(
        navState = BottomNavState(navController)
    )
}