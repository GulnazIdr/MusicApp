package com.example.coroutineapp.presentation.common

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coroutineapp.R
import com.example.coroutineapp.ui.theme.darkerGrey
import com.example.coroutineapp.ui.theme.lightGrey
import com.example.coroutineapp.ui.theme.lightThemeSurfaceColor
import com.example.coroutineapp.ui.theme.profileGrey

@Composable
fun CustomField(
    textValue: String,
    onValueChanged: (String) -> Unit,
    placeholder: String,
    isError: Boolean,
    errorText: String = "",
    label: String = "",
    enabled: Boolean = true,
    isPasswordField: Boolean = false,
    modifier: Modifier = Modifier,
    contentTypeModifier: Modifier = Modifier
) {
    var passwordVisibiliy by remember { mutableStateOf(false) }

    val inputState by animateColorAsState(
        targetValue = if(isError) Color.Red else profileGrey,
        label = "input field error"
    )

    Column{
        if(label.isNotEmpty()){
            Text(
                text = label,
                fontSize = 16.sp,
                lineHeight = 20.sp,
                fontWeight = FontWeight.W600,
                modifier = Modifier.padding(bottom = 12.dp),
                color = lightGrey,
            )
        }

        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(48.dp)
                .clip(RoundedCornerShape(14.dp))
                .border(2.dp, color = inputState, shape = RoundedCornerShape(14.dp))
                .background(lightThemeSurfaceColor),
        ){


            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 14.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                if (textValue.isEmpty()) {
                    Text(
                        text = placeholder,
                        fontWeight = FontWeight.W500,
                        fontSize = 14.sp,
                        lineHeight = 16.sp,
                        color = darkerGrey,
                        modifier = contentTypeModifier
                    )
                }


                Row(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    BasicTextField(
                        value = textValue,
                        textStyle = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.W400,
                            color = lightGrey
                        ),
                        onValueChange = onValueChanged,
                        visualTransformation = if (isPasswordField && !passwordVisibiliy) {
                            PasswordVisualTransformation()
                        } else {
                            VisualTransformation.None
                        },
                        enabled = enabled,
                        modifier = contentTypeModifier
                    )

                    if (isPasswordField) {
                        IconButton(onClick = { passwordVisibiliy = !passwordVisibiliy }) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_visibility_off),
                                contentDescription = "password visibility off"
                            )
                        }
                    }
                }
            }
        }

        if(isError)
            Text(
                text = errorText,
                fontWeight = FontWeight.W500,
                fontSize = 14.sp,
                lineHeight = 16.sp,
                color = Color.Red,
                modifier = modifier
                    .padding(top = 10.dp)
                    .align(Alignment.End)
            )
    }
}


@Preview
@Composable
private fun InputPreview() {
    CustomField(
        "gulna@g.com",
        {},
        "xyz@gmail.com",
        false,
        "something",
        "wrong password",
        false,
        true
    )
}