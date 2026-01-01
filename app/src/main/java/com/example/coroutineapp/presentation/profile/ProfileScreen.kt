package com.example.coroutineapp.presentation.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.coroutineapp.R
import com.example.coroutineapp.presentation.common.CommonScaffold
import com.example.coroutineapp.presentation.common.CommonTopAppBar
import com.example.coroutineapp.presentation.common.CustomField
import com.example.coroutineapp.presentation.common.NavigationButton
import com.example.coroutineapp.ui.theme.lightThemeSurfaceColor

@Composable
fun ProfileScreen(
    innerPadding: PaddingValues
) {
    var name by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(innerPadding)
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CommonTopAppBar(
                title = stringResource(R.string.profile)
            )

            Spacer(modifier = Modifier.height(50.dp))

            Image(
                painter = painterResource(R.drawable.user),
                contentDescription = "",
                modifier = Modifier.size(80.dp)
            )

            Spacer(modifier = Modifier.height(22.dp))

            CustomField(
                textValue = name,
                onValueChanged = { name = it },
                placeholder = "",
                isError = false,
                errorText = "",
                label = "Your name",
            )

            Spacer(modifier = Modifier.height(12.dp))

            CustomField(
                textValue = email,
                onValueChanged = { email = it },
                placeholder = "",
                isError = false,
                errorText = "",
                label = "Email"
            )

            Spacer(modifier = Modifier.height(12.dp))

            CustomField(
                textValue = password,
                onValueChanged = { password = it },
                placeholder = "",
                isError = false,
                errorText = "",
                label = "Password",
                isPasswordField = true
            )

            Spacer(modifier = Modifier.height(30.dp))

            NavigationButton(
                text = "Save",
                onAction = {},
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview
@Composable
private fun ProfileScreenPreview() {
    ProfileScreen(PaddingValues())
}